package pl.coderslab.Project.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.User.dto.UserDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {

	private Long id;
	
	@NotBlank
	private String name;
	
	private LocalDateTime created = LocalDateTime.now();
	
	private String description;
	
	private String website;
	
	private	String identifier;
	
	private Set<UserDto> users = new HashSet<>();
	
	private Boolean isActive;
	
}
