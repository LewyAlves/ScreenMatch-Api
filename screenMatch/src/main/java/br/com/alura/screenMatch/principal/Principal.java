package br.com.alura.screenMatch.principal;

import br.com.alura.screenMatch.Repositorio.SeriesRepository;
import br.com.alura.screenMatch.model.*;
import br.com.alura.screenMatch.service.ConsumoApi;
import br.com.alura.screenMatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;


public class Principal {
    Scanner reader = new Scanner(System.in);
    ConsumoApi consumo = new ConsumoApi();
    ConverteDados conversor = new ConverteDados();
    private  final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=ce0a4b22";
    private List<DadosSerie> dadosSeries = new ArrayList<>();

    private SeriesRepository seriesRepository;
    private List<Serie> series = new ArrayList<>();

    public Principal(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public void menu() {
        var opcao = -1;
        while (opcao != 0) {

            var menu = """
                1 - Buscar séries
                2 - Buscar episódios
                3 - Listar series buscadas
                4 - Pesquisar por serie
                5 - Pesquisa serie por ator
                6 - Top 5
                
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
                case 3:
                    listaSeries();
                    break;
                case 4:
                    pesquisarPorSerie();
                    break;
                case 5:
                    pesquisaSeriePorAtor();
                    break;
                case 6:
                    topSeries();
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
        Serie serie = new Serie(dados);
        seriesRepository.save(serie);
        dadosSeries.add(dados);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = reader.nextLine();
        var json = consumo.ObeterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie() {
        listaSeries();
        System.out.println("Digite a serie que deseja");
        var nomeSerie = reader.nextLine();

        Optional<Serie> serie = seriesRepository.findByTituloContainingIgnoreCase(nomeSerie);

        if (serie.isPresent()) {
            var serieEncontrada = serie.get();
            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumo.ObeterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.temporada(), e)))
                    .collect(Collectors.toList());

            episodios.stream().forEach(System.out::println);
            serieEncontrada.setEpisodios(episodios);
            seriesRepository.save(serieEncontrada);
        } else {
            System.out.println("Serie não econtrada");
        }
    }

    private void listaSeries(){
        series = seriesRepository.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }


    private void pesquisarPorSerie() {
        System.out.println("Digite a serie que deseja");
        var seriePesquisada = reader.nextLine();

        Optional<Serie> serie = seriesRepository.findByTituloContainingIgnoreCase(seriePesquisada);

        if (serie.isPresent()){
            System.out.println("Series encontradas: ");
            System.out.println(serie);
        } else {
            System.out.println("Serie não encontrada");
        }
    }
    private void pesquisaSeriePorAtor(){
        System.out.println("Digite o ator que deseja");
        var atorPesquisado = reader.nextLine();
        System.out.println("Deseja ver series do ator a partir de qual nota?");
        var notaSerie = reader.nextDouble();
        List<Serie> series1 = seriesRepository.findByAtoresContainingIgnoreCaseAndAvaliacoesGreaterThanEqual(atorPesquisado, notaSerie);
        series1.forEach(s -> System.out.println(
                "Serie: " + s.getTitulo() + ", Avaliação: " + s.getAvaliacoes()
        ));
    }

    private void topSeries() {
        List<Serie> top5 = seriesRepository.findTop5ByOrderByAvaliacoesDesc();
        top5.forEach(s -> System.out.println(
                "Serie: " + s.getTitulo() + ", Avaliação: " + s.getAvaliacoes()
        ));
    }
}
