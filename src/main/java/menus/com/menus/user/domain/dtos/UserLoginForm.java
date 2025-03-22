package menus.com.menus.user.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginForm {
    private String email;
    private String password;
}
