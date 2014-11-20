package entities.auth;

import com.springapp.mvc.media.UserRegistrationDTO;
import entities.drilling.model.WorkingDataSet;

public class UserFactory {
    public static User getUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setName(userRegistrationDTO.getName());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setWorkingDataSet(WorkingDataSet.build());
        user.getWorkingDataSet().setUser(user);
        user.setRole("ROLE_USER");
        return user;
    }
}
