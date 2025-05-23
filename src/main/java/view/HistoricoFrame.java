/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ControllerHistorico;
import model.Musica;
import model.Usuario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *
 * @author fabio
 */
public class HistoricoFrame extends javax.swing.JFrame {

    private ControllerHistorico controller;
    private Usuario usuarioLogado;
    
    /**
     * Creates new form HistoricoFrame
     */
    
     public HistoricoFrame(Usuario usuario) {
        initComponents();
        this.usuarioLogado = usuario;
        this.controller = new ControllerHistorico(this, this.usuarioLogado);

        carregarDadosAbaSelecionada();

        tab_painelHistorico.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                carregarDadosAbaSelecionada();
            }
        });
    }
     
    
      private void carregarDadosAbaSelecionada() {
        int indiceAbaSelecionada = tab_painelHistorico.getSelectedIndex();
        if (indiceAbaSelecionada == 1) {
            controller.carregarMusicasCurtidas();
        } else if (indiceAbaSelecionada == 2) {
            controller.carregarMusicasDescurtidas();
        }
    }

    public void exibirMusicasCurtidas(List<Musica> musicas) {
        popularTabela(table_curtidasHistorico, musicas);
    }

    public void exibirMusicasDescurtidas(List<Musica> musicas) {
        popularTabela(table_descurtidasHistorico, musicas);
    }

    private void popularTabela(JTable tabela, List<Musica> musicas) {
        String[] colunas = {"ID", "Nome", "Autor", "Gênero"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (musicas != null) {
            for (Musica musica : musicas) {
                model.addRow(new Object[]{
                    musica.getIdMusica(),
                    musica.getNomeMusica(),
                    musica.getAutorMusica(),
                    musica.getGeneroMusica()
                });
            }
        }
        tabela.setModel(model);

        if (musicas == null || musicas.isEmpty()) {
        }
    }

        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_spotifeiHistorico = new javax.swing.JLabel();
        tab_painelHistorico = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_historicoHistorico = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_curtidasHistorico = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        table_descurtidasHistorico = new javax.swing.JTable();
        bt_voltarHistorico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_spotifeiHistorico.setBackground(new java.awt.Color(30, 215, 96));
        lbl_spotifeiHistorico.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_spotifeiHistorico.setForeground(new java.awt.Color(25, 20, 20));
        lbl_spotifeiHistorico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_spotifeiHistorico.setText("SPOTIFEI");
        lbl_spotifeiHistorico.setToolTipText("");

        table_historicoHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(table_historicoHistorico);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        );

        tab_painelHistorico.addTab("Histórico", jPanel1);

        table_curtidasHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(table_curtidasHistorico);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        );

        tab_painelHistorico.addTab("Curtidas", jPanel2);

        table_descurtidasHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(table_descurtidasHistorico);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        );

        tab_painelHistorico.addTab("Descurtidas", jPanel3);

        bt_voltarHistorico.setBackground(new java.awt.Color(25, 20, 20));
        bt_voltarHistorico.setForeground(new java.awt.Color(30, 215, 96));
        bt_voltarHistorico.setText("<-- Voltar");
        bt_voltarHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_voltarHistoricoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_voltarHistorico)
                .addGap(275, 275, 275)
                .addComponent(lbl_spotifeiHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addGap(355, 355, 355))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(tab_painelHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_spotifeiHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_voltarHistorico))
                .addGap(18, 18, 18)
                .addComponent(tab_painelHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_voltarHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_voltarHistoricoActionPerformed
        if (this.usuarioLogado != null) {
            MenuFrame mf = new MenuFrame(this.usuarioLogado);
            mf.setVisible(true);
        } else {
            LoginFrame lf = new LoginFrame();
            lf.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_bt_voltarHistoricoActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(HistoricoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(HistoricoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(HistoricoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(HistoricoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new HistoricoFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_voltarHistorico;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbl_spotifeiHistorico;
    private javax.swing.JTabbedPane tab_painelHistorico;
    private javax.swing.JTable table_curtidasHistorico;
    private javax.swing.JTable table_descurtidasHistorico;
    private javax.swing.JTable table_historicoHistorico;
    // End of variables declaration//GEN-END:variables
}
