package service;

import entities.auth.User;
import entities.drilling.model.parameters.actual.parameters.drill_column.DrillColumnInnerVolume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.IUserRepository;
import repositories.exceptions.InferenceModelFoundException;
import repositories.exceptions.UserNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserService {

    @PersistenceContext
    public EntityManager entityManager;

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

        user.getWorkingDataSet().getParametersModel().initParameters();

        String str = user.getWorkingDataSet().getParametersModel().getParameter(DrillColumnInnerVolume.class).getParameterName();

        if (user == null) {
            throw new UserNotFoundException("No user found");
        }

        return user;
    }
}
