package pl.coderslab.HomeController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.coderslab.User.Service.UserService;
import pl.coderslab.User.dto.UserDto;

@Controller
@RequestMapping("/")
@SessionAttributes("loggedUser")
public class HomeController {
	
	private final UserService userService;
	
	@Autowired
	public HomeController(UserService userService) {
		this.userService = userService;
	}

	// <--------------------------------RequestMappings---------------------------------->
	
	@GetMapping
	public String logInUser() {
		
		return "login/loginPage";
	}
	
	@PostMapping()
	public String selectUser(@RequestParam("userLogin")String login, Model model) {
		
		UserDto dto = userService.findUserByLogin(login);
		if (dto != null) {
			model.addAttribute("loggedUser", dto);
		}
		return "redirect:/index";
 
	}
	
	@GetMapping("/logout")
	public String logOutUser(Model model) {
		
		model.addAttribute("loggedUser", new UserDto());
		
		return "redirect:/";
	}
	
	
	// <--------------------------------ModelAttributes---------------------------------->

	@ModelAttribute("loggedUser")
	public UserDto getLoggedUser() {
		return new UserDto();
	}
	
	@ModelAttribute("userList")
	public List<UserDto> getUsersList(){
		return userService.getAll();
	}
}
