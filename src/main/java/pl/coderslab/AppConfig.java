package pl.coderslab;
import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import pl.coderslab.Converter.UserConverter;

@Configuration
public class AppConfig {

	@Bean
	public UserConverter getUserConverter() {
		return new UserConverter();
	}

//	@Bean
//	public Validator validator() {
//		return new LocalValidatorFactoryBean();
//	}

}
