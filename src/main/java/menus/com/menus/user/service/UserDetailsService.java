package menus.com.menus.user.service;

import lombok.RequiredArgsConstructor;
import menus.com.menus.user.domain.entities.UserDetailsImpl;
import menus.com.menus.user.domain.entities.Users;
import menus.com.menus.user.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("Usuário não encontrado"));
        return new UserDetailsImpl(user);
    }
}
