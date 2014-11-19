package system.auth;

import com.springapp.mvc.media.UserRegistrationDTO;
import system.drilling.model.WorkingDataSet;

/**
 * Created by Damager1 on 18.11.2014.
 */
public class UserFactory {
    public static User getUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setName(userRegistrationDTO.getName());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setWorkingDataSet(WorkingDataSet.build());
        user.setRole("ROLE_USER");
        return user;
    }
}
