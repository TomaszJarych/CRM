package pl.coderslab.Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.Project.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
