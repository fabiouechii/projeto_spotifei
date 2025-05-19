/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.Conexao;
import DAO.MusicaDAO;
import model.Musica;
import model.Usuario;
import view.PesquisaFrame;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author fabio
 */
public class ControllerPesquisa {
    private PesquisaFrame view;
    private Usuario usuarioLogado;
    
    public ControllerPesquisa(PesquisaFrame view, Usuario usuarioLogado) {
        this.view = view;
        this.usuarioLogado = usuarioLogado;
    }
    
    public void pesquisarMusicas() {
        String termo = view.getTxt_termoPesquisa().getText();

        if (termo == null || termo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor, digite um termo para pesquisar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            view.limparTabela();
            return;
        }
        
        Conexao fabricaConexao = new Conexao();
        Connection conn = null;
        try {
            conn = fabricaConexao.getConnection();
            MusicaDAO musicaDAO = new MusicaDAO(conn);
            List<Musica> musicasEncontradas = musicaDAO.buscarMusicas(termo);

            view.popularTabelaMusicas(musicasEncontradas, termo);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao pesquisar músicas: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            view.limparTabela();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.err.println("Erro ao fechar a conexão no ControllerPesquisa: " + ex.getMessage());
                }
            }
        }
    }
}
