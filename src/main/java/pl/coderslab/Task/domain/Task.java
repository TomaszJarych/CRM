package pl.coderslab.Task.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.Priority.domain.Priority;
import pl.coderslab.Project.domain.Project;
import pl.coderslab.Status.domain.Status;
import pl.coderslab.User.domain.User;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="tasks")
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String topic;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	private String description;

	private LocalDateTime created = LocalDateTime.now();

	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "priority_id")
	private Priority priority;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

}
