package menus.com.menus.user.controller;

import lombok.RequiredArgsConstructor;
import menus.com.menus.user.domain.dtos.UserCreateForm;

import menus.com.menus.user.domain.dtos.UserLoginForm;
import menus.com.menus.user.domain.entities.Users;
import menus.com.menus.user.service.UsersMapper;
import menus.com.menus.user.service.UsersService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> create(@RequestBody UserCreateForm form) {
        Users hasUser = usersService.getUserByEmail(form.getEmail());
        if (hasUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já existente");
        }

        String hash = usersService.passwordHash(form.getPassword());
        Users userToSave = usersMapper.convert(form, hash);
        usersService.save(userToSave);
        return new ResponseEntity<>("Usuário criado com sucesso", HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginForm form) {
        Users userToLogin = usersService.getUserByEmail(form.getEmail());

        if (userToLogin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        String formHash = usersService.passwordHash(form.getPassword());
        if (!formHash.equals(userToLogin.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha inválida");
        }

        return ResponseEntity.ok().build();
    }
}
