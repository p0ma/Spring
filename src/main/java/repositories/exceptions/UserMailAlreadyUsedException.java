package repositories.exceptions;

import localization.LocalizationUtils;

public class UserMailAlreadyUsedException extends Exception {
    public UserMailAlreadyUsedException(String mail) {
        super(LocalizationUtils.getMessage(UserMailAlreadyUsedException.class.getSimpleName() + ".message", mail));
    }
}
