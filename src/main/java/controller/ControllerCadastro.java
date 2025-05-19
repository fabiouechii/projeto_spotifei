/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.UsuarioDAO;
import DAO.Conexao;
import model.Usuario;
import view.CadastroFrame;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author fabio
 */
public class ControllerCadastro {
    private CadastroFrame view;
    
    public ControllerCadastro(CadastroFrame view){
        this.view = view;
    }
    
    public void salvarUsuario (){
        String nome = view.getTxt_nomeCadastro().getText();
        String user = view.getTxt_usuarioCadastro().getText();
        String senha = view.getTxt_senhaCadastro().getText();
        Usuario usuario = new Usuario(nome, user, senha);
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            UsuarioDAO dao = new UsuarioDAO(conn);
            dao.inserir(usuario);
            JOptionPane.showMessageDialog(view, "Usuario Cadastrado");
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de Cadastro");
        }
    }
}
