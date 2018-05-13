/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.controller;

import java.io.Serializable;
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
        boys.set("Preseleccionados", 120);
        boys.set("Evaluados", 100);
        boys.set("Entrevistados", 44);
        boys.set("Seleccionados", 150);
        boys.set("Contratados", 25);
        boys.set("Descartados", 30);
 
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
        pieModel1.set("User 1", 540);
        pieModel1.set("User 2", 325);
        pieModel1.set("User 3", 702);
        pieModel1.set("User 4", 421);
         
        pieModel1.setTitle("Usuarios");
        pieModel1.setLegendPosition("w");
    }
     
   
     
}
