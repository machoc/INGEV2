/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.controller;

import com.sar.model.Departamento;
import com.sar.model.Estado;
import com.sar.model.Postulante;
import com.sar.model.Requisicion;
import com.sar.model.UsuarioInge;
import com.sar.session.EstadoFacadeLocal;
import com.sar.session.PostulanteFacadeLocal;
import com.sar.session.RequisicionFacadeLocal;
import com.sar.session.UsuarioIngeFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
 * @author manuel
 */
@Named(value = "requisicionController")
@RequestScoped
public class requisicionController implements Serializable
{

    @EJB
    private RequisicionFacadeLocal requisicionFacade;
    @EJB
    private PostulanteFacadeLocal postulanteFacade;
    @EJB
    private UsuarioIngeFacadeLocal userFacade;
    @EJB
    private EstadoFacadeLocal estadoFacade;

    private Requisicion r = new Requisicion();
    private Departamento d = new Departamento();
    private Postulante p = new Postulante();
    private Estado estado = new Estado();
    private UsuarioInge user = new UsuarioInge();
    private List<Postulante> aux = new ArrayList<Postulante>();
    private List<Postulante> filtered = new ArrayList<Postulante>();
    private Postulante seleccionado;
    private List<Requisicion> requisicion = new ArrayList<Requisicion>();
    boolean flag = true;
    private String cedulaUsuario= "";
    public requisicionController()
    {
     
    }

