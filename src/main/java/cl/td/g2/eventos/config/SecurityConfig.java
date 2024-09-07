package cl.td.g2.eventos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	/*http.authorizeHttpRequests(request -> request
        		.requestMatchers("/", "/styles/*", "/home-tmp", "/home", "/login").permitAll()
        		.requestMatchers("/categorias/**", "/ciudades/**", "/eventos/**", "/inscripciones/**", "/usuarios/**").permitAll()
        		.anyRequest().authenticated())*/
        	/*.formLogin(form -> form
        		.loginPage("/login")
        		.defaultSuccessUrl("/eventos", true)
        		.permitAll())
        	.logout(LogoutConfigurer::permitAll)*///;
    	http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(request -> request.anyRequest().permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }*/
}
