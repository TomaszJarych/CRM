package pl.coderslab.Activity.Service.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import pl.coderslab.Activity.Repository.ActivityRepository;
import pl.coderslab.Activity.Service.ActivityService;
import pl.coderslab.Activity.domain.Activity;
import pl.coderslab.Activity.dto.ActivityDto;


@Service
@NoArgsConstructor
public class ActivityServiceImpl implements ActivityService {

	private ActivityRepository activityRepository;

	@Autowired
	public ActivityServiceImpl(ActivityRepository activityRepository) {
		this.activityRepository = activityRepository;
	}

	@Override
	public ActivityDto findById(Long id) {
		return toActivityDto(activityRepository.getOne(id));
	}

	@Override
	public ActivityDto save(ActivityDto dto) {
		return toActivityDto(activityRepository.save(toActivityEntity(dto)));
	}

	@Override
	public void deleteFromDb(Long id) {
		activityRepository.deleteById(id);
	}

	@Override
	public List<ActivityDto> getAll() {
		return toActivityDtosList(activityRepository.findAll());
	}
	
	@Override
	public List<ActivityDto> findFirst25ByOrderByCreatedDesc() {
		return toActivityDtosList(activityRepository.findFirst25ByOrderByCreatedDesc());
	}
	
	

	private ActivityDto toActivityDto(Activity activity) {
		ActivityDto dto = new ActivityDto(activity.getContent());
		dto.setCreated(activity.getCreated());
		dto.setId(activity.getId());
		return dto;
	}

	private Activity toActivityEntity(ActivityDto dto) {
		Activity activity;

		if (dto.getId() == null) {
			activity = new Activity(dto.getContent());
		} else {
			activity = activityRepository.getOne(dto.getId());
			activity.setContent(dto.getContent());
		}

		return activity;
	}

	private List<ActivityDto> toActivityDtosList(List<Activity> list) {
		return list.stream().map(this::toActivityDto)
				.collect(Collectors.toList());
	}
	
}
