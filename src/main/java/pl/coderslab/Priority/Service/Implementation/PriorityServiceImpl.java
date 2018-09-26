package pl.coderslab.Priority.Service.Implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.Activity.Observer.Observer;
import pl.coderslab.Activity.Observer.Subject.Observerable;
import pl.coderslab.Priority.Repository.PriorityRepository;
import pl.coderslab.Priority.Service.PriorityService;
import pl.coderslab.Priority.domain.Priority;
import pl.coderslab.Priority.dto.PriorityDto;

@Service
public class PriorityServiceImpl implements PriorityService, Observerable {

	private final PriorityRepository priorityRepository;

	private Set<Observer> observerList = new HashSet<>();
	private final Observer observer;

	@Autowired
	public PriorityServiceImpl(PriorityRepository priorityRepository,
			Observer observer) {
		this.priorityRepository = priorityRepository;
		this.observer = observer;
	}

	// <------------------------ Observerable --------------------------------->
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

	// <------------------------ Observerable --------------------------------->

	@Override
	public PriorityDto findById(Long id) {
		return toDto(priorityRepository.getOne(id));
	}

	@Override
	public PriorityDto save(PriorityDto dto) {

		String activity = (dto.getId() == null)
				? "New Priority has been saved: " + dto.getName()
				: "Priority has been updated: " + dto.getName();
		notifyObservers(activity);

		return toDto(priorityRepository.save(toEntity(dto)));
	}

	@Override
	public void deleteFromDb(Long id) {
		priorityRepository.deleteById(id);
		notifyObservers("Priority has been deleted");

	}

	@Override
	public List<PriorityDto> getAll() {
		return toPriorityDtoList(priorityRepository.findAll());
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

	private List<PriorityDto> toPriorityDtoList(List<Priority> list) {
		return list.stream().filter(Objects::nonNull).map(this::toDto)
				.collect(Collectors.toList());
	}

}
