package menus.com.menus.project.repository;

import menus.com.menus.project.domain.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUserId(Long userId);
    Optional<Project> findByDomainName(String domainName);
}
