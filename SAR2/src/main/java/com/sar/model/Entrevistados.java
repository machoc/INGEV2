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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manuel
 */
@Entity
@Table(name = "ENTREVISTADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrevistados.findAll", query = "SELECT e FROM Entrevistados e")
    , @NamedQuery(name = "Entrevistados.findById", query = "SELECT e FROM Entrevistados e WHERE e.id = :id")
    , @NamedQuery(name = "Entrevistados.findByRespuesta", query = "SELECT e FROM Entrevistados e WHERE e.respuesta = :respuesta")
    , @NamedQuery(name = "Entrevistados.findByTipo", query = "SELECT e FROM Entrevistados e WHERE e.tipo = :tipo")})
public class Entrevistados implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
        @GeneratedValue(generator="entrevista_auto")
   @SequenceGenerator(name="entrevista_auto", sequenceName="AUTO_SEQ_ENTREVISTADOS", allocationSize = 1)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 20)
    @Column(name = "RESPUESTA")
    private String respuesta;
    @Size(max = 50)
    @Column(name = "TIPO")
    private String tipo;
    @JoinColumn(name = "POSTULANTE", referencedColumnName = "CEDULA")
    @ManyToOne
    private Postulante postulante;

    public Entrevistados() {
    }

    public Entrevistados(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrevistados)) {
            return false;
        }
        Entrevistados other = (Entrevistados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sar.model.Entrevistados[ id=" + id + " ]";
    }
    
}
