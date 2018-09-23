package pl.coderslab.User.Service.Implementation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.User.Repository.UserRepository;
import pl.coderslab.User.Service.UserService;
import pl.coderslab.User.domain.User;
import pl.coderslab.User.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDto findById(Long id) {
		return toDto(userRepository.getOne(id));
	}

	@Override
	public UserDto save(UserDto dto) {
		return toDto(userRepository.save(toUserEntity(dto)));
	}

	@Override
	public void deleteFromDb(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<UserDto> getAll() {
		return toDtoList(userRepository.findAll());
	}

	private UserDto toDto(User user) {
		UserDto dto = new UserDto();

		dto.setId(user.getId());
		dto.setLogin(user.getLogin());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		// dto.setPassword(user.getPassword());
		dto.setUserRole(user.getUserRole());

		return dto;
	}

	private User toUserEntity(UserDto dto) {
		User user = new User();

		user.setId(user.getId());
		user.setLogin(user.getLogin());
		user.setFirstName(user.getFirstName());
		user.setLastName(user.getLastName());
		if (dto.getPassword() != null && dto.getPassword() != "") {
			user.setPassword(
					BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
		}
		user.setUserRole(user.getUserRole());

		return user;
	}

	private List<UserDto> toDtoList(List<User> list) {
		return list.stream().filter(Objects::nonNull).map(this::toDto)
				.collect(Collectors.toList());

	}

}
