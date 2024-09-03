package cl.td.g2.eventos.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHomePage() {
    	return "layout/home"; // Correcto, Thymeleaf buscará en src/main/resources/templates/layout/home.html
    }

    @GetMapping("/home-tmp")
    public String getHomeTmpPage() {
        return "home_tmp";
    }

    @GetMapping
    public String getDefaultPage() {
    	return "layout/home";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
