/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.controller;

import com.sar.model.Entrevistados;
import com.sar.model.Estado;
import com.sar.model.Evaluados;
import com.sar.model.Postulante;
import com.sar.model.Requisicion;
import com.sar.session.EntrevistadosFacadeLocal;
import com.sar.session.EstadoFacadeLocal;
import com.sar.session.EvaluadosFacadeLocal;
import com.sar.session.PostulanteFacadeLocal;
import com.sar.session.RequisicionFacadeLocal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
@Named(value = "posController")
@RequestScoped
public class PostulanteController implements Serializable
{

    @EJB
    private EstadoFacadeLocal estadoFacade;
    @EJB
    private EvaluadosFacadeLocal evaluateFacade;
    @EJB
    private RequisicionFacadeLocal rFacade;

    @EJB
    private PostulanteFacadeLocal pFacade;
    
    @EJB
    private EntrevistadosFacadeLocal eFacade;

    private Postulante p = new Postulante();
    private Estado e = new Estado();
    private Requisicion r = new Requisicion();
    private Evaluados seleccionate = new Evaluados();
    private List<String> licencias;
    private List<String> provincias;
    private String[] selectedCities;
    private List<Postulante> aux = new ArrayList<Postulante>();
    private List<Requisicion> requisicion = new ArrayList<Requisicion>();
    boolean flag = true;
    private Postulante[] selected;
    private String note;
    private String prueba;

    public String[] getSelectedCities()
    {
        return selectedCities;
    }

    public void setSelectedCities(String[] selectedCities)
    {
        this.selectedCities = selectedCities;
    }

    public List<String> getLicencias()
    {
        return licencias;
    }

    public void setLicencias(List<String> licencias)
    {
        this.licencias = licencias;
    }

    public List<String> getProvincias()
    {
        return provincias;
    }

    public void setProvincias(List<String> provincias)
    {
        this.provincias = provincias;
    }

    public Postulante getP()
    {
        return p;
    }

    public void setP(Postulante p)
    {
        this.p = p;
    }

    public Estado getE()
    {
        return e;
    }

    public void setE(Estado e)
    {
        this.e = e;
    }

    public Requisicion getR()
    {
        return r;
    }

    public void setR(Requisicion r)
    {
        this.r = r;
    }

    public Postulante[] getSelected()
    {
        return selected;
    }

    public void setSelected(Postulante[] selected)
    {
        this.selected = selected;
    }

    /**
     * Creates a new instance of cursoController
     */
    public PostulanteController()
    {
        inicio();
        note = "";
        prueba = "";


    }
    
     public String getNote() {
        return note;
    }

    public String getPrueba() {
        return prueba;
    }
    

    public void setNote(String note) {
        this.note = note;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public List<Postulante> listar()
    {
        return this.pFacade.findAll();
    }

    public String agregar()
    {
        try
        {
            r.setNumrequisicion(BigDecimal.valueOf(100));
            //System.out.println(estadoFacade.find("000").getDetalle());
            this.e.setCodigoEstado("000");

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

        } catch (Exception e)
        {
            System.out.println(e.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "POSTULANTE EXISTENTE"));

        }
        return "Postulante";
    }

