package repositories.exceptions;

import localization.LocalizationUtils;

/**
 * Created by Damager1 on 18.11.2014.
 */
public class ParameterNotFoundException extends Exception {
    public ParameterNotFoundException() {
        super(LocalizationUtils.getMessage(ParameterNotFoundException.class.getSimpleName() + ".message"));
    }
}
