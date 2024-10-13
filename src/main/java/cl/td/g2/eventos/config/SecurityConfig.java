package cl.td.g2.eventos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/login", "/public/**").permitAll() // Permitir rutas públicas
                .anyRequest().authenticated() // Todas las demás necesitan autenticación
            )
            .formLogin((form) -> form
                .loginPage("/login") // Página de login personalizada
                .permitAll() // Permitir acceso a todos al login
            )
            .logout((logout) -> logout.permitAll());

        return http.build();
    }

    // Configuración de usuarios en memoria (sin encriptación de contraseñas)
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
            .password("{noop}password") // Sin encriptación (usando {noop})
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }
}
