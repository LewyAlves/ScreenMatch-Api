package br.com.alura.screenMatch.principal;

import br.com.alura.screenMatch.model.DadosEpisodio;
import br.com.alura.screenMatch.model.DadosSerie;
import br.com.alura.screenMatch.model.DadosTemporada;
import br.com.alura.screenMatch.model.Episodio;
import br.com.alura.screenMatch.service.ConsumoApi;
import br.com.alura.screenMatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

        temporadas.forEach(
                t -> t.episodios()
                        .forEach(e -> System.out.println("Ep: " + e.episodio() + " Titulo: " + e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\n Top 5 episodios: ");
        dadosEpisodios.stream()
                .filter(e -> !e.episodio().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.temporada(), d))
                        .limit(5)).collect(Collectors.toList());
        episodios.forEach(System.out::println);

        System.out.println("\n");

        System.out.println("Você deseja ver os melhores episodios a partir de qual temporada?");
        var temp = reader.nextInt();
        reader.nextLine();
        episodios.stream()
                .filter(e -> e.getTemporada() != null && e.getTemporada() >= temp)
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                                ", Titulo: " + e.getTitulo() +
                                ", Episodio: " + e.getNumero() +
                                ", Lançado em: " + e.getDataLancamento()
                ));


//        System.out.println("Você deseja ver os melhores episodios a partir de qual data de lançamento? ");
//        var ano = reader.nextInt();
//        reader.nextLine();
//
//        LocalDate escolhaDoAno = LocalDate.of(ano, 1,1);
//        DateTimeFormatter formatacaoDeData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodios.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(escolhaDoAno))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                        ", Titulo: " + e.getTitulo() +
//                        ", Lançado em: " + e.getDataLancamento().format(formatacaoDeData)
//                        ));
    }
}
