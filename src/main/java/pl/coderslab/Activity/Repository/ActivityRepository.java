package pl.coderslab.Activity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.Activity.domain.Activity;

@Repository
public interface ActivityRepository  extends JpaRepository<Activity, Long>{

}
