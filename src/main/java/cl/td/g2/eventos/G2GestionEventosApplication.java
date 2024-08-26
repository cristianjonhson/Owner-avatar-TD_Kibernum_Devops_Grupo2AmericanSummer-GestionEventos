package cl.td.g2.eventos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class G2GestionEventosApplication {

	public static void main(String[] args) {
		SpringApplication.run(G2GestionEventosApplication.class, args);
	}

}
