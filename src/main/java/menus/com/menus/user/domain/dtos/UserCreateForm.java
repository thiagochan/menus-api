package menus.com.menus.user.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    private String name;
    private String password;
    private String email;
}
