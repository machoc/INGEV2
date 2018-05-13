/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.controller;

import com.sar.model.UsuarioInge;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author manuel
 */
@ManagedBean(name="bean")
@SessionScoped
public class PlantillaController implements Serializable{
private boolean flag;
@PostConstruct
public void postConstruct()
{
System.out.println("Inside post Construct");
}

public void beforePageLoad()
{
    System.out.println("Called before pageload for session scoped bean!!!");
}

    public boolean isFlag() {
         UsuarioInge us = (UsuarioInge) FacesContext.getCurrentInstance().getExternalContext().
                 getSessionMap().get("usuario");
         if(us.getTipo().equals("USUARIO")){
             flag = true;
         }
        return flag;
    }


 public void verificarSesion() {
        try {
           
            FacesContext context = FacesContext.getCurrentInstance();
            UsuarioInge us = (UsuarioInge) context.getExternalContext().getSessionMap().get("usuario");
               // System.out.println(us.toString());
            if (us == null ) {
                context.getExternalContext().redirect("permisos.xhtml");
            }
        } catch (Exception e) {
            //log para guardar algun registro de un error
        }
    }
 
  public void isAdmi() {
     try{
     FacesContext context = FacesContext.getCurrentInstance();
            UsuarioInge us = (UsuarioInge) context.getExternalContext().getSessionMap().get("usuario");
            this.isFlag();
     if(flag  || us == null){
          context.getExternalContext().redirect("permisos.xhtml");
     }
     }catch(Exception e){
         
     }
 }
 
public String logout() {
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    return "login?faces-redirect=true";
} 

//public String logout()
//{
//    setAccount(null);
//    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
//    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//
//    NavigationUtils.redirect("/admin/login");
//    return "";
//}
//
/////page
//<h:commandLink action="#{loginBean.logout()}" value="Logout" rendered="#{!empty loginBean.account}" />


}

