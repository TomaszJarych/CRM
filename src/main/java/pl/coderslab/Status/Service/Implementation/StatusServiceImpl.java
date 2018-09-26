package pl.coderslab.Status.Service.Implementation;

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
import pl.coderslab.Status.Repository.StatusRepository;
import pl.coderslab.Status.Service.StatusService;
import pl.coderslab.Status.domain.Status;
import pl.coderslab.Status.dto.StatusDto;

@Service
public class StatusServiceImpl implements StatusService, Observerable {

	private final StatusRepository statusRepository;

	private Set<Observer> observerList = new HashSet<>();
	private final Observer observer;

	@Autowired
	public StatusServiceImpl(StatusRepository statusRepository,
			Observer observer) {
		this.statusRepository = statusRepository;
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
	public StatusDto findById(Long id) {
		return toDto(statusRepository.getOne(id));
	}

	@Override
	public StatusDto save(StatusDto dto) {
		String activity = (dto.getId() == null)
				? "New Status has been saved: " + dto.getName()
				: "Status has been updated: " + dto.getName();
		notifyObservers(activity);
		return toDto(statusRepository.save(toEntityStatus(dto)));
	}

	@Override
	public void deleteFromDb(Long id) {
		statusRepository.deleteById(id);
		notifyObservers("Status has been deleted");
	}

	@Override
	public List<StatusDto> getAll() {
		return toDtoList(statusRepository.findAll());
	}

	private StatusDto toDto(Status status) {
		StatusDto dto = new StatusDto();

		dto.setId(status.getId());
		dto.setName(status.getName());
		dto.setIsActive(status.getIsActive());
		dto.setSortingOrderNumber(status.getSortingOrderNumber());

		return dto;
	}

	private Status toEntityStatus(StatusDto dto) {
		Status status = new Status();

		status.setId(dto.getId());
		status.setName(dto.getName());
		status.setIsActive(dto.getIsActive());
		status.setSortingOrderNumber(dto.getSortingOrderNumber());

		return status;
	}

	private List<StatusDto> toDtoList(List<Status> list) {
		return list.stream().filter(Objects::nonNull).map(this::toDto)
				.collect(Collectors.toList());

	}

}
