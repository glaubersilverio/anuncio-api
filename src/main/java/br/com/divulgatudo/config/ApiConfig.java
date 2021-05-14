package br.com.divulgatudo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {
	
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

}
