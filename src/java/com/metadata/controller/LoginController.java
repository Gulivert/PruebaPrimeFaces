/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metadata.controller;

import com.metadata.facade.UsuariosFacade;
import com.metadata.prueba.Usuarios;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.CDI;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {
    
    @Inject
    ApplicationController applicationController;
    
    private UsuariosFacade ejbFacade;

    private static final long serialVersionUID = -2152389656664659476L;
    private String nombre;
    private String clave;
    private String hora;
    private Usuarios user;
    private boolean logeado = false;

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios user) {
        this.user = user;
    }
    
    public boolean estaLogeado() {
        return logeado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void logout(ActionEvent actionEvent) {
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sesion cerrada", nombre);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        nombre = null;
        clave = null;
        hora = null;
        user=null;
        
        redirectToUrl("/index.xhtml");
    }
    
    public void redirectLogin (ActionEvent actionEvent) {
        redirectToUrl("/login.xhtml");
    }

    public void redirectToUrl(String file) {
        //Recuperamos la ruta para redirigir
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getRequestURL().toString();
        url = url.substring(0, url.length() - req.getRequestURI().length()) + req.getContextPath() + req.getServletPath() + file;
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void login(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        ejbFacade = CDI.current().select(UsuariosFacade.class).get();
        Usuarios user = ejbFacade.findByLogin(nombre, clave);
        
        //Comprobamos si existe el usuario
        if (user != null) {
            this.user=user;
            logeado = true;
            //Incrementamos el numero de usarios logueados desde que se arranco la aplicacion
            applicationController.incrementarNumeroLogueados();
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", user.getNombre());
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            hora = sdf.format(cal.getTime());
            redirectToUrl("/index.xhtml");
        } else {
            logeado = false;
            this.user = null;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                    "Credenciales no válidas");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("estaLogeado", logeado);
                
    }

    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        logeado = false;
    }
}
