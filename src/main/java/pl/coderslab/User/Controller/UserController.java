package pl.coderslab.User.Controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.User.Service.UserService;
import pl.coderslab.User.dto.UserDto;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	private UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDto> getAllUsers() {

		return service.getAll();

	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDto getUserById(@PathVariable("id") Long id) {

		return service.findById(id);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addNewUser(@RequestBody UserDto dto) {
		service.save(dto);

	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addEditUser(@RequestBody UserDto dto) {
		service.save(dto);
	}

	@DeleteMapping(path = "/{id}")
	public void DtodeleteUser(@PathVariable("id") Long id) {

		service.deleteFromDb(id);
	}

}
