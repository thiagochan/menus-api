package menus.com.menus.user.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import menus.com.menus.config.SecurityConfiguration;
import menus.com.menus.jwt.service.JwtTokenService;
import menus.com.menus.user.domain.dtos.UserLoginForm;
import menus.com.menus.user.domain.entities.UserDetailsImpl;
import menus.com.menus.user.domain.entities.Users;
import menus.com.menus.user.repository.UsersRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final SecurityConfiguration securityConfiguration;

    public Users findBy(Long id) {
        Optional<Users> users = usersRepository.findById(id);
        return users.orElse(null);
    }

    public String passwordHash(String password) {
        return securityConfiguration.passwordEncoder().encode(password);
    }

    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email).orElse(null);
    }

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public void save(Users users) {
        usersRepository.save(users);
    }

    public String authenticateUser(UserLoginForm loginUserDto) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        return jwtTokenService.generateToken(userDetails);
    }
}
