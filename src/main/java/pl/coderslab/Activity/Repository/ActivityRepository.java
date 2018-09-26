package pl.coderslab.Activity.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.coderslab.Activity.domain.Activity;

@Repository
public interface ActivityRepository  extends JpaRepository<Activity, Long>{
	
	
	List<Activity> findFirst25ByOrderByCreatedDesc();

}
