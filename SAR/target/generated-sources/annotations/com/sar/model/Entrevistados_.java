package com.sar.model;

import com.sar.model.Postulante;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-06T14:40:48")
@StaticMetamodel(Entrevistados.class)
public class Entrevistados_ { 

    public static volatile SingularAttribute<Entrevistados, String> tipo;
    public static volatile SingularAttribute<Entrevistados, BigDecimal> id;
    public static volatile SingularAttribute<Entrevistados, String> respuesta;
    public static volatile SingularAttribute<Entrevistados, Postulante> postulante;

}