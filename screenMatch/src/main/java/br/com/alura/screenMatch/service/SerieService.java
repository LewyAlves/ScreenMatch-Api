package br.com.alura.screenMatch.service;

import br.com.alura.screenMatch.Repositorio.SeriesRepository;
import br.com.alura.screenMatch.dto.SerieDto;
import br.com.alura.screenMatch.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SeriesRepository repository;

    public List<SerieDto> TodasSeries(){
        return repository.findAll()
                .stream()
                .map(s -> (new SerieDto(s.getTitulo(),s.getAnosDeProducao(), s.getTotalTemporadas(),s.getAvaliacoes(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse())))
                .collect(Collectors.toList());
    }

    public List<SerieDto> top5() {
        return converteDados(repository.findTop5ByOrderByAvaliacoesDesc());
    }

    public List<SerieDto> converteDados(List<Serie> series){
        return series.stream()
                .map(s -> (new SerieDto(s.getTitulo(),s.getAnosDeProducao(), s.getTotalTemporadas(),s.getAvaliacoes(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse())))
                .collect(Collectors.toList());
    }

    public List<SerieDto> lancamentos() {
        return converteDados(repository.findTop5ByOrderByEpisodiosDataLancamentoDesc());
    }
}
