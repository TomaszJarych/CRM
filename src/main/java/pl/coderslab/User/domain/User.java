package pl.coderslab.User.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.criterion.Projections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.Commons.Utils.UserRole;
import pl.coderslab.Project.domain.Project;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="app_user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String login;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	
	@ManyToMany(mappedBy="users")
	private Set<Project> projects = new HashSet<>();

}
