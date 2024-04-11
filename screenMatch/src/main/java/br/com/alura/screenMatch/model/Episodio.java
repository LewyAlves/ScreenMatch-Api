package br.com.alura.screenMatch.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Episodio {
    public Episodio(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer temporada;
    private Integer numero;
    private String titulo;
    private Double avaliacao;
    private LocalDate dataLancamento;
    @ManyToOne
    private Serie serie;


    public Episodio(Integer temporada, DadosEpisodio d) {
        this.temporada = temporada;
        this.numero = Integer.valueOf(d.episodio());
        try {
            this.avaliacao = Double.valueOf(d.avaliacao());
        } catch (NumberFormatException ex){
            avaliacao = 0.0;
        }
        this.titulo = d.titulo();
        try {
            dataLancamento = LocalDate.parse(d.dataLancamento());
        } catch (DateTimeException e){
            dataLancamento = null;
        }
    }


    @Override
    public String toString() {
        return "temporada= " + temporada +
                ", titulo= " + titulo +
                ", numeroEpisodio= " + numero +
                ", avaliacao= " + avaliacao +
                ", dataLancamento= " + dataLancamento;
    }
}