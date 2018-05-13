/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author manuel
 */
public class ReporteUsuarios {
    BigDecimal numRequisicion;
    String puesto;
    String fechaInicio;
    String nombreUsuario;

    public ReporteUsuarios(BigDecimal numRequisicion, String puesto, String fechaInicio, String nombreUsuario) {
        this.numRequisicion = numRequisicion;
        this.puesto = puesto;
        this.fechaInicio = fechaInicio;
        this.nombreUsuario = nombreUsuario;
    }

    public BigDecimal getNumRequisicion() {
        return numRequisicion;
    }

    public void setNumRequisicion(BigDecimal numRequisicion) {
        this.numRequisicion = numRequisicion;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public String toString() {
        return "["+ fechaInicio +"-"+ setNombre()+ ']';
    }
    public ReporteUsuarios(){
        
    }
    
    
    
    public String setNombre(){
        String name ="";
        for(int i=0; i<nombreUsuario.length();i++){
            if(nombreUsuario.charAt(i) != ' '){
                name += nombreUsuario.charAt(i);
            }else{
                break;
            }
        }
        return name;
    }
    
}
