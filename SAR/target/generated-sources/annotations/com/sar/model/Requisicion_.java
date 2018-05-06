package com.sar.model;

import com.sar.model.Departamento;
import com.sar.model.Postulante;
import com.sar.model.UsuarioInge;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-06T14:40:48")
@StaticMetamodel(Requisicion.class)
public class Requisicion_ { 

    public static volatile SingularAttribute<Requisicion, String> puesto;
    public static volatile SingularAttribute<Requisicion, String> estado;
    public static volatile CollectionAttribute<Requisicion, Postulante> postulanteCollection;
    public static volatile SingularAttribute<Requisicion, BigDecimal> numrequisicion;
    public static volatile SingularAttribute<Requisicion, Date> fechaInicio;
    public static volatile SingularAttribute<Requisicion, String> contratante;
    public static volatile SingularAttribute<Requisicion, Date> fechaEntrega;
    public static volatile SingularAttribute<Requisicion, Departamento> departamento;
    public static volatile SingularAttribute<Requisicion, UsuarioInge> usuario;
    public static volatile SingularAttribute<Requisicion, Short> vacantes;

}