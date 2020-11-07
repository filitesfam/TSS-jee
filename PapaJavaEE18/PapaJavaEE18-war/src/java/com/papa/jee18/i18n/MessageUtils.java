/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.application.Application;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;


public class MessageUtils {

    public static String getMessage(final String key) {
        return getMessage(key, null);
    }

    public static String getMessage(String key, final Object[] parameters) {
        try {
            String message = getResourceBundle("msg").getString(key);

            if (parameters != null) {
                message = MessageFormat.format(message, parameters);
            }
            return message;
        } catch (MissingResourceException e) {
            return "???" + key + "???";
        }
    }

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public static Application getApplication() {
        return getFacesContext().getApplication();
    }

    public static UIViewRoot getViewRoot() {
        return getFacesContext().getViewRoot();
    }

    public static void setLocale(Locale locale) {
        getViewRoot().setLocale(locale);
    }

    public static ResourceBundle getResourceBundle(String key) {
        return getApplication().getResourceBundle(getFacesContext(), key);
    }
}

