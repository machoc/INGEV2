package com.sar.model;

import com.sar.model.Departamento;
import com.sar.model.Postulante;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-08T19:06:20")
@StaticMetamodel(Requisicion.class)
public class Requisicion_ { 

    public static volatile SingularAttribute<Requisicion, String> puesto;
    public static volatile SingularAttribute<Requisicion, BigDecimal> numrequisicion;
    public static volatile SingularAttribute<Requisicion, Date> fechaInicio;
    public static volatile SingularAttribute<Requisicion, String> contratante;
    public static volatile SingularAttribute<Requisicion, Date> fechaEntrega;
    public static volatile SingularAttribute<Requisicion, Departamento> departamento;
    public static volatile ListAttribute<Requisicion, Postulante> postulanteList;
    public static volatile SingularAttribute<Requisicion, Short> vacantes;

}