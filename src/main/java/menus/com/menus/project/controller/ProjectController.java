package menus.com.menus.project.controller;

import lombok.RequiredArgsConstructor;
import menus.com.menus.project.domain.dtos.ProjectCreateForm;
import menus.com.menus.project.domain.entities.Project;
import menus.com.menus.project.repository.ProjectRepository;
import menus.com.menus.project.service.ProjectMapper;
import menus.com.menus.project.service.ProjectService;
import menus.com.menus.user.domain.entities.Users;
import menus.com.menus.user.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final UsersService usersService;
    private final ProjectMapper projectMapper;

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody ProjectCreateForm dto) {
        Users user = usersService.findBy(dto.getUserId());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        dto.setCreatedAt(LocalDateTime.now());
        Project project = projectMapper.convert(dto, user);

        return ResponseEntity.ok(projectService.save(project));
    }

    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }
}
