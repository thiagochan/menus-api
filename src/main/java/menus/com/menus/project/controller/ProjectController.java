package menus.com.menus.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import menus.com.menus.project.domain.dtos.ProjectCreateForm;
import menus.com.menus.project.domain.dtos.ProjectDTO;
import menus.com.menus.project.domain.dtos.ProjectUpdateForm;
import menus.com.menus.project.domain.entities.Project;
import menus.com.menus.project.repository.ProjectRepository;
import menus.com.menus.project.service.ProjectMapper;
import menus.com.menus.project.service.ProjectService;
import menus.com.menus.user.domain.entities.Users;
import menus.com.menus.user.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final UsersService usersService;
    private final ProjectMapper projectMapper;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectCreateForm dto) {
        Users user = usersService.findBy(dto.getUserId());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        dto.setCreatedAt(LocalDateTime.now());
        Project project = projectMapper.convert(dto, user);

        return new ResponseEntity<>(projectMapper.convert(projectService.save(project)), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(projectMapper.convert(projectService.getProjectById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(projectMapper.convert(projectService.getAllProjects()));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ProjectDTO>> getProjectsByUserId(@PathVariable Long id) {
        Users user = usersService.findBy(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(projectMapper.convert(projectService.getByUserId(user.getId())));
    }

    @PutMapping
    public ResponseEntity<ProjectDTO> updateProject(@Valid @RequestBody ProjectUpdateForm dto) {
        Project project = projectService.getProjectById(dto.getId());
        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        Project updated = projectMapper.convert(dto, project);
        return ResponseEntity.ok(projectMapper.convert(projectService.save(updated)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        projectService.delete(project);
        return ResponseEntity.noContent().build();
    }
}
