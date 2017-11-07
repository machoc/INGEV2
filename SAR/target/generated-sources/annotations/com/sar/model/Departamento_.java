package com.sar.model;

import com.sar.model.Requisicion;
import javax.annotation.Generated;
<<<<<<< HEAD
<<<<<<< HEAD
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-04T16:23:58")
=======
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-16T13:02:34")
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
=======
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-16T13:02:34")
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
@StaticMetamodel(Departamento.class)
public class Departamento_ { 

    public static volatile SingularAttribute<Departamento, String> descripcion;
    public static volatile SingularAttribute<Departamento, String> codigoDepartamento;
<<<<<<< HEAD
<<<<<<< HEAD
    public static volatile ListAttribute<Departamento, Requisicion> requisicionList;
=======
    public static volatile CollectionAttribute<Departamento, Requisicion> requisicionCollection;
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac
=======
    public static volatile CollectionAttribute<Departamento, Requisicion> requisicionCollection;
>>>>>>> 2b81dbbd337be31ff3c70ebf39abdca403d1edac

}