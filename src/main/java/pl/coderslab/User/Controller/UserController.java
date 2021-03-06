package pl.coderslab.User.Controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.coderslab.Commons.Utils.UserRole;
import pl.coderslab.User.Service.UserService;
import pl.coderslab.User.dto.UserDto;

@Controller
@RequestMapping(path = "/user")
@SessionAttributes("loggedUser")
public class UserController {

	private UserService userService;

	public UserController(UserService service) {
		this.userService = service;
	}

	@GetMapping(path = "/all")
	public String getAllUsers(Model model) {

		model.addAttribute("users", userService.getAll());

		return "user/allUsers";

	}

	@GetMapping(path = "/add")
	public String getUserById(Model model,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}

		model.addAttribute("user", new UserDto());

		return "user/userForm";
	}

	@PostMapping(path = "/add")
	public String addNewUser(@Valid @ModelAttribute("user") UserDto dto,
			BindingResult result,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			return "user/userForm";
		}
		userService.save(dto);
		return "redirect:/user/all";
	}

	@GetMapping(path = "/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}

		model.addAttribute("user", userService.findById(id));

		return "user/userForm";
	}

	@PostMapping(path = "/edit/**")
	public String processEditUser(@Valid @ModelAttribute("user") UserDto dto,
			BindingResult result,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			return "user/userForm";
		}
		userService.save(dto);
		return "redirect:/user/all";
	}

	@GetMapping(path = "/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id,
			@SessionAttribute("loggedUser") UserDto loggedUser) {
		if (loggedUser.getId() == null
				|| !loggedUser.getUserRole().equals(UserRole.ADMIN)) {
			return "redirect:/";
		}

		userService.deleteFromDb(id);

		return "redirect:/user/all";
	}
}
