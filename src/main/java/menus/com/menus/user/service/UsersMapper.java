package menus.com.menus.user.service;

import lombok.Getter;
import lombok.Setter;
import menus.com.menus.user.domain.dtos.UserCreateForm;
import menus.com.menus.user.domain.entities.Role;
import menus.com.menus.user.domain.entities.Users;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class UsersMapper {
    public Users convert(UserCreateForm form, String hash, Role role) {
        Users user = new Users();
        user.setName(form.getName());
        user.setPassword(hash);
        user.setEmail(form.getEmail());
        user.setRole(role);

        return user;
    }
}
