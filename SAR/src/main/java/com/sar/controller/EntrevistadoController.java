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
import com.sar.model.Entrevistados;
import com.sar.model.Estado;
import com.sar.model.Postulante;
import com.sar.model.Requisicion;
import com.sar.session.PostulanteFacadeLocal;
import com.sar.session.EntrevistadosFacadeLocal;
import com.sar.session.EstadoFacadeLocal;
import com.sar.session.RequisicionFacadeLocal;
import java.awt.event.ActionEvent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author manuel
 */
@Named(value = "entrevistaController")
@RequestScoped
public class EntrevistadoController implements Serializable {

    @EJB
    private EntrevistadosFacadeLocal entrevistaFacade;
    @EJB
    private PostulanteFacadeLocal pFacade;
    
    private List<String> tipos;

    private Entrevistados e = new Entrevistados();
    private Postulante p = new Postulante();
    private String respuesta;
    private String tipo;
   

    public EntrevistadoController() {
        inicio();
        respuesta = "";
        tipo = "";
    }
    
     public List<String> getTipos() {
        return tipos;
    }

    public void setTipos(List<String> tipos) {
        this.tipos = tipos;
    }
    
    public void inicio()
    {
        tipos = new ArrayList<String>();
        tipos.add("Presencial");
        tipos.add("Telefonica");

    }

    
    public Entrevistados getE() {
        return e;
    }

    public void setE(Entrevistados e) {
        this.e = e;
    }

    public Postulante getP() {
        System.out.println(p.getCedula());
        return p;
    }

    public void setP(Postulante p) {
        this.p = p;

    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Entrevistados> listEntrevistados() {
        return entrevistaFacade.findAll();
    }
    public void searchPostulante(){
        for(Postulante pos: pFacade.findAll()){
           if(pos.getCedula().equals(respuesta)){
              this.p = pos;
               break;
           }
       } 
    }

    public String agregarEntrevista() {
        searchPostulante();
        System.out.println(this.p.getCedula());
       
        e.setPostulante(this.p);
        entrevistaFacade.create(this.e);
        this.p = new Postulante();
        this.e = new Entrevistados();
        RequestContext req = RequestContext.getCurrentInstance();
        req.execute("PF('dates').hide();");

        return "estados";
    }
 
    
    
   

}
