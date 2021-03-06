package pl.coderslab.Project.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.coderslab.User.dto.UserDto;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDto {

	private Long id;

	@NotBlank
	private String name;

	private LocalDateTime created = LocalDateTime.now();

	private String description;

	@URL
	private String website;

	private String identifier;

	private Set<UserDto> users = new HashSet<>();

	private Boolean isActive;

	public String getIsProjectActive() {
		if (getIsActive() == null) {
			return "No";
		}
		return getIsActive() ? "Yes" : "No";
	}

	public String getFullDate() {

		return this.created
				.format(DateTimeFormatter.ofPattern("d MMM uuuu  HH:mm:ss"));
	}

}
