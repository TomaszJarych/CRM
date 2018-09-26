package pl.coderslab.Activity.Observer.Implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.coderslab.Activity.Observer.Observer;
import pl.coderslab.Activity.Service.ActivityService;
import pl.coderslab.Activity.dto.ActivityDto;

@Component
public class ActivityObserver implements Observer {
	
	@Autowired
	private ActivityService service;
	

	
	@Override
	public void addNewActivity(String content) {
		ActivityDto dto = new ActivityDto(content);
		service.save(dto);
		
		
	}

}
