package cl.td.g2.eventos.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHomePage() {
        return "home"; // Correcto, Thymeleaf buscará en src/main/resources/templates/layout/home.html
    }

    @GetMapping
    public String getDefaultPage() {
    	return "home";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
