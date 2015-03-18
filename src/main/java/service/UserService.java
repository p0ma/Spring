package service;

import entities.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.IUserRepository;
import repositories.exceptions.UserAlreadyExistsException;
import repositories.exceptions.UserMailAlreadyUsedException;
import repositories.exceptions.UserNotFoundException;

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
            throw new UserNotFoundException();
        }
        userRepository.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(Long id) throws UserNotFoundException {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Transactional(rollbackFor = UserNotFoundException.class)
    public User update(User updated) throws UserNotFoundException {
        User user = userRepository.findOne(updated.getId());
        if (user == null) {
            throw new UserNotFoundException();
        }
        userRepository.save(updated);

        return updated;
    }

    @Transactional(readOnly = true)
    public User findByName(String name) throws UserNotFoundException {
        User user = userRepository.findOneByName(name);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Transactional
    public User register(User user) throws UserAlreadyExistsException, UserMailAlreadyUsedException {
        User checkUser;
        try {
            checkUser = userRepository.findOneByName(user.getName());
            if (checkUser != null) {
                throw new UserAlreadyExistsException(user.getName());
            }
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new UserAlreadyExistsException(user.getName());
        }
        try {
            checkUser = userRepository.findOneByEmail(user.getEmail());
            if (checkUser != null) {
                throw new UserMailAlreadyUsedException(user.getEmail());
            }
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new UserMailAlreadyUsedException(user.getEmail());
        }
        return create(user);
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            return findByName(s);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
