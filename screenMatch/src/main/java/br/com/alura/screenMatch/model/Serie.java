package br.com.alura.screenMatch.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
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
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios = new ArrayList<>();

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

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
    }

    @Override
    public String toString() {
        return
                "titulo= '" + titulo + '\'' +
                ", genero= " + genero +
                ", anosDeProducao= '" + anosDeProducao + '\'' +
                ", totalTemporadas= " + totalTemporadas +
                ", avaliacoes= " + avaliacoes +
                ", atores= '" + atores + '\'' +
                ", poster= '" + poster + '\'' +
                ", sinopse= '" + sinopse + '\'' +
                ", episodios= '" + episodios + '\'';

    }
}
