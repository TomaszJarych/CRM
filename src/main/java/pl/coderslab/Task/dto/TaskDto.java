package pl.coderslab.Task.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.Priority.dto.PriorityDto;
import pl.coderslab.Project.dto.ProjectDto;
import pl.coderslab.Status.dto.StatusDto;
import pl.coderslab.User.dto.UserDto;

@Getter
@Setter
@NoArgsConstructor
public class TaskDto {

	private Long id;

	@NotBlank
	private String topic;

	private ProjectDto project;

	@NotBlank
	private String description;

	private LocalDateTime created = LocalDateTime.now();

	private StatusDto status;

	private PriorityDto priority;

	private UserDto user;

}
