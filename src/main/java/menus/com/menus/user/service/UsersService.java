package menus.com.menus.user.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import menus.com.menus.user.domain.entities.Users;
import menus.com.menus.user.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public Users findBy(Long id) {
        Optional<Users> users = usersRepository.findById(id);
        return users.orElse(null);
    }
}
