package br.com.alura.screenMatch.controller;

import br.com.alura.screenMatch.Repositorio.SeriesRepository;
import br.com.alura.screenMatch.dto.SerieDto;
import br.com.alura.screenMatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private SerieService service;

    @GetMapping
    public List<SerieDto> RetornaSeries(){
        return service.TodasSeries();
    }

    @GetMapping("/top5")
    public List<SerieDto> top5(){
        return service.top5();
    }

    @GetMapping("/lancamentos")
    public List<SerieDto> lancamentos(){
        return service.lancamentos();
    }
}
