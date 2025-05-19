package br.com.enzo.series;

import br.com.enzo.series.model.DadosSerie;
import br.com.enzo.series.service.ConsumoAPI;
import br.com.enzo.series.service.ConverteDados;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class SeriesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SeriesApplication.class, args);

	}

	@Override
	public void run(String... args)  throws Exception{
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6926338d");
		System.out.println(json);

		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
	}
}
