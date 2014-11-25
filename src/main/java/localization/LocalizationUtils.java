package localization;

import com.springapp.mvc.context.provider.ApplicationContextProvider;
import org.springframework.context.i18n.LocaleContextHolder;

public class LocalizationUtils {

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

}
