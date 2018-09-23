package pl.coderslab.Priority.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.Priority.domain.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

}
