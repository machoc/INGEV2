package com.sar.model;

import com.sar.model.Requisicion;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-16T23:39:51")
@StaticMetamodel(UsuarioInge.class)
public class UsuarioInge_ { 

    public static volatile SingularAttribute<UsuarioInge, String> tipo;
    public static volatile CollectionAttribute<UsuarioInge, Requisicion> requisicionCollection;
    public static volatile SingularAttribute<UsuarioInge, String> cedula;
    public static volatile SingularAttribute<UsuarioInge, String> telefono;
    public static volatile SingularAttribute<UsuarioInge, String> nombre;
    public static volatile SingularAttribute<UsuarioInge, String> email;
    public static volatile SingularAttribute<UsuarioInge, String> contraseña;

}