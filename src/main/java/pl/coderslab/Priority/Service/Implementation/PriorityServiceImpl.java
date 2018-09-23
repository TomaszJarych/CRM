package pl.coderslab.Priority.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.Priority.Repository.PriorityRepository;
import pl.coderslab.Priority.domain.Priority;
import pl.coderslab.Priority.dto.PriorityDto;

@Service
public class PriorityServiceImpl {
	
	private final PriorityRepository priorityRepository;

	@Autowired
	public PriorityServiceImpl(PriorityRepository priorityRepository) {
		this.priorityRepository = priorityRepository;
	}
	
	private PriorityDto toDto(Priority priority) {
		PriorityDto dto = new PriorityDto();
		
		dto.setId(priority.getId());
		dto.setName(priority.getName());
		dto.setIsActive(priority.getIsActive());
		
		return dto;
	}
	
	private Priority toEntity(PriorityDto dto) {
		Priority priority = new Priority();
		
		priority.setId(dto.getId());
		priority.setName(dto.getName());
		priority.setIsActive(dto.getIsActive());
		
		return priority;
	}

}
