/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.controller;

import com.sar.model.Evaluados;
import com.sar.model.Postulante;
import com.sar.model.Requisicion;
import com.sar.model.UsuarioInge;
import com.sar.session.PostulanteFacadeLocal;
import com.sar.session.RequisicionFacadeLocal;
import com.sar.session.UsuarioIngeFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author manuel
 */
@Named(value = "graficosController")
@RequestScoped
public class GraficosController implements Serializable {
    private BarChartModel barModel;
    private PieChartModel pieModel1;
      private List<Postulante> aux = new ArrayList<Postulante>();
     private List<Evaluados> aux2 = new ArrayList<Evaluados>();
  
    @EJB
    private RequisicionFacadeLocal requisicionFacade;
    
    @EJB
    private UsuarioIngeFacadeLocal usuarioFacade;
    
    
    @EJB
    private PostulanteFacadeLocal postulanteFacade;
    
    
    public GraficosController() {
        createBarModels();
        createPieModels();
    
    }
    
    
    public BarChartModel getBarModel() {
        return barModel;
    }
     
    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Candidatos");
        boys.set("Preseleccionados", this.preseleccionados().size());
        boys.set("Evaluados", this.evaluados().size());
        boys.set("Entrevistados", this.entrevistados().size());
        boys.set("Seleccionados", this.seleccionados().size());
        boys.set("Contratados", this.listContratados().size());
        boys.set("Descartados", this.descartados().size());
 
        model.addSeries(boys);
         
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
        
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Estados de los Candidatos");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Estados");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
    
   
    //
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
     
     
    private void createPieModels() {
        createPieModel1();
     
    }
 
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
         //hacerlo dinamico
         int contador=0;
         for(UsuarioInge user : usuarioFacade.findAll()){
             for(Requisicion requis : requisicionFacade.findAll()){
                 if(requis.getUsuario().getCedula().equals(user.getCedula())){
                     contador++;
                 }
            
             }
              pieModel1.set(user.getNombre(), contador);
              contador=0;
         }
         
        pieModel1.setTitle("Usuarios");
        pieModel1.setLegendPosition("w");
    }
     
       public List<Postulante> preseleccionados() {
        aux = new ArrayList<Postulante>();
        for (Postulante i : postulanteFacade.findAll()) {
            if (i.getEstado().getDetalle().equals("PRESELECCIONADO")) {
                aux.add(i);
            }
        }
        return aux;
    }

    public List<Postulante> evaluados() {
        aux = new ArrayList<Postulante>();
        for (Postulante i : postulanteFacade.findAll()) {
            if (i.getEstado().getDetalle().equals("EVALUADO")) {
                aux.add(i);
            }
        }
        return aux;
    }

    public List<Postulante> entrevistados() {
        aux = new ArrayList<Postulante>();
        for (Postulante i : postulanteFacade.findAll()) {
            if (i.getEstado().getDetalle().equals("ENTREVISTADO")) {
                aux.add(i);
            }
        }
        return aux;
    }

    public List<Postulante> seleccionados() {
        aux = new ArrayList<Postulante>();
        aux = new ArrayList();
        for (Postulante i : postulanteFacade.findAll()) {
            if (i.getEstado().getDetalle().equals("SELECCIONADO")) {
                aux.add(i);
            }
        }
        return aux;
    }

    public List<Postulante> listContratados() {

        aux = new ArrayList<Postulante>();
        for (Postulante i : postulanteFacade.findAll()) {
            if (i.getEstado().getDetalle().equals("CONTRATADO")) {
                aux.add(i);
            }
        }
        return aux;
    }
    public List<Postulante> descartados() {

        aux = new ArrayList<Postulante>();
        for (Postulante i : postulanteFacade.findAll()) {
            if (i.getEstado().getDetalle().equals("DESCARTADO")) {
                aux.add(i);
            }
        }
        return aux;
    }

     
}
