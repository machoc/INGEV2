/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jason
 */
@Entity
@Table(name = "USUARIO_INGE")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "UsuarioInge.findAll", query = "SELECT u FROM UsuarioInge u"),
    @NamedQuery(name = "UsuarioInge.findByCedula", query = "SELECT u FROM UsuarioInge u WHERE u.cedula = :cedula"),
    @NamedQuery(name = "UsuarioInge.findByContrase\u00f1a", query = "SELECT u FROM UsuarioInge u WHERE u.contrase\u00f1a = :contrase\u00f1a"),
    @NamedQuery(name = "UsuarioInge.findByNombre", query = "SELECT u FROM UsuarioInge u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "UsuarioInge.findByNumrequisicion", query = "SELECT u FROM UsuarioInge u WHERE u.numrequisicion = :numrequisicion"),
    @NamedQuery(name = "UsuarioInge.findByTipo", query = "SELECT u FROM UsuarioInge u WHERE u.tipo = :tipo")
})
public class UsuarioInge implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CEDULA")
    private String cedula;
    @Size(max = 255)
    @Column(name = "CONTRASE\u00d1A")
    private String contraseña;
    @Size(max = 255)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "NUMREQUISICION")
    private BigInteger numrequisicion;
    @Size(max = 255)
    @Column(name = "TIPO")
    private String tipo;

    public UsuarioInge()
    {
    }

    public UsuarioInge(String cedula)
    {
        this.cedula = cedula;
    }

    public String getCedula()
    {
        return cedula;
    }

    public void setCedula(String cedula)
    {
        this.cedula = cedula;
    }

    public String getContraseña()
    {
        return contraseña;
    }

    public void setContraseña(String contraseña)
    {
        this.contraseña = contraseña;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public BigInteger getNumrequisicion()
    {
        return numrequisicion;
    }

    public void setNumrequisicion(BigInteger numrequisicion)
    {
        this.numrequisicion = numrequisicion;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioInge))
        {
            return false;
        }
        UsuarioInge other = (UsuarioInge) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sar.model.UsuarioInge[ cedula=" + cedula + " ]";
    }

}
