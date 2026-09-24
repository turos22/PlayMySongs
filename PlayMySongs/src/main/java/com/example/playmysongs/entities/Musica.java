package com.example.playmysongs.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "songs")
public class Musica {
    private String estilo;
    private String titulo;
    private String artista;
    private String caminho_mp3;

    public Musica(){

    }

    public Musica(String estilo, String titulo, String artista, String caminho_mp3) {
        this.estilo = estilo;
        this.titulo = titulo;
        this.artista = artista;
        this.caminho_mp3 = caminho_mp3;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String nome) {
        this.titulo = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getCaminho_mp3() {
        return caminho_mp3;
    }

    public void setCaminho_mp3(String caminho_mp3) {
        this.caminho_mp3 = caminho_mp3;
    }
}
