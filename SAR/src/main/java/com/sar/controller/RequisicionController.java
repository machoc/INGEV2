/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import com.sar.model.Departamento;
import com.sar.model.Estado;
import com.sar.model.Postulante;
import com.sar.model.Requisicion;
import com.sar.session.PostulanteFacadeLocal;
import com.sar.session.RequisicionFacadeLocal;

/**
 *
 * @author manuel
 */
@Named(value = "requisicionController")
@RequestScoped
public class RequisicionController implements Serializable {

    @EJB
    private RequisicionFacadeLocal requisicionFacade;
    @EJB
    private PostulanteFacadeLocal postulanteFacade;

    private Requisicion r = new Requisicion();
    private Departamento d = new Departamento();
    private Postulante p = new Postulante();
    private Estado estado = new Estado();
    private List<Postulante> postulantes;
    private List<Postulante> aux = new ArrayList<Postulante>();
    private boolean flag= true;
     private Postulante selectedCar;
  
    public RequisicionController() {
       
    }

    public Requisicion getR() {
        return r;
    }

    public void setR(Requisicion r) {
        this.r = r;
    }

    public Departamento getD() {
        return d;
    }

    public void setD(Departamento d) {
        this.d = d;
    }

    public Postulante getP() {
        return p;
    }

    public void setP(Postulante p) {
        this.p = p;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado e) {
        this.estado = e;
    }

    public Postulante getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Postulante selectedCar) {
        this.selectedCar = selectedCar;
    }


    public String agregar() {
       try{
           this.r.setDepartamento(this.d);
           requisicionFacade.create(this.r);
           p.setRequisicion(this.r);
           estado.setCodigoEstado("evaluacion");
           p.setEstado(estado);
           
           this.r=new Requisicion();
           this.r.getPostulanteList().add(this.p);
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
        return "index";
    }
    
    public List<Requisicion> listar() {
        return this.requisicionFacade.findAll();
    }

    public void addPostulantes() {

    }

    public List<Postulante> loadPostulantes() {
       if(flag){
        postulantes = postulanteFacade.findAll();
        for (Postulante i : postulantes) {
            if (i.getEstado().getDetalle().equals("Preseleccionado")) {
               // System.out.println(i.getEstado().getDetalle());
                aux.add(i);
            }

        }
       }
        flag= false;
        return aux;
        
    }
    
   
}
