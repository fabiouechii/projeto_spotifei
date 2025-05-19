/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Musica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class MusicaDAO {
    private Connection conn;

    public MusicaDAO(Connection conn) {
        this.conn = conn;
    }
    
    
     public List<Musica> buscarMusicas(String termoPesquisa) throws SQLException {
        List<Musica> musicasEncontradas = new ArrayList<>();
        
        String sql = "SELECT id_musica, nome_musica, autor_musica, genero_musica FROM musicas " +
                     "WHERE nome_musica ILIKE ? OR autor_musica ILIKE ? OR genero_musica ILIKE ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String termoComCuringa = "%" + termoPesquisa + "%";
            stmt.setString(1, termoComCuringa);
            stmt.setString(2, termoComCuringa);
            stmt.setString(3, termoComCuringa);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_musica");
                    String nome = rs.getString("nome_musica");
                    String autor = rs.getString("autor_musica");
                    String genero = rs.getString("genero_musica");
                    musicasEncontradas.add(new Musica(id, nome, autor, genero));
                }
            }
        }
        return musicasEncontradas;
    }
     
    public void curtirMusica(int idUsuario, int idMusica) throws SQLException {
        removerDescurtida(idUsuario, idMusica);

        String sql = "INSERT INTO curtidas (id_usuario, id_musica) VALUES (?, ?) ON CONFLICT (id_usuario, id_musica) DO NOTHING";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idMusica);
            stmt.executeUpdate();
        }
    }
     
    public void removerCurtida(int idUsuario, int idMusica) throws SQLException {
        String sql = "DELETE FROM curtidas WHERE id_usuario = ? AND id_musica = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idMusica);
            stmt.executeUpdate();
        }
    }
    
    public void descurtirMusica(int idUsuario, int idMusica) throws SQLException {
        removerCurtida(idUsuario, idMusica);

        String sql = "INSERT INTO descurtidas (id_usuario, id_musica) VALUES (?, ?) ON CONFLICT (id_usuario, id_musica) DO NOTHING";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idMusica);
            stmt.executeUpdate();
        }
    }
    
    public void removerDescurtida(int idUsuario, int idMusica) throws SQLException {
        String sql = "DELETE FROM descurtidas WHERE id_usuario = ? AND id_musica = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idMusica);
            stmt.executeUpdate();
        }
    }
    
    public boolean isMusicaCurtida(int idUsuario, int idMusica) throws SQLException {
        String sql = "SELECT COUNT(*) FROM curtidas WHERE id_usuario = ? AND id_musica = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idMusica);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    public boolean isMusicaDescurtida(int idUsuario, int idMusica) throws SQLException {
        String sql = "SELECT COUNT(*) FROM descurtidas WHERE id_usuario = ? AND id_musica = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idMusica);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    public List<Musica> listarMusicasCurtidas(int idUsuario) throws SQLException {
        List<Musica> musicasCurtidas = new ArrayList<>();
        String sql = "SELECT m.id_musica, m.nome_musica, m.autor_musica, m.genero_musica " +
                    "FROM musicas m " +
                    "JOIN curtidas c ON m.id_musica = c.id_musica " +
                    "WHERE c.id_usuario = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_musica");
                    String nome = rs.getString("nome_musica");
                    String autor = rs.getString("autor_musica");
                    String genero = rs.getString("genero_musica");
                    musicasCurtidas.add(new Musica(id, nome, autor, genero));
                }
            }
        }
        return musicasCurtidas;
    }
    
    public List<Musica> listarMusicasDescurtidas(int idUsuario) throws SQLException {
        List<Musica> musicasDescurtidas = new ArrayList<>();
        String sql = "SELECT m.id_musica, m.nome_musica, m.autor_musica, m.genero_musica " +
                     "FROM musicas m " +
                     "JOIN descurtidas d ON m.id_musica = d.id_musica " +
                     "WHERE d.id_usuario = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_musica");
                    String nome = rs.getString("nome_musica");
                    String autor = rs.getString("autor_musica");
                    String genero = rs.getString("genero_musica");
                    musicasDescurtidas.add(new Musica(id, nome, autor, genero));
                }
            }
        }
        return musicasDescurtidas;
    }
}