    public List<Postulante> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Postulante> filtered) {
        this.filtered = filtered;
    }
    
    
    
     public List<Requisicion> listar() {
        return this.requisicionFacade.findAll();
        
    }

    public Requisicion getR()
    {
        return r;
    }

    public void setR(Requisicion r)
    {
        this.r = r;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }


    public Departamento getD()
    {
        return d;
    }

    public void setD(Departamento d)
    {
        this.d = d;
    }

    public Postulante getP()
    {
        return p;
    }

    public void setP(Postulante p)
    {
        this.p = p;
    }

    public Estado getEstado()
    {
        return estado;
    }

    public void setEstado(Estado e)
    {
        this.estado = e;
    }
    
    public UsuarioInge getUser() {
        return user;
    }

    public Postulante getSeleccionado()
    {
        return seleccionado;
    }

    public void setSeleccionado(Postulante seleccionado)
    {
        this.seleccionado = seleccionado;
    }

 /*  public List<Requisicion> listar() {
        for (Requisicion i : requisicionFacade.findAll()) {
            if (i.getNumrequisicion().compareTo(BigDecimal.valueOf(100)) == 1) {
                requisicion.add(i);
            }
        }
        return requisicion;
    }*/
      
   


    public String agregar() {
        try {
            this.r.setEstado("ABIERTA");
            this.r.setDepartamento(this.d);
          //  System.out.println(r.getNumrequisicion());   
              //  System.out.println(sirva[0].getCedula());
              this.user = userFacade.find("20764013");
            System.out.println(user.getNombre());
              this.r.setUsuario(this.user);
             requisicionFacade.create(this.r);
             r = new Requisicion();
             d = new Departamento();
             user = new UsuarioInge();
              RequestContext req = RequestContext.getCurrentInstance();
            req.execute("PF('widAdd').hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "AGREGADA EXITOSAMENTE"));

            
       } catch (Exception e) {
            System.out.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR AL AGREGAR"));

        }
        return "addRequisicion";
    }

    public List<Postulante> preseleccionados(){
               for (Postulante i : postulanteFacade.findAll()) {
            if (i.getEstado().getDetalle().equals("PRESELECCIONADO")) {
                aux.add(i);
            }
        }
        return aux;
    }
    
    public List<Postulante> evaluados(){
               for (Postulante i : postulanteFacade.findAll()) {
            if (i.getEstado().getDetalle().equals("EVALUADO")) {
                aux.add(i);
            }
        }
        return aux;
    }
    
    public List<Postulante> entrevistados(){
               for (Postulante i : postulanteFacade.findAll()) {
            if (i.getEstado().getDetalle().equals("ENTREVISTADO")/* && i.getRequisicion().getEstado().equals("ABIERTA")*/) {
                aux.add(i);
            }
        }
        return aux;
    }
    public List<Postulante> seleccionados(){
                aux = new ArrayList();
               for (Postulante i : postulanteFacade.findAll()) {
            if (i.getEstado().getDetalle().equals("SELECCIONADO")) {
                aux.add(i);
            }
        }
        return aux;
    }
    
      public void delete(Requisicion req) {
        
        try{
            initRequisiciones(req);
            this.requisicionFacade.remove(req);
             FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "ELIMINADO EXITOSAMENTE"));
            reload();
        } catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
        }
    }
        
        
      

    public String modify()
    {
        try
        {
            //falta requisicion
            this.r.setDepartamento(this.d);
            this.r.setUsuario(this.user);
           this.requisicionFacade.edit(this.r);
            this.r = new Requisicion();
            this.user = new UsuarioInge();
            this.d = new Departamento();
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
        return "addRequisicion";
    }

    //falta modificar , falta campo area, falta
    public String edit(Requisicion cur)
    {
        this.r = cur;
        this.d = cur.getDepartamento();
        this.user = cur.getUsuario();
        //return add
        return "addRequisicion";
    }    
    public List<Requisicion> listFilter() {
       
          if (flag) {
            //    postulantes = postulanteFacade.findAll();
             for (Requisicion i : requisicionFacade.findAll()) {
            if (i.getNumrequisicion().compareTo(BigDecimal.valueOf(100)) == 1) 
                requisicion.add(i);
            
        }

            }
        
        flag = false;
        return requisicion;
    }
       /*         for(Postulante pos : this.postulanteFacade.findAll()) {
                    if(pos.getRequisicion().equals(req)){
                        
                        pos.setEstado(new Estado("000","NINGUNO"));
                        postulanteFacade.edit(pos);
                        break;
                    }*/
     
      public String cerrarRequisicion(String num){
         try{
           System.out.println(num);

           for(Requisicion req : requisicionFacade.findAll()){
               System.out.println(num);
              
            if(req.getNumrequisicion().toString().equals(num)){
            
                req.setEstado("CERRADA");
                requisicionFacade.edit(req);
              reasignarPostulantes(req);
             
                break;
            }
        }
         this.r = new Requisicion();
         FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE CERRÓ CORRECTAMENTE"));
          reload();   
         }
         catch(Exception e){
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
     
                 }
         
        
        return "addRequisicion";
    }
      
    public void reasignarPostulantes(Requisicion requisicion){
        for(Postulante pos : postulanteFacade.findAll()){
            if(pos.getRequisicion().equals(requisicion) && !pos.getEstado().getDetalle().equals("FINALIZADO")){
                this.p = pos;
              //  estado.setCodigoEstado("000");
              estado = this.estadoFacade.find("000");
                this.p.setEstado(this.estado);
                this.p.setDoprueba("");
            System.out.println(this.p.getEstado().getCodigoEstado());
            this.postulanteFacade.edit(this.p);

            }
        }
    }
    
    public String asignarResponsable(){
        try{
             searchRequisicion();
            // this.user= userFacade.find(user.getCedula());
              this.r.setUsuario(this.user);
               this.requisicionFacade.edit(this.r);
           
        this.r = new Requisicion();
        RequestContext req = RequestContext.getCurrentInstance();
            req.execute("PF('ModifyUser').hide();");
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "USUARIO MODIFICADO"));
           }
         catch(Exception e){
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
     
                 }
        
        return "addRequisicion";
    }
    
    public void searchRequisicion() {
        for (Requisicion requisicionaux : requisicionFacade.findAll()) {
            if (requisicionaux.getNumrequisicion().equals(this.r.getNumrequisicion())) {
                this.r = requisicionaux;
                break;
            }
        }

    }
    
       public void reload() throws IOException {
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE CERRÓ CORRECTAMENTE"));
    
}
       public void initRequisiciones(Requisicion requisicion){
        for(Postulante pos : postulanteFacade.findAll()){
            if(pos.getRequisicion().equals(requisicion) ){
                this.p = pos;
              //  estado.setCodigoEstado("000");
              estado = this.estadoFacade.find("000");
              r = this.requisicionFacade.find(BigDecimal.valueOf(100));
                this.p.setEstado(this.estado);
                this.p.setRequisicion(this.r);
            System.out.println(this.p.getEstado().getCodigoEstado());
            this.postulanteFacade.edit(this.p);

            }
        }
    }
    
}
