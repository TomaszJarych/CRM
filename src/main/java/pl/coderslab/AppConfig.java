package pl.coderslab;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.coderslab.Converter.UserConverter;

@Configuration
public class AppConfig {

	@Bean
	public UserConverter getUserConverter() {
		return new UserConverter();
	}

}
