package br.com.alura.screenMatch.dto;

import br.com.alura.screenMatch.model.Categoria;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record SerieDto( String titulo,
         String anosDeProducao,
         Integer totalTemporadas,
         Double avaliacoes,
         Categoria genero,
         String atores,
         String poster,
         String sinopse) {
}
