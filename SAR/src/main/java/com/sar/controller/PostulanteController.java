/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.sar.model.Estado;
import com.sar.model.Postulante;
import com.sar.model.Requisicion;
<<<<<<< HEAD
<<<<<<< HEAD
import org.primefaces.context.RequestContext;
=======
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
=======
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
import com.sar.session.EstadoFacadeLocal;
import com.sar.session.PostulanteFacadeLocal;
<<<<<<< HEAD
<<<<<<< HEAD
import com.sar.session.RequisicionFacadeLocal;
=======
=======
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac

/**
 *
 * @author Luis Alejandro
 */
@Named(value = "posController")
@RequestScoped
public class PostulanteController implements Serializable {

     @EJB
    private EstadoFacadeLocal estadoFacade;

    @EJB
    private RequisicionFacadeLocal rFacade;

    @EJB
    private PostulanteFacadeLocal pFacade;
  

    private Postulante p = new Postulante();
    private Estado e = new Estado();
    private Requisicion r = new Requisicion();
    private List<String> licencias;
    private List<String> provincias;
    private String[] selectedCities;
    private List<Postulante> aux = new ArrayList<Postulante>();
    private List<Requisicion> requisicion = new ArrayList<Requisicion>();
    boolean flag = true;
    private Postulante[] selected;
    
<<<<<<< HEAD
=======
     
   private Postulante p = new Postulante();
   private Lugares l = new Lugares();
   private Estado e = new Estado();
   private Requisicion r = new Requisicion();
   private List<String> licencias;
   private String[] selectedCities;
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac

    public String[] getSelectedCities() {
        return selectedCities;
    }

    public void setSelectedCities(String[] selectedCities) {
        this.selectedCities = selectedCities;
    }

    public List<String> getLicencias() {
        return licencias;
    }

    public void setLicencias(List<String> licencias) {
        this.licencias = licencias;
    }

    public List<String> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<String> provincias) {
        this.provincias = provincias;
    }
   

    public Postulante getP() {
        return p;
    }

    public void setP(Postulante p) {
        this.p = p;
    }

    public Estado getE() {
        return e;
    }

    public void setE(Estado e) {
        this.e = e;
    }

    public Requisicion getR() {
        return r;
    }

    public void setR(Requisicion r) {
        this.r = r;
    }

    public Postulante[] getSelected() {
        return selected;
    }

    public void setSelected(Postulante[] selected) {
        this.selected = selected;
    }

    /**
     * Creates a new instance of cursoController
     */
    public PostulanteController() {
        inicio();

    }

    public List<Postulante> listar() {
        return this.pFacade.findAll();
    }
<<<<<<< HEAD

    public String agregar() {
        try {
            r.setNumrequisicion(BigDecimal.valueOf(100));
            //System.out.println(estadoFacade.find("000").getDetalle());
            e.setCodigoEstado("001");
           
           
=======
    
    public String agregar(){
        try{
            r.setNumrequisicion(BigDecimal.ZERO);
            this.p.setEstado(e);
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
            this.p.licencia = funcione();
            this.p.setEstado(this.e);  
            this.p.setRequisicion(this.r);
            System.out.println(this.p.getRequisicion().getNumrequisicion());
            System.out.println(this.p.getEstado().getCodigoEstado());
            this.pFacade.create(this.p);

            selectedCities = null;
            this.e = new Estado();
            this.p = new Postulante();
            this.r = new Requisicion();
            RequestContext req = RequestContext.getCurrentInstance();
            req.execute("PF('widModify').hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "REGISTRADO EXITOSAMENTE"));

            

        } catch (Exception e) {
            System.out.println(e.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "REGISTRADO EXITOSAMENTE"));

        }
        return "Postulante";
    }

    public void delete(Postulante al) {
        try{
            this.pFacade.remove(al);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "ELIMINADO EXITOSAMENTE"));
        } catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
        }
    }

    public String modify() {
        try{
        //falta requisicion
      this.p.setEstado(this.e);
        this.p.setRequisicion(this.r);
        System.out.println(r.getNumrequisicion());
        System.out.println(e.getCodigoEstado());
        this.pFacade.edit(this.p);
        this.p = new Postulante();
        this.e = new Estado();
        RequestContext req = RequestContext.getCurrentInstance();
        req.execute("PF('modify').hide();");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE MODIFICO CORRECTAMENTE"));

        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
        }
        
        /*
         Definir tod en  la base de datos mayuscula o minuscula
         
         */
        return "Postulante";
    }

    //falta modificar , falta campo area, falta 
    public String edit(Postulante cur) {
        this.p = cur;
        this.e = cur.getEstado();
        this.r = cur.getRequisicion();
        //return add
        return "Postulante";
    }

    public String funcione() {
        StringBuilder builder = new StringBuilder();
        int c = 1;
        for (String s : selectedCities) {
            builder.append(s);
            if (c < selectedCities.length) {
                builder.append(" ");
                c++;
            }
        }
        String str = builder.toString();
        System.out.println(builder);
        //  pos.getP().licencia = str;
        return str;
    }

    public void inicio() {
        licencias = new ArrayList<String>();
        licencias.add("A2");
        licencias.add("A3");
        licencias.add("B1");
        licencias.add("B2");
        licencias.add("B3");
        licencias.add("B4");
        licencias.add("E1");
        licencias.add("E2");
       
        provincias = new ArrayList<String>();
        provincias.add("San Jose");
        provincias.add("Alajuela");
        provincias.add("Heredia");
        provincias.add("Cartago");
        provincias.add("Puntarenas");
        provincias.add("Limon");
        provincias.add("Guanacaste");
      
    }

    public List<Postulante> loadPostulantes() {
        if (flag) {
            //    postulantes = postulanteFacade.findAll();
            for (Postulante i : pFacade.findAll()) {
                if (i.getEstado().getDetalle().equals("NINGUNO")) {
                    // System.out.println(i.getEstado().getDetalle());
                    aux.add(i);
                }

            }
        }
        flag = false;
        return aux;
    }

    public List<Requisicion> loadRequisiciones() {
        for (Requisicion i : rFacade.findAll()) {
            if (i.getNumrequisicion().compareTo(BigDecimal.valueOf(100)) == 1) {
                requisicion.add(i);
            }
        }
        return requisicion;
    }
    
      public void checkId() {
          System.out.println(p.getCedula());
        for (Postulante i : pFacade.findAll()) {
            if (i.getCedula().equals(p.getCedula())) {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Cedula ya existe."));
                 break;
            }
        }
       
    }

    public String relacionarPostulantes() {

        for (Postulante pos : selected) {
           System.out.println(pos.getCedula());
            System.out.println(this.r.getNumrequisicion());
            pos.setRequisicion(r);
            e.setCodigoEstado("002");
            pos.setEstado(this.e);
            pFacade.edit(pos);
           //RequestContext req = RequestContext.getCurrentInstance();
           // req.execute("PF('tableDialog').hide();");
        }
        return "addRequisicion";
    }
    
    
  
    
   

    
   


}