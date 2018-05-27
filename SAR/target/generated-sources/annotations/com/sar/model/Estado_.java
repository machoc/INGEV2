package com.sar.model;

import com.sar.model.Postulante;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-26T20:33:17")
@StaticMetamodel(Estado.class)
public class Estado_ { 

    public static volatile CollectionAttribute<Estado, Postulante> postulanteCollection;
    public static volatile SingularAttribute<Estado, String> codigoEstado;
    public static volatile SingularAttribute<Estado, String> detalle;

}