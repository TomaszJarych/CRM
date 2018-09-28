package pl.coderslab.Commons.Validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import pl.coderslab.User.Repository.UserRepository;
import pl.coderslab.User.domain.User;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String>{
	
	@Autowired
	private UserRepository repo;

	
	
	@Override
	public void initialize(UniqueLogin constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		User user = repo.findUserByLogin(value); 
		return Objects.isNull(user);
	}

	
}
