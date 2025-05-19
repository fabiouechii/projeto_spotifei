/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author fabio
 */
public class Playlist {
    private int idPlaylist;
    private String nomePlaylist;
    private int idUsuario;

    public Playlist(String nomePlaylist, int idUsuario) {
        this.nomePlaylist = nomePlaylist;
        this.idUsuario = idUsuario;
    }

    public Playlist(int idPlaylist, String nomePlaylist, int idUsuario) {
        this.idPlaylist = idPlaylist;
        this.nomePlaylist = nomePlaylist;
        this.idUsuario = idUsuario;
    }
    
    public int getIdPlaylist() { return idPlaylist; }
    public String getNomePlaylist() { return nomePlaylist; }
    public int getIdUsuario() { return idUsuario; }
    
    public void setIdPlaylist(int idPlaylist) { this.idPlaylist = idPlaylist; }
    public void setNomePlaylist(String nomePlaylist) { this.nomePlaylist = nomePlaylist; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    
    @Override
    public String toString() {
        return nomePlaylist;
    }
}
