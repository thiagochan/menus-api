package menus.com.menus.project.service;

import lombok.Getter;
import lombok.Setter;
import menus.com.menus.project.domain.dtos.ProjectCreateForm;
import menus.com.menus.project.domain.entities.Project;
import menus.com.menus.user.domain.entities.Users;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class ProjectMapper {
    public Project convert(ProjectCreateForm dto, Users user) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setUser(user);
        project.setCreatedAt(dto.getCreatedAt());
        project.setPage(dto.getPage());
        return project;
    }
}
