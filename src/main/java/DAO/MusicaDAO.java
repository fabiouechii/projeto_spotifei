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
}
