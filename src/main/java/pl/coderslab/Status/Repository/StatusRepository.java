package pl.coderslab.Status.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.Status.domain.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
