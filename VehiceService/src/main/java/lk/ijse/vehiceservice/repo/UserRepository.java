package lk.ijse.vehiceservice.repo;

import lk.ijse.vehiceservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<lk.ijse.vehiceservice.entity.User, UUID> {

    User findByEmail(String email);

    boolean existsUserByEmail(String email);
}
