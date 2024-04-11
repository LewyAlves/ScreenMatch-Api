package br.com.alura.screenMatch.controller;

import br.com.alura.screenMatch.dto.EpisodioDto;
import br.com.alura.screenMatch.dto.SerieDto;
import br.com.alura.screenMatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public  SerieDto obterPorId(@PathVariable Long id){
        return service.obterPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDto> listarTemporadas(@PathVariable Long id){
        return service.listarTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{temporada}")
    public List<EpisodioDto> EpisodioPorTemporada(@PathVariable Long id, @PathVariable Long temporada){
        return service.EpisodiosPorTemporada(id, temporada);

    }

    @GetMapping("/categoria/{generoSerie}")
    public List<SerieDto> SeriesPorCategoria(@PathVariable  String generoSerie){
        return service.seriePorCategoria(generoSerie);
    }

    @GetMapping("/{id}/temporadas/top")
    public List<EpisodioDto> TopEpisodios(@PathVariable Long id){
        return service.top5episodios(id);
    }
}
