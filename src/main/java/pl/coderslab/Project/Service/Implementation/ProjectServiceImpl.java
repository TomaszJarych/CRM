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
import pl.coderslab.User.Service.Implementation.UserServiceImpl;

@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;
	private final UserRepository userRepository;
	private final UserServiceImpl userService;
	
	//zmienić importy aby uniknąć błędu nieprzerwanego cyklu!

	@Autowired
	public ProjectServiceImpl(ProjectRepository projectRepository,
			UserRepository userRepository, UserServiceImpl userService) {
		this.projectRepository = projectRepository;
		this.userRepository = userRepository;
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
		dto.setIdentifier(createIdentifier(project.getIdentifier()));
		dto.setIsActive(project.getIsActive());

		if (Objects.nonNull(dto.getUsers()) && !dto.getUsers().isEmpty()) {
			project.getUsers().stream().filter(Objects::nonNull)
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
		dto.setIdentifier(createIdentifier(project.getIdentifier()));
		dto.setIsActive(project.getIsActive());

		return dto;
	}

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
		project.setIdentifier(createIdentifier(dto.getIdentifier()));
		project.setIsActive(dto.getIsActive());

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
