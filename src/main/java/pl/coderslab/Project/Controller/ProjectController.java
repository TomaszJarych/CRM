package pl.coderslab.Project.Controller;

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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.coderslab.Commons.Utils.UserRole;
import pl.coderslab.Project.Service.ProjectService;
import pl.coderslab.Project.dto.ProjectDto;
import pl.coderslab.User.Service.UserService;
import pl.coderslab.User.dto.UserDto;

@Controller
@RequestMapping("/project")
@SessionAttributes("loggedUser")
public class ProjectController {

	private final ProjectService projectService;
	private final UserService userService;

	@Autowired
	public ProjectController(ProjectService projectService,
			UserService userService) {
		this.projectService = projectService;
		this.userService = userService;
	}

	// <--------------------------------RequestMappings---------------------------------->

	@GetMapping("/all")
	public String allProjects(Model model,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}

		model.addAttribute("projects", projectService.getAll());

		return "project/allProjects";
	}

	@GetMapping("/add")
	public String addNewProject(Model model,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}

		model.addAttribute("project", new ProjectDto());

		return "project/projectForm";
	}

	@PostMapping("/add")
	public String processNewProject(
			@Valid @ModelAttribute("project") ProjectDto dto,
			BindingResult result,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			return "project/projectForm";
		}
		projectService.save(dto);

		return "redirect:/project/all";
	}

	@GetMapping("/details/{id}")
	public String projectDetails(@PathVariable("id") Long id, Model model,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null || loggedUser == null) {
			return "redirect:/";
		}

		model.addAttribute("project", projectService.findById(id));

		return "project/projectDetails";
	}

	@GetMapping("/edit/{id}")
	public String editProject(@PathVariable("id") Long id, Model model,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}

		model.addAttribute("project", projectService.findById(id));

		return "project/projectForm";
	}

	@PostMapping("/edit/**")
	public String processEditProject(
			@Valid @ModelAttribute("project") ProjectDto dto,
			BindingResult result,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			return "project/projectForm";
		}
		projectService.save(dto);

		return "redirect:/project/all";
	}

	@GetMapping("/delete/{id}")
	public String deleteProject(@PathVariable("id") Long id,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}

		projectService.deleteFromDb(id);

		return "redirect:/project/all";
	}

	// <--------------------------------ModelAttributes---------------------------------->

	@ModelAttribute("users")
	private List<UserDto> getUsers() {
		return userService.getAll();
	}

}
