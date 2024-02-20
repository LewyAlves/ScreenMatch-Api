package br.com.alura.screenMatch.principal;

import br.com.alura.screenMatch.model.*;
import br.com.alura.screenMatch.service.ConsumoApi;
import br.com.alura.screenMatch.service.ConverteDados;

import java.util.*;


public class Principal {
    Scanner reader = new Scanner(System.in);
    ConsumoApi consumo = new ConsumoApi();
    ConverteDados conversor = new ConverteDados();
    private  final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=ce0a4b22";

    public void menu() {
        var opcao = -1;
        while (opcao != 0) {

            var menu = """
                1 - Buscar séries
                2 - Buscar episódios
                
                0 - Sair                                 
                """;

            System.out.println(menu);
            opcao = reader.nextInt();
            reader.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = reader.nextLine();
        var json = consumo.ObeterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie(){
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumo.ObeterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }
}
