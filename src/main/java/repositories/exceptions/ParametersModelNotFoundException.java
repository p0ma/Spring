package repositories.exceptions;

import localization.LocalizationUtils;

public class ParametersModelNotFoundException extends Exception {
    public ParametersModelNotFoundException() {
        super(LocalizationUtils.getMessage(ParametersModelNotFoundException.class.getSimpleName() + ".message"));
    }
}
