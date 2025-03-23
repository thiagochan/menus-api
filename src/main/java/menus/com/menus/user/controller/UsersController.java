package menus.com.menus.user.controller;

import lombok.RequiredArgsConstructor;
import menus.com.menus.jwt.dto.TokenDTO;
import menus.com.menus.user.domain.dtos.UserCreateForm;

import menus.com.menus.user.domain.dtos.UserLoginForm;
import menus.com.menus.user.domain.entities.Users;
import menus.com.menus.user.service.RoleService;
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
    private final RoleService roleService;
    private final UsersMapper usersMapper;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserCreateForm form) {
        Users hasUser = usersService.findUserByEmail(form.getEmail());
        if (hasUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já existente");
        }

        String hash = usersService.passwordHash(form.getPassword());
        Users userToSave = usersMapper.convert(form, hash, roleService.getDefault());
        usersService.save(userToSave);
        return new ResponseEntity<>("Usuário criado com sucesso", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserLoginForm form) {
        Users userToLogin = usersService.findUserByEmail(form.getEmail());

        if (userToLogin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(usersService.authenticateUser(form));
        return ResponseEntity.ok(tokenDTO);
    }
}
