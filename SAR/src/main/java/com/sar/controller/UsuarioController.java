/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.controller;

import com.sar.model.UsuarioInge;
import com.sar.session.UsuarioIngeFacadeLocal;
import java.io.Serializable;
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

    private UsuarioInge  user;

    @EJB
    private UsuarioIngeFacadeLocal facade;

    @PostConstruct
    public void init()
    {
        user = new UsuarioInge();

        System.out.println("aqui voy");
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
            facade.create(user);
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

    public void edit(UsuarioInge c)
    {
        this.user = c;
        //return "editar";
    }

    public void reset()
    {
        RequestContext.getCurrentInstance().reset("formCrear:panel");
    }
}
