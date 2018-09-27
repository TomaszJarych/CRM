package pl.coderslab.Task.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.Task.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	
	List<Task> findAllByProjectId(Long id);

}