    public void delete(Postulante al)
    {
        try
        {
            this.pFacade.remove(al);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "ELIMINADO EXITOSAMENTE"));
        } catch (Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
        }
    }

    public String modify()
    {
        try
        {
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

        } catch (Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
        }

        /*
         Definir tod en  la base de datos mayuscula o minuscula

         */
        return "Postulante";
    }

    //falta modificar , falta campo area, falta
    public String edit(Postulante cur)
    {
        this.p = cur;
        this.e = cur.getEstado();
        this.r = cur.getRequisicion();
        //return add
        return "Postulante";
    }

    public String funcione()
    {
        StringBuilder builder = new StringBuilder();
        int c = 1;
        for (String s : selectedCities)
        {
            builder.append(s);
            if (c < selectedCities.length)
            {
                builder.append(" ");
                c++;
            }
        }
        String str = builder.toString();
        System.out.println(builder);
        //  pos.getP().licencia = str;
        return str;
    }

    public void inicio()
    {
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
        provincias.add("San José");
        provincias.add("Alajuela");
        provincias.add("Heredia");
        provincias.add("Cartago");
        provincias.add("Puntarenas");
        provincias.add("Limón");
        provincias.add("Guanacaste");

    }

    public List<Postulante> loadPostulantes()
    {
        if (flag)
        {
            //    postulantes = postulanteFacade.findAll();
            for (Postulante i : pFacade.findAll())
            {
                if (i.getEstado().getDetalle().equals("NINGUNO"))
                {
                    // System.out.println(i.getEstado().getDetalle());
                    aux.add(i);
                }

            }
        }
        flag = false;
        return aux;
    }

    public List<Requisicion> loadRequisiciones()
    {
        for (Requisicion i : rFacade.findAll())
        {
            if (i.getNumrequisicion().compareTo(BigDecimal.valueOf(100)) == 1)
            {
                requisicion.add(i);
            }
        }
        return requisicion;
    }

    public void checkId()
    {
        System.out.println(p.getCedula());
        for (Postulante i : pFacade.findAll())
        {
            if (i.getCedula().equals(p.getCedula()))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Cedula ya existe."));
                break;
            }
        }

    }

    public String relacionarPostulantes()
    {
        try{

        for (Postulante pos : selected)
        {
            System.out.println(pos.getCedula());
            System.out.println(this.r.getNumrequisicion());
            pos.setRequisicion(r);
            e.setCodigoEstado("001");
            pos.setEstado(this.e);
            pFacade.edit(pos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE ASIGNÓ EXITOSAMENTE"));

            //RequestContext req = RequestContext.getCurrentInstance();
            // req.execute("PF('tableDialog').hide();");
        }
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
        }
        return "addRequisicion";
    }
    
    public String changeToEvaluate(){
        System.out.println(this.p.getCedula());
        for(Postulante pos : pFacade.findAll()){
            if(pos.getCedula().equals(this.p.getCedula())){
            
                pos.setNotas(note);
                pos.setDoprueba(prueba);
                
                pFacade.edit(pos);
               // System.out.println("paso");
                break;
            }
        }
         this.p = new Postulante();
         note = "";
         prueba= "";     
         RequestContext req = RequestContext.getCurrentInstance();
        req.execute("PF('modify').hide();");
        /*
         this.p.setNotas(notes);
       this.p.setDoprueba(s);*/
        return "Evaluados";
    }
    
  public String cambiar(){
        for(Postulante pos : pFacade.findAll()){
            if(pos.getCedula().equals(this.p.getCedula())){
            
                pos.setNotas(note);
                pFacade.edit(pos);
                break;
            }
        }
         this.p = new Postulante();
         RequestContext req = RequestContext.getCurrentInstance();
         req.execute("PF('modify2').hide();");
         return "Entrevistados";
    }
   
     public void cambiarEstados1(Postulante pos){
         try{
         boolean bandera = false;
        
         //falta validaciones
         if(pos.getEstado().getCodigoEstado().equals("001")){
            e.setCodigoEstado("002");
            pos.setEstado(this.e);
            pFacade.edit(pos);
            bandera = true;
         }else if(pos.getEstado().getCodigoEstado().equals("002") && pos.getDoprueba().equals("SI")){
             e.setCodigoEstado("003");
            pos.setEstado(this.e);
            pFacade.edit(pos);
            bandera = true;
         }else if(pos.getEstado().getCodigoEstado().equals("003")){
            for(Entrevistados est : eFacade.findAll() ){
                if(est.getPostulante().getCedula().equals(pos.getCedula())){
                    e.setCodigoEstado("004");
                    pos.setEstado(this.e);
                    pFacade.edit(pos);
                    bandera = true;
                    break;
                }
         } 
            
         }
         
        if(!bandera) System.out.println("ALERTA DE MENSAJE");//DEBERIA DE MOSTRAR MENSAJE
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE CAMBIÓ EXITOSAMENTE"));

         }
         catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
        }
         
     }
     
     public String cambiarEstados2(Postulante pos){
         try{
         boolean bandera = false;
        
         //falta validaciones
         if(pos.getEstado().getCodigoEstado().equals("001")){
            e.setCodigoEstado("002");
            pos.setEstado(this.e);
            pFacade.edit(pos);
            bandera = true;
         }else if(pos.getEstado().getCodigoEstado().equals("002") && pos.getDoprueba().equals("SI")){
             e.setCodigoEstado("003");
            pos.setEstado(this.e);
            pFacade.edit(pos);
            bandera = true;
         }else if(pos.getEstado().getCodigoEstado().equals("003")){
            for(Entrevistados est : eFacade.findAll() ){
                if(est.getPostulante().getCedula().equals(pos.getCedula())){
                    e.setCodigoEstado("004");
                    pos.setEstado(this.e);
                    pFacade.edit(pos);
                    bandera = true;
                    break;
                }
         } 
            
         }
         
        if(!bandera) System.out.println("ALERTA DE MENSAJE");//DEBERIA DE MOSTRAR MENSAJE
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE CAMBIÓ EXITOSAMENTE"));

         }
         catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
        }
         return "Entrevistados";
     }
     
     public String cambiarEstados3(Postulante pos){
         try{
         boolean bandera = false;
        // System.out.println(pos.getNombreCompleto());
         //falta validaciones
         if(pos.getEstado().getCodigoEstado().equals("001")){
            e.setCodigoEstado("002");
            pos.setEstado(this.e);
            pFacade.edit(pos);
            bandera = true;
         }else if(pos.getEstado().getCodigoEstado().equals("002") && pos.getDoprueba().equals("SI")){
             e.setCodigoEstado("003");
            pos.setEstado(this.e);
            pFacade.edit(pos);
            bandera = true;
         }else if(pos.getEstado().getCodigoEstado().equals("003")){
            for(Entrevistados est : eFacade.findAll() ){
                if(est.getPostulante().getCedula().equals(pos.getCedula())){
                    e.setCodigoEstado("004");
                    pos.setEstado(this.e);
                    pFacade.edit(pos);
                    bandera = true;
                    break;
                }
         } 
            
         }
         
        if(!bandera) System.out.println("ALERTA DE MENSAJE");//DEBERIA DE MOSTRAR MENSAJE
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE CAMBIÓ EXITOSAMENTE"));

         }
         catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
        }
         return "addRequisicion";
     }
     
     
     public String cambiarEstados4(Postulante pos){
         
         boolean bandera = false;
         try{
      
         if(pos.getEstado().getCodigoEstado().equals("004") ){
            for(Evaluados eva: evaluateFacade.findAll() ){
                if(eva.getPostulante().getCedula().equals(pos.getCedula())){
                    System.out.println("entramos");
                    e.setCodigoEstado("005");
                    pos.setEstado(this.e);
                    pFacade.edit(pos);
                    bandera = true;
                    break;
                }
            }
     }
         if(!bandera){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "No se ha asignado prioridad"));

         }
         }catch (Exception e)
        {
            System.out.println(e.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "pOSTULANTE EXISTENTE"));

        }
         
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE CAMBIÓ EXITOSAMENTE"));
  
         return "Seleccionados";
     }
     
     public String finalizarEstados(Postulante pos){
         
         boolean bandera = false;
         try{
      
         if(pos.getEstado().getCodigoEstado().equals("005") ){
            for(Evaluados eva: evaluateFacade.findAll() ){
                if(eva.getPostulante().getCedula().equals(pos.getCedula())){
                    System.out.println("entramos");
                    e.setCodigoEstado("006");
                    pos.setEstado(this.e);
                    pFacade.edit(pos);
                    bandera = true;
                    break;
                }
            }
     }
         if(!bandera){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "No se ha asignado prioridad"));

         }
         }catch (Exception e)
        {
            System.out.println(e.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "pOSTULANTE EXISTENTE"));

        }
         
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE CAMBIÓ EXITOSAMENTE"));
  
         return "Contratados";
     }

}
