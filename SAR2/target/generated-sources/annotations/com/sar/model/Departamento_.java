package com.sar.model;

import com.sar.model.Requisicion;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-08T21:02:23")
@StaticMetamodel(Departamento.class)
public class Departamento_ { 

    public static volatile SingularAttribute<Departamento, String> descripcion;
    public static volatile SingularAttribute<Departamento, String> codigoDepartamento;
    public static volatile CollectionAttribute<Departamento, Requisicion> requisicionCollection;

}