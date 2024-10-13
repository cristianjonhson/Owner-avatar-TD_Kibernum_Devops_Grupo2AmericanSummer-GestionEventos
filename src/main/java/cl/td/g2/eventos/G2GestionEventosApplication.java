package cl.td.g2.eventos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackages = "cl.td.g2.eventos")
public class G2GestionEventosApplication {

	public static void main(String[] args) {
		System.out.println("prueba webhook");
		SpringApplication.run(G2GestionEventosApplication.class, args);
	}
}
