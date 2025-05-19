/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.Conexao;
import DAO.MusicaDAO;
import model.Musica;
import model.Usuario;
import view.HistoricoFrame;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author fabio
 */
public class ControllerHistorico {
    private HistoricoFrame view;
    private Usuario usuarioLogado;
    private MusicaDAO musicaDAO;

    public ControllerHistorico(HistoricoFrame view, Usuario usuarioLogado) {
        this.view = view;
        this.usuarioLogado = usuarioLogado;
        // this.musicaDAO = new MusicaDAO(new Conexao().getConnection()); // Ou obter conexão a cada método
    }

    public void carregarMusicasCurtidas() {
        if (usuarioLogado == null) return;

        List<Musica> musicas = new ArrayList<>();
        Conexao fabricaConexao = new Conexao();
        Connection conn = null;
        try {
            conn = fabricaConexao.getConnection();
            MusicaDAO dao = new MusicaDAO(conn); // Cria DAO com a conexão
            musicas = dao.listarMusicasCurtidas(usuarioLogado.getIdUsuario());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar músicas curtidas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
        }
        view.exibirMusicasCurtidas(musicas); // Método a ser criado na view
    }

    public void carregarMusicasDescurtidas() {
        if (usuarioLogado == null) return;

        List<Musica> musicas = new ArrayList<>();
        Conexao fabricaConexao = new Conexao();
        Connection conn = null;
        try {
            conn = fabricaConexao.getConnection();
            MusicaDAO dao = new MusicaDAO(conn);
            musicas = dao.listarMusicasDescurtidas(usuarioLogado.getIdUsuario());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar músicas descurtidas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
        }
        view.exibirMusicasDescurtidas(musicas); // Método a ser criado na view
    }

    // Mais tarde, adicionar método para carregar histórico de buscas
}