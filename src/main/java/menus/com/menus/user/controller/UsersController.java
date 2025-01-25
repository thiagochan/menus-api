package menus.com.menus.user.controller;

import lombok.RequiredArgsConstructor;
import menus.com.menus.user.domain.entities.Users;
import menus.com.menus.user.repository.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok().body(usersRepository.findAll());
    }
}
