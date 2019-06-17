package fandradetecinfo.com.moviecollectionapp.Models;

/**
 * Created by Fernando on 03/05/2018.
 */

public class DadosFilme  {

    private String nome;
    private String filmes;


    public DadosFilme() {
    }

    public DadosFilme(String nome, String filmes) {
        super();
        this.nome = nome;
        this.filmes = filmes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFilmes() {
        return filmes;
    }

    public void setFilmes(String filmes) {
        this.filmes = filmes;
    }

}
