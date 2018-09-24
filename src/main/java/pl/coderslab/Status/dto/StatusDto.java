package pl.coderslab.Status.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto {
	
	
	private Long id;
	
	@NotBlank
	private String name;
	
	private Boolean isActive;
	
	@Min(0)
	private Long sortingOrderNumber;
	
	

}
