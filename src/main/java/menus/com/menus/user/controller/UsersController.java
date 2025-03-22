package menus.com.menus.user.controller;

import lombok.RequiredArgsConstructor;
import menus.com.menus.user.domain.dtos.UserCreateForm;

import menus.com.menus.user.domain.dtos.UserLoginForm;
import menus.com.menus.user.domain.entities.Users;
import menus.com.menus.user.service.UsersMapper;
import menus.com.menus.user.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class UsersController {
    private final UsersService usersService;
    private final UsersMapper usersMapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserCreateForm form) {
        String hash = usersService.passwordHash(form.getPassword());
        Users userToSave = usersMapper.convert(form, hash);
        usersService.save(userToSave);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserLoginForm form) {
        Users userToLogin = usersService.getUserByEmail(form.getEmail());

        if (userToLogin == null) {
            return ResponseEntity.notFound().build();
        }

        String formHash = usersService.passwordHash(form.getPassword());
        if (!formHash.equals(userToLogin.getPassword())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }
}
