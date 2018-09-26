package pl.coderslab.Activity.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActivityDto {

	private Long id;
	private String content;
	private LocalDateTime created = LocalDateTime.now();

	public ActivityDto(String content) {
		this.content = content;
	}

}
