package pl.coderslab.Task.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.Priority.Service.PriorityService;
import pl.coderslab.Priority.dto.PriorityDto;
import pl.coderslab.Project.Service.ProjectService;
import pl.coderslab.Status.Service.StatusService;
import pl.coderslab.Status.dto.StatusDto;
import pl.coderslab.Task.Service.TaskService;
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
	
	
	

	// <--------------------------------ModelAttributes---------------------------------->
	
	
	@ModelAttribute("priorities")
	private List<PriorityDto> getPriorities(){
		return priorityService.getAll();
	}
	
	@ModelAttribute("statuses")
	private List<StatusDto> getStatuses(){
		return statusService.getAll();
	}

}
