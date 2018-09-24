package pl.coderslab.Task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.Task.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
