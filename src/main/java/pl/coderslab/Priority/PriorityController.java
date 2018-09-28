package pl.coderslab.Priority;

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
import pl.coderslab.Priority.Service.PriorityService;
import pl.coderslab.Priority.dto.PriorityDto;
import pl.coderslab.User.dto.UserDto;

@Controller
@RequestMapping("/priority")
@SessionAttributes("loggedUser")
public class PriorityController {

	private final PriorityService priorityService;

	@Autowired
	public PriorityController(PriorityService priorityService) {
		this.priorityService = priorityService;
	}

	@GetMapping("/all")
	public String getAllPriorities(Model model,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}

		model.addAttribute("priorities", priorityService.getAll());

		return "priority/allPriorities";
	}

	@GetMapping("/add")
	public String addNewPriority(Model model,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}

		model.addAttribute("priority", new PriorityDto());

		return "priority/priorityForm";
	}

	@PostMapping("/add")
	public String processNewPriority(
			@Valid @ModelAttribute("priority") PriorityDto dto,
			BindingResult result,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			return "priority/priorityForm";
		}

		priorityService.save(dto);

		return "redirect:/priority/all";
	}

	@GetMapping("/edit/{id}")
	public String editPriority(@PathVariable("id") Long id, Model model,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}

		model.addAttribute("priority", priorityService.findById(id));

		return "priority/priorityForm";
	}

	@PostMapping("/edit/**")
	public String processEditPriority(
			@Valid @ModelAttribute("priority") PriorityDto dto,
			BindingResult result,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			return "priority/priorityForm";
		}
		priorityService.save(dto);

		return "redirect:/priority/all";
	}

	@GetMapping("/delete/{id}")
	public String deletePriority(@PathVariable("id") Long id,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}

		priorityService.deleteFromDb(id);

		return "redirect:/priority/all";
	}
}
