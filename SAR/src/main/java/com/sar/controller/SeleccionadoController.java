/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.controller;

import com.sar.model.Entrevistados;
import com.sar.model.Evaluados;
import com.sar.model.Postulante;
import com.sar.session.EvaluadosFacadeLocal;
import com.sar.session.PostulanteFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author manuel
 */
@Named(value = "seleccionadoController")
@RequestScoped
public class SeleccionadoController implements Serializable {

    @EJB 
    private EvaluadosFacadeLocal eFacade;
      @EJB
    private PostulanteFacadeLocal pFacade;
      
    private Postulante p = new Postulante();
    private Evaluados evaluate = new Evaluados();
    private String respuesta;
    private String tipo;
    private Postulante seleccionado;
    private List<Evaluados> aux = new ArrayList();
   
    public SeleccionadoController() {
        this.tipo = "";
        this.respuesta = "";
    }

    public Postulante getP() {
        return p;
    }

    public void setP(Postulante p) {
        this.p = p;
    }

    public Evaluados getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Evaluados evaluate) {
        this.evaluate = evaluate;
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
    
   
    public List<Evaluados> listSeleccionados() {
         for (Evaluados i : eFacade.findAll())
        {
            if (i.getPostulante().getEstado().getCodigoEstado().equals("005"))
            {
               aux.add(i);
            }
        }
        return aux;
       // return eFacade.findAll();
    }
    public void searchPostulante(){
        for(Postulante pos: pFacade.findAll()){
           if(pos.getCedula().equals(respuesta)){
              this.p = pos;
               break;
           }
       } 
    }

    public String agregarSeleccionado() {
    try{
        searchPostulante();
        System.out.println(this.p.getCedula());
        System.out.println(tipo);
        evaluate.setPrioridad(Short.valueOf(tipo));
        evaluate.setPostulante(this.p);
        eFacade.create(this.evaluate);
        System.out.println("paso");
        this.p = new Postulante();
        this.evaluate = new Evaluados();
        this.respuesta = " ";
        this.tipo = "";
        RequestContext req = RequestContext.getCurrentInstance();
        req.execute("PF('select').hide();");
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE AGREGÓ A SELECCIONADOS EXITOSAMENTE"));

        } catch (Exception e)
        {
            System.out.println(e.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "POSTULANTE EXISTENTE"));

        }
        return "Seleccionados";
    }
    
        public String modify()
    {
        try
        {
            //falta requisicion
            searchPostulante();
            System.out.println(p.getCedula()); 
        for(Evaluados evaluado : eFacade.findAll()){
            if(evaluado.getPostulante().getCedula().equals(this.p.getCedula())){
                this.evaluate = evaluado;
                
                break;
            }
        }
            System.out.println(evaluate.getCodigo());
            this.evaluate.setPostulante(this.p);
            this.eFacade.edit(this.evaluate);
            this.p = new Postulante();
            this.evaluate = new Evaluados();
            RequestContext req = RequestContext.getCurrentInstance();
            req.execute("PF('dates').hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE MODIFICO CORRECTAMENTE"));

        } catch (Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR AL MODIFICAR"));
        }

        /*
         Definir tod en  la base de datos mayuscula o minuscula

         */
        return "Seleccionados";
    }
 
       public void checkSeleccionado(Postulante pos){
      for (Evaluados i : eFacade.findAll())
        {
            if (i.getPostulante().getCedula().equals(pos.getCedula()))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!", "YA FUE SELECCIONADO."));
                break;
            }
        }

   }
    
}
