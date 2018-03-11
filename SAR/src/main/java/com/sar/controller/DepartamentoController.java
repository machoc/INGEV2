/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.controller;

import com.sar.model.Departamento;
import com.sar.session.DepartamentoFacadeLocal;
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
 * @author Luis Alejandro
 */
@Named("departamentoController")
@RequestScoped
public class DepartamentoController implements Serializable
{

    private Departamento  dep = new Departamento();

    @EJB
    private DepartamentoFacadeLocal facade;

    @PostConstruct
    public void init()
    {

        System.out.println("aqui voy");
    }

    public Departamento getDep()
    {
        return dep;
    }

    public void setDep(Departamento dep)
    {
        this.dep = dep;
    }

    public List<Departamento> listar()
    {
        return this.facade.findAll();
    }

    public void limpiar()
    {
        //mejorar con cni
      
    }

   public void delete(Departamento dep)
    {
        try
        {
            this.facade.remove(dep);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "ELIMINADO EXITOSAMENTE"));
        } catch (Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
        }
    }

     public String agregar()
    {
        try
        {
            this.facade.create(this.dep);

            this.dep = new Departamento();

            RequestContext req = RequestContext.getCurrentInstance();
            req.execute("PF('widModify').hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "REGISTRADO EXITOSAMENTE"));

        } catch (Exception e)
        {
            System.out.println(e.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "POSTULANTE EXISTENTE"));

        }
        return "departamentos";
    }

     public String modify()
    {
        try
        {
            this.facade.edit(this.dep);
            this.dep = new Departamento();
            RequestContext req = RequestContext.getCurrentInstance();
            req.execute("PF('modify').hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE MODIFICO CORRECTAMENTE"));

        } catch (Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
        }

        /*
         Definir tod en  la base de datos mayuscula o minuscula

         */
        return "departamentos";
    }


    public void edit(Departamento d)
    {
        this.dep = d;
        //return "editar";
    }

}
