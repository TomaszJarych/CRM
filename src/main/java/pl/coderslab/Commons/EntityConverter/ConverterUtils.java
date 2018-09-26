package pl.coderslab.Commons.EntityConverter;

import java.util.Objects;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.coderslab.Priority.Repository.PriorityRepository;
import pl.coderslab.Priority.domain.Priority;
import pl.coderslab.Priority.dto.PriorityDto;
import pl.coderslab.Project.Repository.ProjectRepository;
import pl.coderslab.Project.domain.Project;
import pl.coderslab.Project.dto.ProjectDto;
import pl.coderslab.Status.Repository.StatusRepository;
import pl.coderslab.Status.domain.Status;
import pl.coderslab.Status.dto.StatusDto;
import pl.coderslab.User.Repository.UserRepository;
import pl.coderslab.User.domain.User;
import pl.coderslab.User.dto.UserDto;

@Component
public class ConverterUtils {

	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	private final StatusRepository statusRepository;
	private final PriorityRepository priorityRepository;

	@Autowired
	public ConverterUtils(UserRepository userRepository,
			ProjectRepository projectRepository,
			StatusRepository statusRepository,
			PriorityRepository priorityRepository) {
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
		this.statusRepository = statusRepository;
		this.priorityRepository = priorityRepository;
	}

	public UserDto toSimpleUserDto(User user) {
		UserDto dto = new UserDto();

		dto.setId(user.getId());
		dto.setLogin(user.getLogin());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setUserRole(user.getUserRole());

		return dto;
	}
	public UserDto toUserDto(User user) {
		UserDto dto = new UserDto();

		dto.setId(user.getId());
		dto.setLogin(user.getLogin());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setUserRole(user.getUserRole());
		if (Objects.nonNull(user.getProjects())
				&& !user.getProjects().isEmpty()) {
			dto.getProjects().clear();
			user.getProjects().stream().map(this::toSimpleProjectDto)
					.forEach(el -> dto.getProjects().add(el));
		}

		return dto;
	}

	public User toUserEntity(UserDto dto) {
		User user = new User();

		user.setId(user.getId());
		user.setLogin(user.getLogin());
		user.setFirstName(user.getFirstName());
		user.setLastName(user.getLastName());
		if (dto.getPassword() != null && dto.getPassword() != "") {
			user.setPassword(
					BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
		}
		user.setUserRole(user.getUserRole());

		if (Objects.nonNull(user.getProjects())
				&& !user.getProjects().isEmpty()) {
			user.getProjects().clear();
			dto.getProjects().stream().forEach(el -> user.getProjects()
					.add(projectRepository.getOne(el.getId())));
		}

		return user;
	}

	public ProjectDto toProjectDto(Project project) {
		ProjectDto dto = new ProjectDto();
		dto.setId(project.getId());
		dto.setName(project.getName());
		dto.setDescription(project.getDescription());
		dto.setCreated(project.getCreated());
		dto.setIdentifier(project.getIdentifier());
		dto.setIsActive(project.getIsActive());

		if (Objects.nonNull(project.getUsers())
				&& !project.getUsers().isEmpty()) {
			project.getUsers().stream().map(this::toSimpleUserDto)
					.forEach(el -> dto.getUsers().add(el));;
		}

		return dto;
	}

	public ProjectDto toSimpleProjectDto(Project project) {
		ProjectDto dto = new ProjectDto();

		dto.setId(project.getId());
		dto.setName(project.getName());
		dto.setDescription(project.getDescription());
		dto.setCreated(project.getCreated());
		dto.setIdentifier(project.getIdentifier());
		dto.setIsActive(project.getIsActive());
		dto.setWebsite(project.getWebsite());

		return dto;
	}

	private String createIdentifier(String name) {
		String identifier = name.toLowerCase().trim().replaceAll("ó", "o")
				.replaceAll("ł", "l").replaceAll("ą", "a").replaceAll("ż", "z")
				.replaceAll("ę", "e").replaceAll("ź", "z").replaceAll("ń", "n")
				.replaceAll("ć", "c").replaceAll("\\s", "-");

		return identifier;

	}

	public Project toEntityProject(ProjectDto dto) {
		Project project;
		if (dto.getId() == null) {
			project = new Project();
		} else {
			project = projectRepository.getOne(dto.getId());
		}

		project.setId(dto.getId());
		project.setName(dto.getName());
		project.setDescription(dto.getDescription());
		project.setCreated(dto.getCreated());
		project.setIdentifier(createIdentifier(dto.getName()));
		project.setIsActive(dto.getIsActive());
		project.setWebsite(dto.getWebsite());

		if (Objects.nonNull(dto.getUsers()) && !dto.getUsers().isEmpty()) {
			dto.getUsers().stream().map(el -> userRepository.getOne(el.getId()))
					.forEach(el -> project.getUsers().add(el));

		}
		return project;
	}

	public PriorityDto toPriorityDto(Priority priority) {
		PriorityDto dto = new PriorityDto();

		dto.setId(priority.getId());
		dto.setName(priority.getName());
		dto.setIsActive(priority.getIsActive());

		return dto;
	}

	public Priority toPriorityEntity(PriorityDto dto) {
		Priority priority = new Priority();

		priority.setId(dto.getId());
		priority.setName(dto.getName());
		priority.setIsActive(dto.getIsActive());

		return priority;
	}

	public StatusDto toStatusDto(Status status) {
		StatusDto dto = new StatusDto();

		dto.setId(status.getId());
		dto.setName(status.getName());
		dto.setIsActive(status.getIsActive());
		dto.setSortingOrderNumber(status.getSortingOrderNumber());

		return dto;
	}

	public Status toStatusEntity(StatusDto dto) {
		Status status = new Status();

		status.setId(dto.getId());
		status.setName(dto.getName());
		status.setIsActive(dto.getIsActive());
		status.setSortingOrderNumber(dto.getSortingOrderNumber());

		return status;
	}

}
