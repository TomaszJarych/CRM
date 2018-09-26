package pl.coderslab.Project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.Project.Service.ProjectService;
import pl.coderslab.Project.dto.ProjectDto;
import pl.coderslab.User.Service.UserService;

@RestController
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
	
	@GetMapping(path="/createData", produces= MediaType.APPLICATION_JSON_VALUE)
	public ProjectDto createData() {
		ProjectDto dto = new ProjectDto();
		dto.setName("CRM-nowy projekt");
		dto.setDescription("CRM");
		dto.getUsers().add(userService.findById(1L));
		dto.setIsActive(true);
		dto.setWebsite("www.crm.com.pl");

		
		return projectService.save(dto);
	}
	
	@GetMapping(path="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ProjectDto getProjectById(@PathVariable("id")Long id) {
		
		return projectService.findById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ProjectDto saveNewProject(@RequestBody ProjectDto dto) {
		
		return projectService.save(dto);
	}
	
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ProjectDto updateProject(@RequestBody ProjectDto dto) {
		
		return projectService.save(dto);
	}
	
	@DeleteMapping(path="/{id}")
	public void deleteProject(@PathVariable("id")Long id) {
		projectService.deleteFromDb(id);
	}
	
	@GetMapping(path ="/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectDto> getAllProjects(){
		return projectService.getAll();
	}
	
	@GetMapping(path ="/last", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectDto> getLastProjects(){
		return projectService.findFirst5ByOrderByCreatedDesc();
	}
}
