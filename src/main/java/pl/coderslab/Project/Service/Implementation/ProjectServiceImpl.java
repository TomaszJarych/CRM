package pl.coderslab.Project.Service.Implementation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.Project.Repository.ProjectRepository;
import pl.coderslab.Project.Service.ProjectService;
import pl.coderslab.Project.domain.Project;
import pl.coderslab.Project.dto.ProjectDto;
import pl.coderslab.User.Repository.UserRepository;
import pl.coderslab.User.Service.UserService;
import pl.coderslab.User.Service.Implementation.UserServiceImpl;

@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;
	private final UserRepository userRepository;
	private UserService userService;

	@Autowired
	public ProjectServiceImpl(ProjectRepository projectRepository,
			UserRepository userRepository) {
		this.projectRepository = projectRepository;
		this.userRepository = userRepository;
	}

	// wstrzyknięcie poprzez setter - aby uniknąć zapętlenia
	@Autowired
	public void setUserServiceImp(UserService userService) {
		this.userService = userService;
	}

	@Override
	public ProjectDto findById(Long id) {
		return toDto(projectRepository.getOne(id));
	}

	@Override
	public ProjectDto save(ProjectDto dto) {
		return toDto(projectRepository.save(toEntityProject(dto)));
	}

	@Override
	public void deleteFromDb(Long id) {
		projectRepository.deleteById(id);
	}

	@Override
	public List<ProjectDto> getAll() {
		return toDtoList(projectRepository.findAll());
	}

	private ProjectDto toDto(Project project) {
		ProjectDto dto = new ProjectDto();
		dto.setId(dto.getId());
		dto.setName(project.getName());
		dto.setDescription(project.getDescription());
		dto.setCreated(project.getCreated());
		dto.setIdentifier(project.getIdentifier());
		dto.setIsActive(project.getIsActive());

		if (Objects.nonNull(project.getUsers()) && !project.getUsers().isEmpty()) {
			project.getUsers().stream()
					.map(userService::toSimpleDto)
					.forEach(el -> dto.getUsers().add(el));;
		}

		return dto;
	}

	public ProjectDto toSimpleDto(Project project) {
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

	private Project toEntityProject(ProjectDto dto) {
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

	private List<ProjectDto> toDtoList(List<Project> list) {
		return list.stream().filter(Objects::nonNull).map(this::toDto)
				.collect(Collectors.toList());
	}

}
