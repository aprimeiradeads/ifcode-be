package ifsul.ads.hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class HackathonApplication {

	public static void main(String[] args) {
		//Dotenv dotenv = Dotenv.load();

		// Define as variáveis como propriedades do sistema
		//dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		SpringApplication.run(HackathonApplication.class, args);
	}

}
