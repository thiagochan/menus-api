package menus.com.menus.project.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ProjectDTO {
    private Long id;
    private LocalDateTime createdAt;
    private Long userId;
    private Map<String, Object> page;
    private String name;
}
