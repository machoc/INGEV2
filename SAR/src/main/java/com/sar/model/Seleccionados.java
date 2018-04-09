/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Alejandro
 */
@Entity
@Table(name = "SELECCIONADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seleccionados.findAll", query = "SELECT s FROM Seleccionados s")
    , @NamedQuery(name = "Seleccionados.findByCodigo", query = "SELECT s FROM Seleccionados s WHERE s.codigo = :codigo")
    , @NamedQuery(name = "Seleccionados.findByPrioridad", query = "SELECT s FROM Seleccionados s WHERE s.prioridad = :prioridad")})
public class Seleccionados implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator="some_seq_name3")
   @SequenceGenerator(name="some_seq_name3", sequenceName="AUTO_SEQ3", allocationSize=1)
    @Column(name = "CODIGO")
    private BigDecimal codigo;
    @Column(name = "PRIORIDAD")
    private Short prioridad;
    @JoinColumn(name = "POSTULANTE", referencedColumnName = "CEDULA")
    @ManyToOne
    private Postulante postulante;

    public Seleccionados() {
    }

    public Seleccionados(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getCodigo() {
        return codigo;
    }

    public void setCodigo(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public Short getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Short prioridad) {
        this.prioridad = prioridad;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seleccionados)) {
            return false;
        }
        Seleccionados other = (Seleccionados) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sar.model.Seleccionados[ codigo=" + codigo + " ]";
    }
    
}
