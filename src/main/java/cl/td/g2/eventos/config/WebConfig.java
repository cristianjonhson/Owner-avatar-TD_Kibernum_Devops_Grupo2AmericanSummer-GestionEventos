package cl.td.g2.eventos.config;

import java.time.Duration;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;


@Configuration
public class WebConfig {
 
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(auth ->
        	auth.requestMatchers("/static/**", "/js/**", "/css/**", "/webjars/**").permitAll().anyRequest().permitAll());
     
        return http.build();
		
	    /*http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
	    		request -> request
	                            .requestMatchers("/css/**")
	                            .permitAll()
	                            .anyRequest().permitAll());
	    return http.build();*/
		
		/*http.csrf(AbstractHttpConfigurer::disable)
		        .authorizeHttpRequests(auth -> {
		            auth.requestMatchers("/css/**").permitAll();
		            auth.anyRequest().authenticated();
		        })
		        .formLogin(form ->form
		                .loginPage("/login")
		                .permitAll()
		        );
		return http.build();*/
		
		/*return http.csrf().disable()
                .authorizeHttpRequests().
                       requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                .build();*/
	}
 
	/*@Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
         
        //http.authenticationProvider(authenticationProvider());
         
        http.authorizeHttpRequests(auth ->
            auth.requestMatchers("/images/**", "/js/**", "/css/**", "/webjars/**").permitAll().anyRequest().authenticated()));
         
        return http.build();
    }*/
     
 
    /*@Bean
    WebSecurityCustomizer configureWebSecurity() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/css/**", "/webjars/**");
    }*/
}


/*@Configuration
public class WebConfig implements WebMvcConfigurer { 
  
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) { 
        registry.addResourceHandler("/static/**") 
                .addResourceLocations("classpath:/static/") 
                .setCachePeriod(3600) 
                .resourceChain(true) 
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**")); 
    } 
}*/

/*@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/public", "classpath:/static/")
				.setCacheControl(CacheControl.maxAge(Duration.ofDays(365)));
	}
}*/

//@Configuration
//@EnableWebMvc
/*public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/static/")
				.resourceChain(true)
				.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
	}
}*/

