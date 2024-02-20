package br.com.alura.screenMatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(
        @JsonAlias("Title")
        String titulo,
        @JsonAlias("Year")
        String anosDeProducao,
        @JsonAlias("totalSeasons")
        Integer totalTemporadas,
        @JsonAlias("imdbRating")
        Double avaliacoes,
        @JsonAlias("Genre")
        String genero,
        @JsonAlias("Actors")
        String atores,
        @JsonAlias("Poster")
        String poster,
        @JsonAlias("Plot")
        String sinopse
) {
}
