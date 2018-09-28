package pl.coderslab.HomeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.coderslab.Activity.Service.ActivityService;
import pl.coderslab.Priority.Service.PriorityService;
import pl.coderslab.Project.Service.ProjectService;
import pl.coderslab.Status.Service.StatusService;
import pl.coderslab.Task.Service.TaskService;
import pl.coderslab.User.Service.UserService;
import pl.coderslab.User.dto.UserDto;

@Controller
@RequestMapping("/index")
@SessionAttributes("loggedUser")
public class HomePageController {
	
	
	private final UserService userService;
	private final ProjectService projectService;
	private final TaskService taskService;
	private final PriorityService priorityService;
	private final StatusService statusService;
	private final ActivityService activityService;
	
	@Autowired
	public HomePageController(UserService userService,
			ProjectService projectService, TaskService taskService,
			PriorityService priorityService, StatusService statusService,
			ActivityService activityService) {
		this.userService = userService;
		this.projectService = projectService;
		this.taskService = taskService;
		this.priorityService = priorityService;
		this.statusService = statusService;
		this.activityService = activityService;
	}
	
	@GetMapping
	public String getIdexPage(@SessionAttribute("loggedUser")UserDto dto,Model model) {
		if (dto.getId() == null) {
			return "redirect:/";
		}
		model.addAttribute("projects", projectService.findFirst5ByOrderByCreatedDesc());
		model.addAttribute("activities", activityService.findFirst25ByOrderByCreatedDesc());
		return "index";
	}
	

}
