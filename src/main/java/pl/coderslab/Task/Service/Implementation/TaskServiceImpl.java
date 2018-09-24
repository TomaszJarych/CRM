package pl.coderslab.Task.Service.Implementation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import pl.coderslab.Commons.EntityConverter.ConverterUtils;
import pl.coderslab.Priority.Repository.PriorityRepository;
import pl.coderslab.Project.Repository.ProjectRepository;
import pl.coderslab.Status.Repository.StatusRepository;
import pl.coderslab.Task.Repository.TaskRepository;
import pl.coderslab.Task.Service.TaskService;
import pl.coderslab.Task.domain.Task;
import pl.coderslab.Task.dto.TaskDto;
import pl.coderslab.User.Repository.UserRepository;

public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	private final StatusRepository statusRepository;
	private final PriorityRepository priorityRepository;
	private final ConverterUtils converterUtils;

	public TaskServiceImpl(TaskRepository taskRepository,
			UserRepository userRepository, ProjectRepository projectRepository,
			StatusRepository statusRepository,
			PriorityRepository priorityRepository,
			ConverterUtils converterUtils) {
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
		this.statusRepository = statusRepository;
		this.priorityRepository = priorityRepository;
		this.converterUtils = converterUtils;
	}

	@Override
	public TaskDto findById(Long id) {

		return toTaskDto(taskRepository.getOne(id));
	}

	@Override
	public TaskDto save(TaskDto dto) {
		return toTaskDto(taskRepository.save(toTaskEntity(dto)));
	}

	@Override
	public void deleteFromDb(Long id) {
		taskRepository.deleteById(id);

	}

	@Override
	public List<TaskDto> getAll() {
		return toListTaskDto(taskRepository.findAll());
	}

	private TaskDto toTaskDto(Task task) {
		TaskDto dto = new TaskDto();

		dto.setId(task.getId());
		dto.setTopic(task.getTopic());
		dto.setDescription(task.getDescription());
		dto.setCreated(task.getCreated());

		dto.setProject(converterUtils.toSimpleProjectDto(task.getProject()));
		dto.setUser(converterUtils.toSimpleUserDto(task.getUser()));
		dto.setPriority(converterUtils.toPriorityDto(task.getPriority()));
		dto.setStatus(converterUtils.toStatusDto(task.getStatus()));

		return dto;
	}

	private Task toTaskEntity(TaskDto dto) {
		Task task;
		if (dto.getId() == null) {
			task = new Task();
		} else {
			task = taskRepository.getOne(dto.getId());
		}

		task.setId(dto.getId());
		task.setTopic(dto.getTopic());
		task.setDescription(dto.getDescription());
		task.setCreated(dto.getCreated());

		task.setProject(projectRepository.getOne(dto.getProject().getId()));
		task.setUser(userRepository.getOne(dto.getUser().getId()));
		task.setPriority(priorityRepository.getOne(dto.getPriority().getId()));
		task.setStatus(statusRepository.getOne(dto.getStatus().getId()));

		return task;
	}

	private List<TaskDto> toListTaskDto(List<Task> list) {
		return list.stream().filter(Objects::nonNull).map(this::toTaskDto)
				.collect(Collectors.toList());
	}

}
