package repositories.exceptions;

import localization.LocalizationUtils;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String name) {
        super(LocalizationUtils.getMessage(UserAlreadyExistsException.class.getSimpleName() + ".message", name));
    }
}
