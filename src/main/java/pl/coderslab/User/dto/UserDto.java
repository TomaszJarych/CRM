package pl.coderslab.User.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.Commons.Utils.UserRole;
import pl.coderslab.Project.domain.Project;

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

	private Set<Project> projects = new HashSet<>();
}
