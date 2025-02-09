package menus.com.menus.project.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ProjectCreateForm {
    private String name;
    private Long userId;
    private LocalDateTime createdAt;
    private Map<String, Object> page;
}
