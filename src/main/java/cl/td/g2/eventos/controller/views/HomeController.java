package cl.td.g2.eventos.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "layout/home"; // Correcto, Thymeleaf buscará en src/main/resources/templates/layout/home.html
    }
}
