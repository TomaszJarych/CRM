package pl.coderslab.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.Project.Service.ProjectService;
import pl.coderslab.Project.dto.ProjectDto;
import pl.coderslab.User.Service.UserService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	private final ProjectService projectService;
	private final UserService userService;
	
	@Autowired
	public ProjectController(ProjectService projectService,
			UserService userService) {
		this.projectService = projectService;
		this.userService = userService;
	}
	
	@GetMapping("/all")
	public String allProjects(Model model) {
		
		model.addAttribute("projects", projectService.getAll());
		
		return "project/allProjects";
	}
	
	@GetMapping("/add")
	public String addNewProject(Model model) {
		
		model.addAttribute("projects", new ProjectDto());
		
		return "project/***";
	}
}
