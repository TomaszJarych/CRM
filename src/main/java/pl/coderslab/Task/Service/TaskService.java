package pl.coderslab.Task.Service;

import java.util.List;

import pl.coderslab.Commons.Service.BaseCrudService;
import pl.coderslab.Task.dto.TaskDto;

public interface TaskService  extends BaseCrudService<TaskDto, Long>{
	
	List<TaskDto> findAllByProjectId(Long id);

}
