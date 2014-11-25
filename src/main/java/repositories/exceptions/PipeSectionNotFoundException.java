package repositories.exceptions;

import localization.LocalizationUtils;

public class PipeSectionNotFoundException extends Exception {
    public PipeSectionNotFoundException() {
        super(LocalizationUtils.getMessage(PipeSectionNotFoundException.class.getSimpleName() + ".message"));
    }
}
