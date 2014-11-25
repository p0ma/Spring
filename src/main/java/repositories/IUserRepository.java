package repositories;

import entities.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

    public User findOneByName(String name);

    public User findOneByEmail(String email);

}
