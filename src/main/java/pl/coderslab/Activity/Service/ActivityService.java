package pl.coderslab.Activity.Service;

import java.util.List;

import pl.coderslab.Activity.dto.ActivityDto;
import pl.coderslab.Commons.Service.BaseCrudService;

public interface ActivityService extends BaseCrudService<ActivityDto, Long>{
	
	List<ActivityDto> findFirst25ByOrderByCreatedDesc();

}
