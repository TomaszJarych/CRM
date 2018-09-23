package pl.coderslab.Project.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.User.domain.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="project")
public class Project {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	private LocalDateTime created = LocalDateTime.now();

	private String description;

	private String website;

	private String identifier;

	@ManyToMany()
	private Set<User> users = new HashSet<>();

	private Boolean isActive;

	@Transient // przenieść do serwisu
	private String createIdentifier(String name) {
		String identifier = name.toLowerCase();
		identifier.replaceAll("ó", "o");
		identifier.replaceAll("ł", "l");
		identifier.replaceAll("ą", "a");
		identifier.replaceAll("ż", "z");
		identifier.replaceAll("ę", "e");
		identifier.replaceAll("ź", "z");
		identifier.replaceAll("ń", "n");
		identifier.replaceAll("ć", "c");
		
		return identifier;

	}
}
