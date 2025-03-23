package menus.com.menus.user.service;

import lombok.RequiredArgsConstructor;
import menus.com.menus.user.domain.entities.Role;
import menus.com.menus.user.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role getDefault() {
        return roleRepository.getReferenceById(1L);
    }
}
