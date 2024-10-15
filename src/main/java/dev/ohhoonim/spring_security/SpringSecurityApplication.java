package dev.ohhoonim.spring_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authorization.method.PrePostTemplateDefaults;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
@EnableMethodSecurity
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Bean
	PrePostTemplateDefaults prePostTemplateDefaults() {
		return new PrePostTemplateDefaults();
	}

	@Bean
	UserDetailsService userDetailsService() {
		UserDetails rob = User.withDefaultPasswordEncoder().username("rob").password("password").roles("USER").build();
		UserDetails josh = User.withDefaultPasswordEncoder().username("josh").password("password").roles("USER").build();
		UserDetails accountant = User.withDefaultPasswordEncoder().username("accountant").password("password").roles("ACCOUNTANT").build();

		return new InMemoryUserDetailsManager(rob, josh, accountant); 
	}

}
