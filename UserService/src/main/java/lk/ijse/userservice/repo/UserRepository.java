package lk.ijse.userservice.repo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lk.ijse.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

    boolean existsUserByEmail(String email);
}
