package pl.coderslab.User.Service.Implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.Activity.Observer.Observer;
import pl.coderslab.Activity.Observer.Subject.Observerable;
import pl.coderslab.Commons.EntityConverter.ConverterUtils;
import pl.coderslab.Project.Repository.ProjectRepository;
import pl.coderslab.User.Repository.UserRepository;
import pl.coderslab.User.Service.UserService;
import pl.coderslab.User.domain.User;
import pl.coderslab.User.dto.UserDto;

@Service
public class UserServiceImpl implements UserService, Observerable {

	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	private final ConverterUtils converter;
	private Set<Observer> observerList = new HashSet<>();
	
	@Autowired
	private Observer observer;

	@Override
	public void attatchObserver(Observer observer) {
		this.observerList.add(observer);
	}

	@Override
	public void detatchObserver(Observer observer) {
		this.observerList.remove(observer);

	}
	@Override
	public void notifyObservers(String content) {
		this.observerList.forEach(el -> el.addNewActivity(content));

	}
	
	@PostConstruct
	public void initObservers() {
		attatchObserver(observer);
	}

	@Autowired
	public UserServiceImpl(UserRepository userRepository,
			ProjectRepository projectRepository, ConverterUtils converter) {
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
		this.converter = converter;
	}

	@Override
	public UserDto findById(Long id) {
		return toSimpleDto(userRepository.getOne(id));
	}

	@Override
	public UserDto save(UserDto dto) {
		 notifyObservers("User has been saved: "+dto.getFirstName()+" "+dto.getLastName());
		return toSimpleDto(userRepository.save(toUserEntity(dto)));
	}

	@Override
	public void deleteFromDb(Long id) {
		
		notifyObservers("User has been deleted form DB");
		userRepository.deleteById(id);
	}

	@Override
	public List<UserDto> getAll() {
		return toDtoList(userRepository.findAll());
	}

	public UserDto toSimpleDto(User user) {
		UserDto dto = new UserDto();

		dto.setId(user.getId());
		dto.setLogin(user.getLogin());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		// dto.setPassword(user.getPassword());
		dto.setUserRole(user.getUserRole());

		return dto;
	}
	public UserDto toDto(User user) {
		UserDto dto = new UserDto();

		dto.setId(user.getId());
		dto.setLogin(user.getLogin());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		// dto.setPassword(user.getPassword());
		dto.setUserRole(user.getUserRole());
		if (Objects.nonNull(user.getProjects())
				&& !user.getProjects().isEmpty()) {
			dto.getProjects().clear();
			user.getProjects().stream()
					.map(el -> converter.toSimpleProjectDto(el))
					.forEach(el -> dto.getProjects().add(el));
		}

		return dto;
	}

	private User toUserEntity(UserDto dto) {
		User user = new User();

		user.setId(dto.getId());
		user.setLogin(dto.getLogin());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		if (dto.getPassword() != null && dto.getPassword() != "") {
			user.setPassword(
					BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
		}
		user.setUserRole(dto.getUserRole());

		if (Objects.nonNull(user.getProjects())
				&& !user.getProjects().isEmpty()) {
			user.getProjects().clear();
			dto.getProjects().stream().forEach(el -> user.getProjects()
					.add(projectRepository.getOne(el.getId())));
		}

		return user;
	}

	private List<UserDto> toDtoList(List<User> list) {
		return list.stream().map(el -> toDto(el)).collect(Collectors.toList());

	}

}
