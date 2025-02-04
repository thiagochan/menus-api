package menus.com.menus.project.controller;

import lombok.RequiredArgsConstructor;
import menus.com.menus.project.domain.dtos.ProjectCreateForm;
import menus.com.menus.project.domain.entities.Project;
import menus.com.menus.project.repository.ProjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectRepository projectRepository;

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody ProjectCreateForm dto) {

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        return ResponseEntity.ok(projectRepository.findAll());
    }
}
