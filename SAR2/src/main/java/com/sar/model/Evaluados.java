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
 * @author manuel
 */
@Entity
@Table(name = "EVALUADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluados.findAll", query = "SELECT e FROM Evaluados e")
    , @NamedQuery(name = "Evaluados.findByCodigo", query = "SELECT e FROM Evaluados e WHERE e.codigo = :codigo")
    , @NamedQuery(name = "Evaluados.findByPrioridad", query = "SELECT e FROM Evaluados e WHERE e.prioridad = :prioridad")})
public class Evaluados implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
        @GeneratedValue(generator="evaluado_auto")
   @SequenceGenerator(name="evaluado_auto", sequenceName="AUTO_SEQ3", allocationSize = 1)
    @NotNull
    @Column(name = "CODIGO")
    private BigDecimal codigo;
    @Column(name = "PRIORIDAD")
    private Short prioridad;
    @JoinColumn(name = "POSTULANTE", referencedColumnName = "CEDULA")
    @ManyToOne
    private Postulante postulante;

    public Evaluados() {
    }

    public Evaluados(BigDecimal codigo) {
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
        if (!(object instanceof Evaluados)) {
            return false;
        }
        Evaluados other = (Evaluados) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sar.model.Evaluados[ codigo=" + codigo + " ]";
    }
    
}
