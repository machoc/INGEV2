/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Alejandro
 */
@Entity
@Table(name = "POSTULANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Postulante.findAll", query = "SELECT p FROM Postulante p")
    , @NamedQuery(name = "Postulante.findByCedula", query = "SELECT p FROM Postulante p WHERE p.cedula = :cedula")
    , @NamedQuery(name = "Postulante.findByNombreCompleto", query = "SELECT p FROM Postulante p WHERE p.nombreCompleto = :nombreCompleto")
    , @NamedQuery(name = "Postulante.findByEdad", query = "SELECT p FROM Postulante p WHERE p.edad = :edad")
    , @NamedQuery(name = "Postulante.findByExperiencia", query = "SELECT p FROM Postulante p WHERE p.experiencia = :experiencia")
    , @NamedQuery(name = "Postulante.findByLicencia", query = "SELECT p FROM Postulante p WHERE p.licencia = :licencia")
    , @NamedQuery(name = "Postulante.findByEspecialidad", query = "SELECT p FROM Postulante p WHERE p.especialidad = :especialidad")
    , @NamedQuery(name = "Postulante.findByResidencia", query = "SELECT p FROM Postulante p WHERE p.residencia = :residencia")
    , @NamedQuery(name = "Postulante.findByTelefono", query = "SELECT p FROM Postulante p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Postulante.findByNotas", query = "SELECT p FROM Postulante p WHERE p.notas = :notas")
    , @NamedQuery(name = "Postulante.findByDoprueba", query = "SELECT p FROM Postulante p WHERE p.doprueba = :doprueba")})
public class Postulante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CEDULA")
    private String cedula;
    @Size(max = 30)
    @Column(name = "NOMBRE_COMPLETO")
    private String nombreCompleto;
    @Column(name = "EDAD")
    private Short edad;
    @Size(max = 100)
    @Column(name = "EXPERIENCIA")
    private String experiencia;
    @Size(max = 5)
    @Column(name = "LICENCIA")
    public String licencia;
    @Size(max = 20)
    @Column(name = "ESPECIALIDAD")
    private String especialidad;
    @Size(max = 20)
    @Column(name = "RESIDENCIA")
    private String residencia;
    @Size(max = 12)
    @Column(name = "TELEFONO")
    private String telefono;
    @Size(max = 200)
    @Column(name = "NOTAS")
    private String notas;
    @Size(max = 2)
    @Column(name = "DOPRUEBA")
    private String doprueba;
    @JoinColumn(name = "ESTADO", referencedColumnName = "CODIGO_ESTADO")
    @ManyToOne
    private Estado estado;
    @JoinColumn(name = "REQUISICION", referencedColumnName = "NUMREQUISICION")
    @ManyToOne
    private Requisicion requisicion;
    /*private String intentomil;
    public String getintentomil() {
        return intentomil;
    }

    public void setintentomil(String intento_mil) {
        this.intentomil  = intento_mil;
    }*/

    public Postulante() {
    }

    public Postulante(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Short getEdad() {
        return edad;
    }

    public void setEdad(Short edad) {
        this.edad = edad;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getDoprueba() {
        return doprueba;
    }

    public void setDoprueba(String doprueba) {
        this.doprueba = doprueba;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Requisicion getRequisicion() {
        return requisicion;
    }

    public void setRequisicion(Requisicion requisicion) {
        this.requisicion = requisicion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Postulante)) {
            return false;
        }
        Postulante other = (Postulante) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sar.model.Postulante[ cedula=" + cedula + " ]";
    }
    
}
