package pl.coderslab.User.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.User.UserRole;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private Long id;
	
	private String login;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private UserRole userRole;

}
