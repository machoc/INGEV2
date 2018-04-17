/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import com.sar.model.Departamento;
import com.sar.model.Estado;
import com.sar.model.Requisicion;
import com.sar.model.UsuarioInge;
import com.sar.session.DepartamentoFacadeLocal;
import com.sar.session.EstadoFacadeLocal;
import com.sar.session.RequisicionFacadeLocal;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author manuel
 */
@Named(value = "lController")
@RequestScoped
public class ListController implements Serializable {

    @EJB
    private DepartamentoFacadeLocal departamentoFacade;

    @EJB
    private EstadoFacadeLocal estadoFacade;

    @EJB
    private RequisicionFacadeLocal requisicionFacade;
    
    private Departamento d = new Departamento();
  

    /**
     * Creates a new instance of lcontroller
    
      public listController() { 
      }
      *  */
     
   public ListController(){
       
   }

    public Departamento getD() {
        return d;
    }

    public void setD(Departamento d) {
        this.d = d;
    }

    
    
    public List<Estado> listEstados() {
        return estadoFacade.findAll();
    }
    public List<Requisicion> listRequisiones() {
        return requisicionFacade.findAll();
    }
      public List<Departamento> listDepartamentos() {
        return departamentoFacade.findAll();
    }
    public String agregarDepartamento(){
       this.departamentoFacade.create(this.d);
       d = new Departamento();
       return "index";
    }
    
    


}
