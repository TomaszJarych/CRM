package pl.coderslab.Task.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.Priority.Service.PriorityService;
import pl.coderslab.Priority.dto.PriorityDto;
import pl.coderslab.Project.Service.ProjectService;
import pl.coderslab.Status.Service.StatusService;
import pl.coderslab.Status.dto.StatusDto;
import pl.coderslab.Task.Service.TaskService;
import pl.coderslab.Task.dto.TaskDto;
import pl.coderslab.User.Service.UserService;

@Controller
@RequestMapping("/task")
public class TaskController {

	private final TaskService taskService;
	private final ProjectService projectService;
	private final PriorityService priorityService;
	private final StatusService statusService;
	private final UserService userService;

	@Autowired
	public TaskController(TaskService taskService,
			ProjectService projectService, PriorityService priorityService,
			StatusService statusService, UserService userService) {
		this.taskService = taskService;
		this.projectService = projectService;
		this.priorityService = priorityService;
		this.statusService = statusService;
		this.userService = userService;
	}

	// <--------------------------------RequestMappings---------------------------------->

	@GetMapping("/all")
	public String getAllTask(Model model) {

		model.addAttribute("tasks", taskService.getAll());

		return "task/allTasks";
	}

	@GetMapping("/add/{id}")
	public String addNewTask(@PathVariable("id") Long id, Model model) {

		model.addAttribute("project", projectService.findById(id));
		model.addAttribute("task", new TaskDto());

		return "task/taskForm";

	}

	@PostMapping("/add/**")
	public String processNewTask(@Valid @ModelAttribute("task") TaskDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return "task/taskForm";
		}
		taskService.save(dto);
		return "redirect:/task/all";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editTask(@PathVariable("id") Long id, Model model) {
		TaskDto dto = taskService.findById(id);
		
		model.addAttribute("task", dto);
		model.addAttribute("project", projectService.findById(dto.getProject().getId()));


		return "task/taskForm";

	}

	@PostMapping("/edit/**")
	public String processEditTask(@Valid @ModelAttribute("task") TaskDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return "task/taskForm";
		}
		taskService.save(dto);
		return "redirect:/task/all";
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteTask(@PathVariable("id")Long id) {
		
		taskService.deleteFromDb(id);
		
		return "redirect:/task/all";
	}
	
	@GetMapping("/getTaskByProjectId/{id}")
	public String getTaskByProjectId(@PathVariable("id")Long id, Model model) {
		
		model.addAttribute("tasks", taskService.findAllByProjectId(id));

		return "task/allTasks";
	}

	// <--------------------------------ModelAttributes---------------------------------->

	@ModelAttribute("priorities")
	private List<PriorityDto> getPriorities() {
		return priorityService.getAll();
	}

	@ModelAttribute("statuses")
	private List<StatusDto> getStatuses() {
		return statusService.getAll();
	}

}
