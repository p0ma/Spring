package localization;

import com.springapp.mvc.context.provider.ApplicationContextProvider;
import org.springframework.context.i18n.LocaleContextHolder;

public class LocalizationUtils {

    public static String getFieldsMatchMessage(String field) {
        return getMessage("FieldMatch.message", getMessage("user." + field));
    }

    public static String getMessage(String code) {
        return ApplicationContextProvider.getApplicationContext().getMessage(code, null,
                LocaleContextHolder.getLocale());
    }

    public static String getMessage(String code, Object[] args) {
        return ApplicationContextProvider.getApplicationContext().getMessage(code, args,
                LocaleContextHolder.getLocale());
    }

    public static String getMessage(String code, Object arg) {
        return ApplicationContextProvider.getApplicationContext().getMessage(code, new Object[]{arg},
                LocaleContextHolder.getLocale());
    }

    public static String packageSimpleName(Package p) {
        String str = p.getName();
        return str.substring(str.lastIndexOf('.') + 1, str.length());
    }

    public static String[] getMonths() {
        return new String[]{
                getMessage("January"),
                getMessage("February"),
                getMessage("March"),
                getMessage("April"),
                getMessage("May"),
                getMessage("June"),
                getMessage("July"),
                getMessage("August"),
                getMessage("September"),
                getMessage("October"),
                getMessage("November"),
                getMessage("December")
        };
    }

    public static String[] getWeekdays() {
        return new String[]{
                getMessage("Sunday"),
                getMessage("Monday"),
                getMessage("Tuesday"),
                getMessage("Wednesday"),
                getMessage("Thursday"),
                getMessage("Friday"),
                getMessage("Saturday")
        };
    }

    public static String[] getShortMonths() {
        return new String[]{
                getMessage("January.short"),
                getMessage("February.short"),
                getMessage("March.short"),
                getMessage("April.short"),
                getMessage("May.short"),
                getMessage("June.short"),
                getMessage("July.short"),
                getMessage("August.short"),
                getMessage("September.short"),
                getMessage("October.short"),
                getMessage("November.short"),
                getMessage("December.short")
        };
    }
}
