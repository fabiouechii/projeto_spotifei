/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.Conexao;
import DAO.PlaylistDAO;
import DAO.MusicaDAO;
import model.Playlist;
import model.Musica;
import model.Usuario;
import view.PlaylistFrame;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */

public class ControllerPlaylist {
    private PlaylistFrame view;
    private Usuario usuarioLogado;

    public ControllerPlaylist(PlaylistFrame view, Usuario usuarioLogado) {
        this.view = view;
        this.usuarioLogado = usuarioLogado;
    }
    
    public void carregarPlaylistsParaAbaApagar() {
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(view, "Usuário não logado.", "Erro", JOptionPane.ERROR_MESSAGE);
            view.exibirPlaylistsNaTabelaApagar(new ArrayList<>());
            return;
        }
        List<Playlist> playlists = new ArrayList<>();
        Conexao fabrica = new Conexao();
        Connection conn = null;
        try {
            conn = fabrica.getConnection();
            PlaylistDAO dao = new PlaylistDAO(conn);
            playlists = dao.listarPlaylistsPorUsuario(usuarioLogado.getIdUsuario());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar playlists para apagar: " + e.getMessage(), "Erro de BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        view.exibirPlaylistsNaTabelaApagar(playlists);
    }
    
    public void carregarPlaylistsParaAbaEditar() {
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(view, "Usuário não logado.", "Erro", JOptionPane.ERROR_MESSAGE);
            view.exibirPlaylistsNaTabelaEditar(new ArrayList<>());
            return;
        }
        List<Playlist> playlists = new ArrayList<>();
        Conexao fabrica = new Conexao();
        Connection conn = null;
        try {
            conn = fabrica.getConnection();
            PlaylistDAO dao = new PlaylistDAO(conn);
            playlists = dao.listarPlaylistsPorUsuario(usuarioLogado.getIdUsuario());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar playlists para edição: " + e.getMessage(), "Erro de BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        view.exibirPlaylistsNaTabelaEditar(playlists);
    }
    
    public void carregarMusicasDaPlaylistSelecionadaParaEdicao() {
        Playlist playlistSelecionada = view.getPlaylistSelecionadaParaEditar();
        if (playlistSelecionada == null) {
            view.exibirMusicasDaPlaylistNaAbaEditar(new ArrayList<>());
            return;
        }
        if (usuarioLogado == null) { /* ... */ return; }

        List<Musica> musicas = new ArrayList<>();
        Conexao fabrica = new Conexao();
        Connection conn = null;
        try {
            conn = fabrica.getConnection();
            PlaylistDAO dao = new PlaylistDAO(conn);
            musicas = dao.listarMusicasDaPlaylist(playlistSelecionada.getIdPlaylist());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar músicas da playlist: " + e.getMessage(), "Erro de BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        view.exibirMusicasDaPlaylistNaAbaEditar(musicas);
    }

    public void acaoCriarNovaPlaylist() {
        String nomeNovaPlaylist = view.getTxt_nomePlaylist().getText().trim();
        if (nomeNovaPlaylist.isEmpty()) {
            JOptionPane.showMessageDialog(view, "O nome da playlist não pode ser vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(view, "Erro: Usuário não identificado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Conexao fabrica = new Conexao();
        Connection conn = null;
        try {
            conn = fabrica.getConnection();
            PlaylistDAO dao = new PlaylistDAO(conn);

            if (dao.nomePlaylistJaExiste(nomeNovaPlaylist, usuarioLogado.getIdUsuario())) {
                JOptionPane.showMessageDialog(view, "Você já possui uma playlist com o nome '" + nomeNovaPlaylist + "'.", "Playlist Existente", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Playlist novaPlaylist = new Playlist(nomeNovaPlaylist, usuarioLogado.getIdUsuario());
            int idCriado = dao.criarPlaylist(novaPlaylist);

            if (idCriado != -1) {
                JOptionPane.showMessageDialog(view, "Playlist '" + nomeNovaPlaylist + "' criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                view.limparCampoNovaPlaylist();
                carregarPlaylistsParaAbaApagar();
                carregarPlaylistsParaAbaEditar();
            } else {
                JOptionPane.showMessageDialog(view, "Não foi possível criar a playlist.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro de banco de dados ao criar playlist: " + e.getMessage(), "Erro de BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
    
    public void acaoApagarPlaylistSelecionada() {
        Playlist playlistParaApagar = view.getPlaylistSelecionadaParaApagar();
        if (playlistParaApagar == null) {
            JOptionPane.showMessageDialog(view, "Selecione uma playlist para apagar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (usuarioLogado == null) { /* ... */ return; }

        int confirm = JOptionPane.showConfirmDialog(view,
                "Tem certeza que deseja apagar a playlist '" + playlistParaApagar.getNomePlaylist() + "'?\nTodas as músicas nela serão removidas desta playlist.",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            Conexao fabrica = new Conexao();
            Connection conn = null;
            try {
                conn = fabrica.getConnection();
                PlaylistDAO dao = new PlaylistDAO(conn);
                dao.deletarPlaylist(playlistParaApagar.getIdPlaylist());
                JOptionPane.showMessageDialog(view, "Playlist apagada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                carregarPlaylistsParaAbaApagar();
                carregarPlaylistsParaAbaEditar();
                view.exibirMusicasDaPlaylistNaAbaEditar(null);

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(view, "Erro ao apagar playlist: " + e.getMessage(), "Erro de BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
    
    public void acaoRenomearPlaylistSelecionada() {
        Playlist playlistParaRenomear = view.getPlaylistSelecionadaParaEditar();
        if (playlistParaRenomear == null) {
            JOptionPane.showMessageDialog(view, "Selecione uma playlist para renomear.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String novoNome = view.getTxt_novonomePlaylist().getText().trim();
        if (novoNome.isEmpty()) {
            JOptionPane.showMessageDialog(view, "O novo nome da playlist não pode ser vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (novoNome.equals(playlistParaRenomear.getNomePlaylist())) {
            JOptionPane.showMessageDialog(view, "O novo nome é igual ao nome atual.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (usuarioLogado == null) { /* ... */ return; }

        Conexao fabrica = new Conexao();
        Connection conn = null;
        try {
            conn = fabrica.getConnection();
            PlaylistDAO dao = new PlaylistDAO(conn);

            dao.renomearPlaylist(playlistParaRenomear.getIdPlaylist(), novoNome);
            JOptionPane.showMessageDialog(view, "Playlist renomeada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            carregarPlaylistsParaAbaApagar();
            carregarPlaylistsParaAbaEditar();
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao renomear playlist: " + e.getMessage(), "Erro de BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
    
    public void acaoRemoverMusicaSelecionadaDaPlaylist() {
        //
    }
    
    public void adicionarMusicaEmPlaylist(int idMusica, int idPlaylist) {
        Conexao fabrica = new Conexao();
        Connection conn = null;
        try {
            conn = fabrica.getConnection();
            PlaylistDAO dao = new PlaylistDAO(conn);
            dao.adicionarMusicaNaPlaylist(idPlaylist, idMusica);
            JOptionPane.showMessageDialog(null, "Música adicionada à playlist!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao adicionar música à playlist: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
