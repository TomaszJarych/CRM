package pl.coderslab.Status.Service.Implementation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.Status.Repository.StatusRepository;
import pl.coderslab.Status.Service.StatusService;
import pl.coderslab.Status.domain.Status;
import pl.coderslab.Status.dto.StatusDto;

@Service
public class StatusServiceImpl implements StatusService {

	private final StatusRepository statusRepository;

	@Autowired
	public StatusServiceImpl(StatusRepository statusRepository) {
		this.statusRepository = statusRepository;
	}

	@Override
	public StatusDto findById(Long id) {
		return toDto(statusRepository.getOne(id));
	}

	@Override
	public StatusDto save(StatusDto dto) {
		return toDto(statusRepository.save(toEntityStatus(dto)));
	}

	@Override
	public void deleteFromDb(Long id) {
		statusRepository.deleteById(id);
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

		status.setId(status.getId());
		status.setName(status.getName());
		status.setIsActive(status.getIsActive());
		status.setSortingOrderNumber(status.getSortingOrderNumber());

		return status;
	}

	private List<StatusDto> toDtoList(List<Status> list) {
		return list.stream().filter(Objects::nonNull).map(this::toDto)
				.collect(Collectors.toList());

	}

}
