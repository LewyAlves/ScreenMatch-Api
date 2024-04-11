package br.com.alura.screenMatch.service;

import br.com.alura.screenMatch.Repositorio.SeriesRepository;
import br.com.alura.screenMatch.dto.EpisodioDto;
import br.com.alura.screenMatch.dto.SerieDto;
import br.com.alura.screenMatch.model.Categoria;
import br.com.alura.screenMatch.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SeriesRepository repository;

    public List<SerieDto> TodasSeries(){
        return repository.findAll()
                .stream()
                .map(s -> (new SerieDto(s.getId(),s.getTitulo(),s.getAnosDeProducao(), s.getTotalTemporadas(),s.getAvaliacoes(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse())))
                .collect(Collectors.toList());
    }

    public List<SerieDto> top5() {
        return converteDados(repository.findTop5ByOrderByAvaliacoesDesc());
    }

    public List<SerieDto> converteDados(List<Serie> series){
        return series.stream()
                .map(s -> (new SerieDto(s.getId(),s.getTitulo(),s.getAnosDeProducao(), s.getTotalTemporadas(),s.getAvaliacoes(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse())))
                .collect(Collectors.toList());
    }

    public List<SerieDto> lancamentos() {
        return converteDados(repository.lancamentosMaisRecentes());
    }

    public SerieDto obterPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()){
            Serie s = serie.get();
            return new SerieDto(s.getId(),s.getTitulo(),s.getAnosDeProducao(), s.getTotalTemporadas(),s.getAvaliacoes(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse());
        }
        return null;
    }


    public List<EpisodioDto> listarTemporadas(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()){
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e -> new EpisodioDto(e.getTemporada(),e.getNumero(), e.getTitulo()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDto> EpisodiosPorTemporada(Long id, Long temporada) {
        return repository.episodiosPorTemporada(id, temporada).stream()
                .map(e -> new EpisodioDto(e.getTemporada(), e.getNumero(), e.getTitulo()))
                .collect(Collectors.toList());
    }

    public List<SerieDto> seriePorCategoria(String generoSerie) {
        Categoria categoria = Categoria.fromPortuguese(generoSerie);
        return converteDados(repository.findByGenero(categoria));
    }

    public List<EpisodioDto> top5episodios(Long id) {
        return repository.top5episodios(id).stream()
                .map(e -> new EpisodioDto(e.getTemporada(), e.getNumero(), e.getTitulo()))
                .collect(Collectors.toList());
    }
}
