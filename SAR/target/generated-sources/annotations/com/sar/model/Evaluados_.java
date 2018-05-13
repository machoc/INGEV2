package com.sar.model;

import com.sar.model.Postulante;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-13T11:16:25")
@StaticMetamodel(Evaluados.class)
public class Evaluados_ { 

    public static volatile SingularAttribute<Evaluados, BigDecimal> codigo;
    public static volatile SingularAttribute<Evaluados, Postulante> postulante;
    public static volatile SingularAttribute<Evaluados, Short> prioridad;

}