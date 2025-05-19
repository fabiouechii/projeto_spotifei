/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.UsuarioDAO;
import DAO.Conexao;
import model.Usuario;
import view.LoginFrame;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import view.MenuFrame;

/**
 *
 * @author Fabio
 */
public class ControllerLogin {
    private LoginFrame view;
    
    public ControllerLogin(LoginFrame view){
        this.view = view;
    }
    
    public void loginUsuario (){
        Usuario usuario = new Usuario(null, view.getTxt_usuarioLogin().getText(), view.getTxt_senhaLogin().getText());
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            UsuarioDAO dao = new UsuarioDAO(conn);
            ResultSet res = dao.consultar(usuario);
            if (res.next()){
                JOptionPane.showMessageDialog(view, "Login Efetuado com Sucesso!");
                
                int idUsuario = res.getInt("id_usuario");
                String nomeUsuarioDB = res.getString("nome_usuario");
                String loginUsuarioDB = res.getString("login_usuario");
                
                Usuario usuarioLogado = new Usuario();
                usuarioLogado.setIdUsuario(idUsuario);
                usuarioLogado.setNomeUsuario(nomeUsuarioDB);
                usuarioLogado.setLoginUsuario(loginUsuarioDB);
                
                MenuFrame menu = new MenuFrame(usuarioLogado);
                menu.setVisible(true);
                view.setVisible(false);
            } else{
                JOptionPane.showMessageDialog(view, "Login não Efetuado!");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // imprime o erro no console
            JOptionPane.showMessageDialog(view, "Erro de Conexão: " + e.getMessage());
        }
    }
}
