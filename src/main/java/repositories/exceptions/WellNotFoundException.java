package repositories.exceptions;

import localization.LocalizationUtils;

public class WellNotFoundException extends Exception {
    public WellNotFoundException() {
        super(LocalizationUtils.getMessage(WellNotFoundException.class.getSimpleName() + ".message"));
    }
}
