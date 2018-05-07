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
import com.sar.session.PostulanteFacadeLocal;
import com.sar.session.RequisicionFacadeLocal;
import com.sar.session.UsuarioIngeFacadeLocal;
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

    private Requisicion r = new Requisicion();
    private Departamento d = new Departamento();
    private Postulante p = new Postulante();
    private Estado estado = new Estado();
    private UsuarioInge user = new UsuarioInge();
    private List<Postulante> aux = new ArrayList<Postulante>();
    private List<UsuarioInge> usuario = new ArrayList<UsuarioInge>();
    private Postulante seleccionado;
    private List<Requisicion> requisicion = new ArrayList<Requisicion>();
    boolean flag = true;

    public requisicionController()
    {

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

           /* for (Postulante pos : sirva) {
                if(pos.equals(null))
                    break;
                
                System.out.println(pos.getCedula());
                System.out.println(r.getPuesto());
                pos.setRequisicion(this.r);
                estado.setCodigoEstado("evaluacion");
                pos.setEstado(estado);
                this.r.getPostulanteList().add(pos);*/
            
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
            if (i.getEstado().getDetalle().equals("ENTREVISTADO")) {
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
        this.requisicionFacade.remove(req);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "ELIMINADO EXITOSAMENTE"));

        }
        catch(Exception e){
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));

        }
    }
      
    public String edit(Requisicion req) {
        this.r = req;
        this.d = req.getDepartamento();
        this.user = req.getUsuario();
       
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
    
    public void cerrarRequisicion(String num){
         try{
           System.out.println(num);

           for(Requisicion req : requisicionFacade.findAll()){
               System.out.println(num);

            if(req.getNumrequisicion().toString().equals(num)){
            
                req.setEstado("CERRADA");
                requisicionFacade.edit(req);
              
                break;
            }
        }
         this.r = new Requisicion();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE CERRÓ CORRECTAMENTE"));
           }
         catch(Exception e){
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "AVISO", "ERROR"));
     
                 }
        
    }
    
    public List<UsuarioInge> loadUsuarios()
    {
        for (UsuarioInge i : userFacade.findAll())
        {
            
                usuario.add(i);
            
        }
        return usuario;
    }
    
    public void cambiar(BigDecimal numero){
        for(Requisicion req : requisicionFacade.findAll()){
            if(req.getNumrequisicion().equals(numero)){
            
                req.setUsuario(user);
                requisicionFacade.edit(req);
                break;
            }
        }
         this.r = new Requisicion();
         RequestContext req = RequestContext.getCurrentInstance();
         req.execute("PF('modify7').hide();");
        // return "Entrevistados";
    }
    
}
