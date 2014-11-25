package repositories.exceptions;

import localization.LocalizationUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Damager1
 * Date: 17.11.14
 * Time: 18:43
 * To change this template use File | Settings | File Templates.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super(LocalizationUtils.getMessage(UserNotFoundException.class.getSimpleName() + ".message"));
    }
}
