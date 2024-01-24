package br.com.alura.screenMatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(
    @JsonAlias("Title")
    String titulo,
    @JsonAlias("Season")
    Integer temporada,
    @JsonAlias("Episode")
    String episodio,
    @JsonAlias("imdbRating")
    String avaliacao
) {
}
