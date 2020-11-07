/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.web.auth;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


@SessionScoped
@Named
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 5247284495374001127L;

    private String locale;

    public String getUsername() {
        Principal currentUser = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        if (currentUser == null) {
            return null;
        }
        return currentUser.getName();
    }

    public boolean isUserEmployee() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("EMPLOYEE");
    }

    public boolean isUserStaff() {
        boolean staff = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("STAFF");
        System.out.println("STAFF: " + staff);
        return staff;
    }

    public boolean isUserSecretary() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("SECRETARY");
    }

    public boolean isUserSupervisor() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("SUPERVISOR");
    }

    public boolean isUserAssistant() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ASSISTANT");
    }

    public boolean isUserInRole(String str) {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(str);
    }

    public boolean isLoggedIn() {
        if(getUsername() != null){
            return true;
        }
        return false;
    }

    public void logout() throws Exception {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath());
        fc.responseComplete();
    }

    public static void sendForward(String forwardUrl) throws Exception {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext ectx = ctx.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
            if (forwardUrl == null) {
                forwardUrl = request.getContextPath();
            }
            ctx.getExternalContext().redirect(forwardUrl);
            ctx.responseComplete();
        } catch (IOException e) {
            throw new Exception("[Error][Logout] [1]: " + e.getMessage());
        }
    }

    public String changeLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(this.locale));
        return null;
    }

    public String getLocale() {
        if(this.locale == null){
            this.locale = "en";
        }
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
        changeLocale();
    }
    
    public void setLocaleEN() {
        setLocale("en");
    }
    
    public void setLocaleDE() {
        setLocale("de");
    }
}
