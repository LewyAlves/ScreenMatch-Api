package br.com.alura.screenMatch.controller;

import br.com.alura.screenMatch.Repositorio.SeriesRepository;
import br.com.alura.screenMatch.dto.SerieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SeriesController {

    @Autowired
    private SeriesRepository repository;

    @GetMapping("/series")
    public List<SerieDto> RetornaSeries(){

        return repository.findAll()
                .stream()
                .map(s -> (new SerieDto(s.getTitulo(),s.getAnosDeProducao(), s.getTotalTemporadas(),s.getAvaliacoes(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse())))
                .collect(Collectors.toList());
    }
}
