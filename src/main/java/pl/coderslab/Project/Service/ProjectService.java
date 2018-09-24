package pl.coderslab.Project.Service;

import pl.coderslab.Commons.Service.BaseCrudService;
import pl.coderslab.Project.domain.Project;
import pl.coderslab.Project.dto.ProjectDto;

public interface ProjectService extends BaseCrudService<ProjectDto, Long>{
	
	ProjectDto toSimpleDto(Project project);

}
