package cl.td.g2.eventos.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHomePage() {
    	return "layout/home"; // Correcto, Thymeleaf buscará en src/main/resources/templates/layout/home.html
    }

    @GetMapping("/about")
    public String getAboutPage() {
    	return "layout/about"; // Correcto, Thymeleaf buscará en src/main/resources/templates/layout/about.html
    }

    @GetMapping("/terms")
    public String getTermsPage() {
    	return "layout/terms"; // Correcto, Thymeleaf buscará en src/main/resources/templates/layout/terms.html
    }

    @GetMapping("/privacy")
    public String getPrivacyPage() {
    	return "layout/privacy"; // Correcto, Thymeleaf buscará en src/main/resources/templates/layout/privacy.html
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
