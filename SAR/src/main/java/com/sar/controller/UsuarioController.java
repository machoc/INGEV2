/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.controller;

import com.sar.model.UsuarioInge;
import com.sar.session.UsuarioIngeFacadeLocal;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author manuel
 */
@Named("usuarioController")
@RequestScoped
public class UsuarioController implements Serializable
{

    private UsuarioInge  user = new UsuarioInge();
    private String setcontraseña;
     

    @EJB
    private UsuarioIngeFacadeLocal facade;
    private boolean flag;

    public UsuarioController(){
   
    
}

    public String getSetcontraseña() {
        return setcontraseña;
    }

    public void setSetcontraseña(String setcontraseña) {
        this.setcontraseña = setcontraseña;
    }
    
    

    public UsuarioInge getUser()
    {
        return user;
    }

    public void setUser(UsuarioInge user)
    {
        this.user = user;
    }

    public List<UsuarioInge> listar()
    {
        return this.facade.findAll();
    }

    


    public void limpiar()
    {
        //mejorar con cni
      
    }

    public void eliminar(UsuarioInge c)
    {
        try
        {
            //pregintar confirmacion en el view
            facade.remove(c);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "ELIMINADO EXITOSAMENTE"));
        } catch (Exception e)
        {
            //System.err.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
        }
    }

    public void registrar()
    {
        try
        {
            // this.user.setCodigo(persona);
             this.user.setContraseña("1234");
            facade.create(user);   
            this.user = new UsuarioInge();
            RequestContext req = RequestContext.getCurrentInstance();
            req.execute("PF('widModify').hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "REGISTRADO EXITOSAMENTE"));
        } catch (Exception e)
        {
            // System.err.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR la cedula ya existe"));

        }
    }

    public void modificar()
    {
        try
        {
            facade.edit(user);
            this.user = new UsuarioInge();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "MODIFICADO EXITOSAMENTE"));
        } catch (Exception e)
        {
            // System.err.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
        }
        // return "categoria";
    }

    public void modificarRow(UsuarioInge us)
    {
        try
        {
            facade.edit(us);
            // this.user = new Usuario();
        } catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        // return "categoria";
    }

    public String edit(UsuarioInge c)
    {
        this.user = c;
        return "usuario";
        //return "editar";
    }

    public void reset()
    {
        RequestContext.getCurrentInstance().reset("formNew:panelNew");
    }
    
    public String iniciarSesion(){
        String redireccion = null;
        UsuarioInge us;
        try{
            us = this.facade.iniciarSesion(this.user);
            if(us != null){
                
               //almacenar datos en una sesion JSF
                  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                  redireccion = "index?faces-redirect=true";
             //   redireccion = (us.getTipo().equals("Usuario"))?"principal?faces-redirect=true":"index?faces-redirect=true";
            }else{
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "AVISO", "Datos Incorrectos"));
      
            }
        }catch(Exception e){
            //errores
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
      
        }
        
        return redireccion;
    }
    
        public String cambiarContraseña(){
        try{
            for(UsuarioInge usuarios : this.facade.findAll()){
                if(this.user.getContraseña().equals(usuarios.getContraseña()) && this.user.getCedula().equals(usuarios.getCedula())){
                    //this.user.setContraseña(this.setcontraseña);
                    usuarios.setContraseña(setcontraseña);
                   this.user = usuarios;
                   modificar();
                  RequestContext req = RequestContext.getCurrentInstance();
            req.execute("PF('cambiar').hide();");
                //   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Contraseña Modificada Correctamente"));
                
                }else{
                      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "AVISO", "Cedula o Contraseña Incorrectas"));
      
                }
               
            }
        }catch(Exception e){
                     //   System.err.println(e.getMessage());
                    //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
      
        }
        
        return "login";
    }
    
    
    public void checkId()
    {
        for (UsuarioInge i : facade.findAll())
        {
            if (!i.getCedula().equals(user.getCedula()))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Cedula ya existe."));
                break;
            }
        }

    }
    
}
    
  