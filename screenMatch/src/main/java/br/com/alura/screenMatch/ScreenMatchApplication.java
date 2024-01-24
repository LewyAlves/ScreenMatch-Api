package br.com.alura.screenMatch;

import br.com.alura.screenMatch.model.DadosEpisodio;
import br.com.alura.screenMatch.model.DadosSerie;
import br.com.alura.screenMatch.service.ConsumoApi;
import br.com.alura.screenMatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoApi api = new ConsumoApi();
		var endereco = api.ObeterDados("http://www.omdbapi.com/?t=supernatural&season=4&episode=12&apikey=ce0a4b22");
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(endereco, DadosSerie.class);
		System.out.println("Serie: " + dados);
		DadosEpisodio dados2 = conversor.obterDados(endereco, DadosEpisodio.class);
		System.out.println("Episodio: " + dados2);
	}

}
