package pl.coderslab.Priority.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriorityDto {

	private Long id;

	@NotBlank
	private String name;
	
	private Boolean isActive;
	
	
}
