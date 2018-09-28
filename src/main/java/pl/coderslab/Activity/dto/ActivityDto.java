package pl.coderslab.Activity.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

	public String getFullDate() {
		return this.created
				.format(DateTimeFormatter.ofPattern("d MMM uuuu  HH:mm:ss"));
	}

}
