package pl.coderslab.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.User.Service.UserService;
import pl.coderslab.User.dto.UserDto;

public class UserConverter implements Converter<String, UserDto> {
	
	@Autowired
	private UserService service; 

	@Override
	public UserDto convert(String source) {
		return service.findById(Long.valueOf(source));
	}

}
