package pl.coderslab.User.Service;

import pl.coderslab.Commons.Service.BaseCrudService;
import pl.coderslab.User.domain.User;
import pl.coderslab.User.dto.UserDto;

public interface UserService extends BaseCrudService<UserDto, Long> {

	UserDto toSimpleDto(User user);

}
