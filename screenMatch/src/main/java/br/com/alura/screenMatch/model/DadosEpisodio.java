package br.com.alura.screenMatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(
        @JsonAlias("Episode")
        String episodio,
        @JsonAlias("Title")
        String titulo,
        @JsonAlias("imdbRating")
        String avaliacao,
        @JsonAlias("Released")
        String dataLancamento
) {
}
