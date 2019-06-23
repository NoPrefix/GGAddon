package net.noprefix.GGAddon.utils;

import java.util.Locale;

public class LocaleManager {

    Locale current;
    String locale;


    public LocaleManager() {
        current = Locale.getDefault();
        locale = current.getLanguage().toUpperCase();
    }

    public String getLanguage() {
        return this.locale;
    }

}
