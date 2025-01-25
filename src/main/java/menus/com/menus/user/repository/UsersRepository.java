package menus.com.menus.user.repository;

import menus.com.menus.user.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
