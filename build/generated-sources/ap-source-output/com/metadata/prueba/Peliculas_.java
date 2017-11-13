package com.metadata.prueba;

import com.metadata.prueba.Genero;
import com.metadata.prueba.Usuarios;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-13T12:58:09")
@StaticMetamodel(Peliculas.class)
public class Peliculas_ { 

    public static volatile SingularAttribute<Peliculas, String> descripcion;
    public static volatile SingularAttribute<Peliculas, Date> fechaVisualizacion;
    public static volatile SingularAttribute<Peliculas, Usuarios> idUsuario;
    public static volatile SingularAttribute<Peliculas, Genero> idGenero;
    public static volatile SingularAttribute<Peliculas, Integer> idPelicula;
    public static volatile SingularAttribute<Peliculas, String> nombre;
    public static volatile SingularAttribute<Peliculas, BigDecimal> nota;

}