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
        Integer totalTemoradas,
        @JsonAlias("imdbRating")
        String avaliacoes
) {
}
