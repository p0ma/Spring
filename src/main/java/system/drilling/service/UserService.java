package system.drilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.auth.User;
import system.drilling.repositories.IUserRepository;
import system.drilling.repositories.exceptions.InferenceModelFoundException;
import system.drilling.repositories.exceptions.UserNotFoundException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Transactional
    public User create(User created) {
        return userRepository.save(created);
    }

    @Transactional(rollbackFor = UserNotFoundException.class)
    public User delete(Long id) throws UserNotFoundException {
        User deleted = userRepository.findOne(id);

        if (deleted == null) {
            throw new UserNotFoundException("User not found");
        }

        userRepository.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Transactional(rollbackFor = InferenceModelFoundException.class)
    public User update(User updated) throws UserNotFoundException {
        User user = userRepository.findOne(updated.getId());

        if (user == null) {
            throw new UserNotFoundException("No user found");
        }

        userRepository.save(updated);

        return updated;
    }

    @Transactional(readOnly = true)
    public User findByName(String name) throws UserNotFoundException {
        User user = userRepository.findOneByName(name);

        if (user == null) {
            throw new UserNotFoundException("No user found");
        }

        return user;
    }
}
