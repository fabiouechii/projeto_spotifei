/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.Usuario;

/**
 *
 * @author Fabio
 */
public class UsuarioDAO {
    private Connection conn;
    
    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet consultar(Usuario usuario) throws SQLException{
        String sql = "select * from usuarios where login_usuario = ? and senha_usuario = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usuario.getLoginUsuario());
        statement.setString(2, usuario.getSenhaUsuario());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    
    public void inserir(Usuario usuario) throws SQLException{
        String sql = "insert into usuarios(nome_usuario, login_usuario, senha_usuario) values ('"
                + usuario.getNomeUsuario() + "' , '"
                + usuario.getLoginUsuario() + "' , '"
                + usuario.getSenhaUsuario() + "')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        //conn.close();
    }
}
