package menus.com.menus.project.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import menus.com.menus.project.domain.entities.Project;
import menus.com.menus.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    public Project save(Project project) {
        return projectRepository.save(project);
    }
    public List<Project> getByUserId(Long userId) {
        return projectRepository.findByUserId(userId);
    }
}
