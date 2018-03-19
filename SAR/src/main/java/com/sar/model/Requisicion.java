/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis Alejandro
 */
@Entity
@Table(name = "REQUISICION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requisicion.findAll", query = "SELECT r FROM Requisicion r")
    , @NamedQuery(name = "Requisicion.findByNumrequisicion", query = "SELECT r FROM Requisicion r WHERE r.numrequisicion = :numrequisicion")
    , @NamedQuery(name = "Requisicion.findByPuesto", query = "SELECT r FROM Requisicion r WHERE r.puesto = :puesto")
    , @NamedQuery(name = "Requisicion.findByVacantes", query = "SELECT r FROM Requisicion r WHERE r.vacantes = :vacantes")
    , @NamedQuery(name = "Requisicion.findByContratante", query = "SELECT r FROM Requisicion r WHERE r.contratante = :contratante")
    , @NamedQuery(name = "Requisicion.findByFechaInicio", query = "SELECT r FROM Requisicion r WHERE r.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Requisicion.findByFechaEntrega", query = "SELECT r FROM Requisicion r WHERE r.fechaEntrega = :fechaEntrega")})
public class Requisicion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @NotNull
    @GeneratedValue(generator="some_seq_name")
   @SequenceGenerator(name="some_seq_name", sequenceName="AUTO_SEQ", allocationSize=1)
    @Column(name = "NUMREQUISICION")
    private BigDecimal numrequisicion;
    @Size(max = 50)
    @Column(name = "PUESTO")
    private String puesto;
    @Column(name = "VACANTES")
    private Short vacantes;
    @Size(max = 60)
    @Column(name = "CONTRATANTE")
    private String contratante;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_ENTREGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    @JoinColumn(name = "DEPARTAMENTO", referencedColumnName = "CODIGO_DEPARTAMENTO")
    @ManyToOne
    private Departamento departamento;
    @JoinColumn(name = "USUARIO", referencedColumnName = "CEDULA")
    @ManyToOne
    private UsuarioInge usuario;
    @OneToMany(mappedBy = "requisicion")
    private Collection<Postulante> postulanteCollection;

    public Requisicion() {
    }

    public Requisicion(BigDecimal numrequisicion) {
        this.numrequisicion = numrequisicion;
    }

    public BigDecimal getNumrequisicion() {
        return numrequisicion;
    }

    public void setNumrequisicion(BigDecimal numrequisicion) {
        this.numrequisicion = numrequisicion;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Short getVacantes() {
        return vacantes;
    }

    public void setVacantes(Short vacantes) {
        this.vacantes = vacantes;
    }

    public String getContratante() {
        return contratante;
    }

    public void setContratante(String contratante) {
        this.contratante = contratante;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public UsuarioInge getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioInge usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<Postulante> getPostulanteCollection() {
        return postulanteCollection;
    }

    public void setPostulanteCollection(Collection<Postulante> postulanteCollection) {
        this.postulanteCollection = postulanteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numrequisicion != null ? numrequisicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Requisicion)) {
            return false;
        }
        Requisicion other = (Requisicion) object;
        if ((this.numrequisicion == null && other.numrequisicion != null) || (this.numrequisicion != null && !this.numrequisicion.equals(other.numrequisicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  numrequisicion +"- "+ puesto;
    }
    
}
