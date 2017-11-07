/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.model;

import com.sar.model.Requisicion;
import java.io.Serializable;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.List;
=======
import java.util.Collection;
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
=======
import java.util.Collection;
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author manuel
 */
@Entity
@Table(name = "DEPARTAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
    , @NamedQuery(name = "Departamento.findByCodigoDepartamento", query = "SELECT d FROM Departamento d WHERE d.codigoDepartamento = :codigoDepartamento")
    , @NamedQuery(name = "Departamento.findByDescripcion", query = "SELECT d FROM Departamento d WHERE d.descripcion = :descripcion")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO_DEPARTAMENTO")
    private String codigoDepartamento;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "departamento")
<<<<<<< HEAD
<<<<<<< HEAD
    private List<Requisicion> requisicionList;
=======
    private Collection<Requisicion> requisicionCollection;
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
=======
    private Collection<Requisicion> requisicionCollection;
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac

    public Departamento() {
    }

    public Departamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
<<<<<<< HEAD
<<<<<<< HEAD
    public List<Requisicion> getRequisicionList() {
        return requisicionList;
    }

    public void setRequisicionList(List<Requisicion> requisicionList) {
        this.requisicionList = requisicionList;
=======
=======
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
    public Collection<Requisicion> getRequisicionCollection() {
        return requisicionCollection;
    }

    public void setRequisicionCollection(Collection<Requisicion> requisicionCollection) {
        this.requisicionCollection = requisicionCollection;
<<<<<<< HEAD
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
=======
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoDepartamento != null ? codigoDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.codigoDepartamento == null && other.codigoDepartamento != null) || (this.codigoDepartamento != null && !this.codigoDepartamento.equals(other.codigoDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
<<<<<<< HEAD
<<<<<<< HEAD
        return "com.sar.controller.Departamento[ codigoDepartamento=" + codigoDepartamento + " ]";
=======
        return "com.sar.model.Departamento[ codigoDepartamento=" + codigoDepartamento + " ]";
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
=======
        return "com.sar.model.Departamento[ codigoDepartamento=" + codigoDepartamento + " ]";
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
    }
    
}
