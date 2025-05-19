/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author fabio
 */
public class Musica {
    private int idMusica;
    private String nomeMusica;
    private String autorMusica;
    private String generoMusica;

    public Musica() {    
    }
    
    public Musica(int idMusica, String nomeMusica, String autorMusica, String generoMusica) {
        this.idMusica = idMusica;
        this.nomeMusica = nomeMusica;
        this.autorMusica = autorMusica;
        this.generoMusica = generoMusica;
    }

    public int getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public String getAutorMusica() {
        return autorMusica;
    }

    public void setAutorMusica(String autorMusica) {
        this.autorMusica = autorMusica;
    }

    public String getGeneroMusica() {
        return generoMusica;
    }

    public void setGeneroMusica(String generoMusica) {
        this.generoMusica = generoMusica;
    }
    
    @Override
    public String toString() {
        return "Musica{" +
               "idMusica=" + idMusica +
               ", nomeMusica='" + nomeMusica + '\'' +
               ", autorMusica='" + autorMusica + '\'' +
               ", generoMusica='" + generoMusica + '\'' +
               '}';
    }
}