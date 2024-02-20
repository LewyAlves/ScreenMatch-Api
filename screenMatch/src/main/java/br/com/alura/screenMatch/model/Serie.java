package br.com.alura.screenMatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Optional;
import java.util.OptionalDouble;

public class Serie {

    private String titulo;
    private String anosDeProducao;
    private Integer totalTemporadas;
    private Double avaliacoes;
    private Categoria genero;
    private String atores;
    private String poster;
    private String sinopse;

    Serie(DadosSerie dados){
        this.titulo = dados.titulo();
        this.anosDeProducao = dados.anosDeProducao();
        this.totalTemporadas = dados.totalTemporadas();
        this.avaliacoes = OptionalDouble.of(Double.valueOf(dados.avaliacoes())).orElse(0);
        this.genero = Categoria.fromString(dados.genero().split(",")[0]);
    }


}
