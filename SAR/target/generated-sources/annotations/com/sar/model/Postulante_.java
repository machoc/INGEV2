package com.sar.model;

import com.sar.model.Estado;
import com.sar.model.Requisicion;
import javax.annotation.Generated;
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
@StaticMetamodel(Postulante.class)
public class Postulante_ { 

    public static volatile SingularAttribute<Postulante, Estado> estado;
    public static volatile SingularAttribute<Postulante, String> licencia;
    public static volatile SingularAttribute<Postulante, String> cedula;
    public static volatile SingularAttribute<Postulante, String> residencia;
    public static volatile SingularAttribute<Postulante, String> experiencia;
    public static volatile SingularAttribute<Postulante, String> nombrecompleto;
<<<<<<< HEAD
<<<<<<< HEAD
    public static volatile SingularAttribute<Postulante, String> telefono;
=======
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
=======
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
    public static volatile SingularAttribute<Postulante, Requisicion> requisicion;
    public static volatile SingularAttribute<Postulante, Short> edad;
    public static volatile SingularAttribute<Postulante, String> especialidad;

}