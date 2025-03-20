package menus.com.menus.user.controller;

import lombok.RequiredArgsConstructor;
import menus.com.menus.user.domain.dtos.UserCreateForm;

import menus.com.menus.user.domain.entities.Users;
import menus.com.menus.user.service.UsersMapper;
import menus.com.menus.user.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
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

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok().body(usersService.findAll());
    }
}
