package br.com.alura.screenMatch.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.OptionalDouble;

@Getter
@Setter
@Entity
public class Serie {
    public Serie(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String anosDeProducao;
    private Integer totalTemporadas;
    private Double avaliacoes;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String atores;
    private String poster;
    private String sinopse;

    public Serie(DadosSerie dados){
        this.titulo = dados.titulo();
        this.anosDeProducao = dados.anosDeProducao();
        this.totalTemporadas = dados.totalTemporadas();
        this.avaliacoes = OptionalDouble.of(Double.valueOf(dados.avaliacoes())).orElse(0);
        this.genero = Categoria.fromString(dados.genero().split(",")[0]);
        this.atores = dados.atores();
        this.poster = dados.poster();
        this.sinopse = dados.sinopse();
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", genero=" + genero +
                ", anosDeProducao='" + anosDeProducao + '\'' +
                ", totalTemporadas=" + totalTemporadas +
                ", avaliacoes=" + avaliacoes +
                ", atores='" + atores + '\'' +
                ", poster='" + poster + '\'' +
                ", sinopse='" + sinopse + '\'';
    }
}
