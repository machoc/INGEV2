package com.sar.model;

import com.sar.model.Departamento;
import com.sar.model.Postulante;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-04T16:23:58")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-16T13:02:34")
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-16T13:02:34")
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
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