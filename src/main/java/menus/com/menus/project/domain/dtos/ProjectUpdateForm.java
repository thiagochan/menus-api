package menus.com.menus.project.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ProjectUpdateForm {
    private Long id;
    private String name;
    private Map<String, Object> page;
}
