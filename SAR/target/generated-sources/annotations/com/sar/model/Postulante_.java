package com.sar.model;

import com.sar.model.Entrevistados;
import com.sar.model.Estado;
import com.sar.model.Evaluados;
import com.sar.model.Requisicion;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-16T23:39:51")
@StaticMetamodel(Postulante.class)
public class Postulante_ { 

    public static volatile SingularAttribute<Postulante, Estado> estado;
    public static volatile SingularAttribute<Postulante, String> cedula;
    public static volatile SingularAttribute<Postulante, String> residencia;
    public static volatile SingularAttribute<Postulante, String> notas;
    public static volatile SingularAttribute<Postulante, String> nombrecompleto;
    public static volatile SingularAttribute<Postulante, Short> edad;
    public static volatile SingularAttribute<Postulante, String> especialidad;
    public static volatile SingularAttribute<Postulante, String> licencia;
    public static volatile SingularAttribute<Postulante, String> experiencia;
    public static volatile CollectionAttribute<Postulante, Entrevistados> entrevistadosCollection;
    public static volatile SingularAttribute<Postulante, String> doprueba;
    public static volatile CollectionAttribute<Postulante, Evaluados> evaluadosCollection;
    public static volatile SingularAttribute<Postulante, String> telefono;
    public static volatile SingularAttribute<Postulante, Requisicion> requisicion;

}