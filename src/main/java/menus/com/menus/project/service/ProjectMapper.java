package menus.com.menus.project.service;

import lombok.Getter;
import lombok.Setter;
import menus.com.menus.project.domain.dtos.ProjectCreateForm;
import menus.com.menus.project.domain.entities.Project;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class ProjectMapper {
    public Project convert(ProjectCreateForm dto) {
        Project project = new Project();
        project.setName(dto.getName());
        return null;
    }
}
