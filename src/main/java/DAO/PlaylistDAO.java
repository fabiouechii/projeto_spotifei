/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Playlist;
import model.Musica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class PlaylistDAO {
    private Connection conn;

    public PlaylistDAO(Connection conn) {
        this.conn = conn;
    }
    
    public int criarPlaylist(Playlist playlist) throws SQLException {
        String sql = "INSERT INTO playlists (nome_playlist, id_usuario) VALUES (?, ?)";
        int idPlaylistCriada = -1;
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, playlist.getNomePlaylist());
            stmt.setInt(2, playlist.getIdUsuario());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idPlaylistCriada = generatedKeys.getInt(1);
                        playlist.setIdPlaylist(idPlaylistCriada);
                    }
                }
            }
        }
        return idPlaylistCriada;
    }
    
    public boolean nomePlaylistJaExiste(String nomePlaylist, int idUsuario) throws SQLException {
        String sql = "SELECT COUNT(*) FROM playlists WHERE nome_playlist ILIKE ? AND id_usuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomePlaylist);
            stmt.setInt(2, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    public List<Playlist> listarPlaylistsPorUsuario(int idUsuario) throws SQLException {
        List<Playlist> playlistsDoUsuario = new ArrayList<>();
        String sql = "SELECT id_playlist, nome_playlist, id_usuario FROM playlists WHERE id_usuario = ? ORDER BY nome_playlist ASC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    playlistsDoUsuario.add(new Playlist(rs.getInt("id_playlist"), rs.getString("nome_playlist"), rs.getInt("id_usuario")));
                }
            }
        }
        return playlistsDoUsuario;
    }
    
    public void deletarPlaylist(int idPlaylist) throws SQLException {
        String sql = "DELETE FROM playlists WHERE id_playlist = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPlaylist);
            stmt.executeUpdate();
        }
    }
    
    public void adicionarMusicaNaPlaylist(int idPlaylist, int idMusica) throws SQLException {
        String sql = "INSERT INTO playlist_musicas (id_playlist, id_musica) VALUES (?, ?) ON CONFLICT (id_playlist, id_musica) DO NOTHING";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPlaylist);
            stmt.setInt(2, idMusica);
            stmt.executeUpdate();
        }
    }
    
    public void removerMusicaDaPlaylist(int idPlaylist, int idMusica) throws SQLException {
        String sql = "DELETE FROM playlist_musicas WHERE id_playlist = ? AND id_musica = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPlaylist);
            stmt.setInt(2, idMusica);
            stmt.executeUpdate();
        }
    }
    
    public List<Musica> listarMusicasDaPlaylist(int idPlaylist) throws SQLException {
        List<Musica> musicasDaPlaylist = new ArrayList<>();
        String sql = "SELECT m.id_musica, m.nome_musica, m.autor_musica, m.genero_musica " +
                     "FROM musicas m " +
                     "JOIN playlist_musicas pm ON m.id_musica = pm.id_musica " +
                     "WHERE pm.id_playlist = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPlaylist);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    musicasDaPlaylist.add(new Musica(
                        rs.getInt("id_musica"),
                        rs.getString("nome_musica"),
                        rs.getString("autor_musica"),
                        rs.getString("genero_musica")
                    ));
                }
            }
        }
        return musicasDaPlaylist;
    }
    
    public void renomearPlaylist(int idPlaylist, String novoNome) throws SQLException {
        String sql = "UPDATE playlists SET nome_playlist = ? WHERE id_playlist = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setInt(2, idPlaylist);
            stmt.executeUpdate();
        }
    }
    
    
}
