package pl.coderslab.Task.Service.Implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import pl.coderslab.Activity.Observer.Observer;
import pl.coderslab.Activity.Observer.Subject.Observerable;
import pl.coderslab.Commons.EntityConverter.ConverterUtils;
import pl.coderslab.Priority.Repository.PriorityRepository;
import pl.coderslab.Project.Repository.ProjectRepository;
import pl.coderslab.Status.Repository.StatusRepository;
import pl.coderslab.Task.Repository.TaskRepository;
import pl.coderslab.Task.Service.TaskService;
import pl.coderslab.Task.domain.Task;
import pl.coderslab.Task.dto.TaskDto;
import pl.coderslab.User.Repository.UserRepository;

public class TaskServiceImpl implements TaskService, Observerable {

	private final TaskRepository taskRepository;
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	private final StatusRepository statusRepository;
	private final PriorityRepository priorityRepository;
	private final ConverterUtils converterUtils;

	private Set<Observer> observerList = new HashSet<>();
	private final Observer observer;

	public TaskServiceImpl(TaskRepository taskRepository,
			UserRepository userRepository, ProjectRepository projectRepository,
			StatusRepository statusRepository,
			PriorityRepository priorityRepository,
			ConverterUtils converterUtils, Observer observer) {
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
		this.statusRepository = statusRepository;
		this.priorityRepository = priorityRepository;
		this.converterUtils = converterUtils;
		this.observer = observer;
	}

	// <------------------------ Observerable --------------------------------->
	@Override
	public void attatchObserver(Observer observer) {
		this.observerList.add(observer);
	}

	@Override
	public void detatchObserver(Observer observer) {
		this.observerList.remove(observer);

	}
	@Override
	public void notifyObservers(String content) {
		this.observerList.forEach(el -> el.addNewActivity(content));

	}

	@PostConstruct
	public void initObservers() {
		attatchObserver(observer);
	}

	// <------------------------ Observerable --------------------------------->

	@Override
	public TaskDto findById(Long id) {

		return toTaskDto(taskRepository.getOne(id));
	}

	@Override
	public TaskDto save(TaskDto dto) {
		String activity = (dto.getId() == null)
				? "New Task has been saved: " + dto.getTopic()
				: "Task has been updated: " + dto.getTopic();
		notifyObservers(activity);

		return toTaskDto(taskRepository.save(toTaskEntity(dto)));
	}

	@Override
	public void deleteFromDb(Long id) {
		taskRepository.deleteById(id);
		notifyObservers("Task has been deleted");

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
