package com.example.list.model;

public class Livro {

    private static int idCounter = 0;

    private int id;
    private String nome;
    private String editora;
    private String volume;


    public Livro(String nome, String editora, String volume) {

        this.id = idCounter++;

        this.nome = nome;
        this.editora = editora;
        this.volume = volume;

    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Livro: " + nome + "\n" + "Editora: " + editora + "\n" + "Volume: " + volume ;
    }
}
