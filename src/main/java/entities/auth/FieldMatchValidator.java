package entities.auth;

import localization.LocalizationUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String[] fields;
    private String[] verifyFields;

    public void initialize(FieldMatch constraintAnnotation) {
        fields = constraintAnnotation.fields();
        verifyFields = constraintAnnotation.verifyFields();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {

        boolean matches = true;

        for (int i = 0; i < fields.length; i++) {
            Object fieldObj, verifyFieldObj;
            try {
                fieldObj = BeanUtils.getProperty(value, fields[i]);
                verifyFieldObj = BeanUtils.getProperty(value, verifyFields[i]);
            } catch (Exception e) {
                //ignore
                continue;
            }
            boolean neitherSet = (fieldObj == null) && (verifyFieldObj == null);
            if (neitherSet) {
                continue;
            }

            boolean tempMatches = (fieldObj != null) && fieldObj.equals(verifyFieldObj);

            if (!tempMatches) {
                addConstraintViolation(context,
                        LocalizationUtils.getFieldsMatchMessage(fields[i]),
                        verifyFields[i]);
            }

            matches = matches ? tempMatches : matches;
        }
        return matches;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message, String field) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addNode(field).addConstraintViolation();
    }
}
