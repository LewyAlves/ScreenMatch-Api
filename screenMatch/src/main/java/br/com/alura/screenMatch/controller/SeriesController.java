package br.com.alura.screenMatch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeriesController {

    @GetMapping("/series")
    public String RetornaSeries(){
        return "Aqui será a série";
    }
}
