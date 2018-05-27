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
import com.sar.model.UsuarioInge;
import com.sar.session.EntrevistadosFacadeLocal;
import com.sar.session.EstadoFacadeLocal;
import com.sar.session.EvaluadosFacadeLocal;
import com.sar.session.PostulanteFacadeLocal;
import com.sar.session.RequisicionFacadeLocal;
import com.sar.session.UsuarioIngeFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
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
     @EJB
    private UsuarioIngeFacadeLocal userFacade;
   

    private Postulante p = new Postulante();
    private Estado e = new Estado();
    private UsuarioInge user = new UsuarioInge();
    private Requisicion r = new Requisicion();
    private Evaluados seleccionate = new Evaluados();
    private List<String> licencias;
    private List<String> provincias;
    private String[] selectedCities;
    private List<Postulante> aux = new ArrayList<Postulante>();
     private List<Postulante> filtered= new ArrayList<Postulante>();
   
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

    public List<Postulante> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Postulante> filtered) {
        this.filtered = filtered;
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
            e.setCodigoEstado("000");

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
        licencias.add("B1");
        licencias.add("B2");
        licencias.add("B3");
        licencias.add("B4");
        licencias.add("E1");
        licencias.add("E2");
        licencias.add("D1");
        licencias.add("D2");
        

        provincias = new ArrayList<String>();
        provincias.add("SAN JOSE");
        provincias.add("ALAJUELA");
        provincias.add("HEREDIA");
        provincias.add("CARTAGO");
        provincias.add("PUNTARENAS");
        provincias.add("LIMON");
        provincias.add("GUANACASTE");

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
            if (i.getNumrequisicion().compareTo(BigDecimal.valueOf(100)) == 1 && i.getEstado().equals("ABIERTA"))
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
       public void checkPrueba(Postulante pos){
             if (pos.getDoprueba().equals("SI"))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!", "YA REALIZO LA PRUEBA."));
                
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
           FacesContext context = FacesContext.getCurrentInstance();
          context.getExternalContext().getFlash().setKeepMessages(true);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE ASIGNÓ EXITOSAMENTE"));
            reload();
            //RequestContext req = RequestContext.getCurrentInstance();
            // req.execute("PF('tableDialog').hide();");
        }
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
        }
        return "asignarPostulantes";
    }
    
    public String changeToEvaluate(){
        try{
        System.out.println(this.p.getCedula());
        for(Postulante pos : pFacade.findAll()){
            if(pos.getCedula().equals(this.p.getCedula())){
            
                if(!note.equals("")){
                    pos.setNotas(note);
                }
                pos.setDoprueba(prueba);
                
                pFacade.edit(pos);
               // System.out.println("paso");
               reload();
                break;
            }
        }
         this.p = new Postulante();
         note = "";
         prueba= "";     
         RequestContext req = RequestContext.getCurrentInstance();
        req.execute("PF('modify').hide();");
        } catch (Exception e)
        {
            System.out.println(e.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "POSTULANTE EXISTENTE"));

        }
        return "Evaluados";
    }
    
  public void cambiar(){
        try {
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
            reload();
            // return "Entrevistados";
        } catch (IOException ex) {
            Logger.getLogger(PostulanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public String cambiarEstados1(Postulante pos) {
        try {
            boolean bandera = false;

            //falta validaciones
            if (pos.getEstado().getCodigoEstado().equals("001")) {
                e.setCodigoEstado("002");
                pos.setEstado(this.e);
                pFacade.edit(pos);
                bandera = true;
                  FacesContext context = FacesContext.getCurrentInstance();
                    context.getExternalContext().getFlash().setKeepMessages(true);
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE CAMBIÓ EXITOSAMENTE"));
                  reload();
              
            }
            if (!bandera) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "No se ha asignado prioridad"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "POSTULANTE EXISTENTE"));
        }

        return "estados";
    }
   
     
     public String cambiarEstados2(Postulante pos){
         try{
         boolean bandera = false;
        
       if(pos.getEstado().getCodigoEstado().equals("002") && pos.getDoprueba().equals("SI")){
             e.setCodigoEstado("003");
            pos.setEstado(this.e);
            pFacade.edit(pos);
            bandera = true;
               FacesContext context = FacesContext.getCurrentInstance();
                   context.getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE CAMBIÓ EXITOSAMENTE"));
            reload();
            
         }
       if(!bandera){
                   FacesContext context = FacesContext.getCurrentInstance();
                   context.getExternalContext().getFlash().setKeepMessages(true);
      
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "NO SE HA ASIGNADO PRIORIDAD"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
           // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "pOSTULANTE EXISTENTE"));
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "No se ha asignado prioridad"));
      
        }
            FacesContext context = FacesContext.getCurrentInstance();
                   context.getExternalContext().getFlash().setKeepMessages(true);
      
            try {
            reload();
        } catch (IOException ex) {
            Logger.getLogger(PostulanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return "Evaluados";
     }
     
     public String cambiarEstados3(Postulante pos){
         try{
         boolean bandera = false;
        // System.out.println(pos.getNombreCompleto());
         //falta validaciones
         if(pos.getEstado().getCodigoEstado().equals("003")){
            for(Entrevistados est : eFacade.findAll() ){
                if(est.getPostulante().getCedula().equals(pos.getCedula())){
                    e.setCodigoEstado("004");
                    pos.setEstado(this.e);
                    pFacade.edit(pos);
                    bandera = true;
                    reload();
                    break;
                }
         } 
            
         }
         if (!bandera) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "No se ha asignado prioridad"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "pOSTULANTE EXISTENTE"));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "No se ha asignado prioridad o cedula ya existente"));
            
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE CAMBIÓ EXITOSAMENTE"));

        return "Entrevistados";
         
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
                      FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().getFlash().setKeepMessages(true);
                    reload();
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
         //   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "pOSTULANTE EXISTENTE"));
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "No se ha asignado prioridad o cedula ya existente"));
        
        }
         
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE CAMBIÓ EXITOSAMENTE"));
  
         return "Seleccionados";
     }
     
     public String finalizarEstados(Postulante pos) throws IOException{
         
         boolean bandera = false;
         try{
      
         if(pos.getEstado().getCodigoEstado().equals("005") ){
            for(Evaluados eva: evaluateFacade.findAll() ){
                if(eva.getPostulante().getCedula().equals(pos.getCedula())){
                    System.out.println("entramos");
                    e.setCodigoEstado("007");
                    pos.setEstado(this.e);
                    pFacade.edit(pos);
                    bandera = true;
                    reload();
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
          //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "pOSTULANTE EXISTENTE"));
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "No se ha asignado prioridad o cedula ya existente"));
        
        }
          FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().getFlash().setKeepMessages(true);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE CAMBIÓ EXITOSAMENTE"));
        reload();
         return "Contratados";
     }
     
     

     
       public void VerificarSession(){
        try{
            UsuarioInge us = (UsuarioInge)  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if(us == null){
                   FacesContext.getCurrentInstance().getExternalContext().redirect("permisos.xhtml");
      
            }
        }catch(Exception e){
            //guardar registro de errores
        }
    }
       public void reasignar1(String cedula){
           try{
           System.out.println(cedula);

           for(Postulante pos : pFacade.findAll()){
               System.out.println(cedula);

            if(pos.getCedula().equals(cedula)){
            
                r.setNumrequisicion(BigDecimal.valueOf(100));
            //System.out.println(estadoFacade.find("000").getDetalle());
            e.setCodigoEstado("000");

            pos.setEstado(this.e);
            pos.setRequisicion(this.r);
                pFacade.edit(pos);
                break;
            }
        }
         this.p = new Postulante();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE REASIGNÓ CORRECTAMENTE"));
           }
         catch(Exception e){
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
     
                 }

       }
       
       
    public String asignarResponsable(){
        try{
             searchRequisicion();
             this.user = this.userFacade.find(this.prueba);
              this.r.setUsuario(user);
              // this.requisicionFacade.edit(r);
           
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "addRequisicion";
    }
    
    public void searchRequisicion() {
        for (Requisicion requisicionaux : this.rFacade.findAll()) {
            if (requisicionaux.getNumrequisicion().equals(this.r.getNumrequisicion())) {
                this.r = requisicionaux;
                break;
            }
        }
    }
    
    public void reload() throws IOException {
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
}
}
