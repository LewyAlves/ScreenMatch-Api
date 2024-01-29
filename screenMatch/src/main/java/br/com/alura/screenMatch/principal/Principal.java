package br.com.alura.screenMatch.principal;

import br.com.alura.screenMatch.model.DadosSerie;
import br.com.alura.screenMatch.model.DadosTemporada;
import br.com.alura.screenMatch.service.ConsumoApi;
import br.com.alura.screenMatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    Scanner reader = new Scanner(System.in);
    ConsumoApi consumo = new ConsumoApi();
    private  final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=ce0a4b22";

    public void menu(){
        System.out.println("Digite a serie que deseja buscar: ");
        var busca = reader.nextLine();
        var endereco = consumo.ObeterDados(ENDERECO + busca.replace(" ", "+") + API_KEY);
        ConverteDados conversor = new ConverteDados();
        DadosSerie dadosSerie = conversor.obterDados(endereco, DadosSerie.class);
        System.out.println("Serie: " + dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas() ; i++) {
            var json = consumo.ObeterDados(ENDERECO + busca.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println("Ep: " + e.episodio() + " Titulo: " + e.titulo())));


    }
}
