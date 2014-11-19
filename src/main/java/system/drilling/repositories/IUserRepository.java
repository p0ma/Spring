package system.drilling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import system.auth.User;

public interface IUserRepository extends JpaRepository<User, Long> {

    public User findOneByName(String name);

}
