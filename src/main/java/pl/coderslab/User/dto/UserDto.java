package pl.coderslab.User.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.Commons.Utils.UserRole;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

	private Long id;

	@NotBlank
	private String login;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	private String password;

	@NotBlank
	private UserRole userRole;

}
