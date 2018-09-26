package pl.coderslab.Project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.Project.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	List<Project> findFirst5ByOrderByCreatedDesc();
}
