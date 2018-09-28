package pl.coderslab.User.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import pl.coderslab.Commons.Utils.UserRole;
import pl.coderslab.Commons.Validator.UniqueLogin;
import pl.coderslab.Project.dto.ProjectDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

	private Long id;

	@NotBlank
	@UniqueLogin
	private String login;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	private String password;

	@NonNull
	private UserRole userRole;

	private Set<ProjectDto> projects = new HashSet<>();
	
	public String getFullName() {
		return this.firstName+" "+this.lastName;
	}
	
}
