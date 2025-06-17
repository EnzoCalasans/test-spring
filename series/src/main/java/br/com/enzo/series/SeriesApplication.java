package br.com.enzo.series;

import br.com.enzo.series.main.Principal;
import br.com.enzo.series.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeriesApplication implements CommandLineRunner {

	@Autowired
	private SerieRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(SeriesApplication.class, args);

	}

	@Override
	public void run(String... args)  throws Exception{
		Principal principal = new Principal(repositorio);
		principal.exibeMenu();
	}
}
