package com.metadata.prueba;

import com.metadata.prueba.Peliculas;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-15T09:24:10")
@StaticMetamodel(Genero.class)
public class Genero_ { 

    public static volatile CollectionAttribute<Genero, Peliculas> peliculasCollection;
    public static volatile SingularAttribute<Genero, Integer> idGenero;
    public static volatile SingularAttribute<Genero, String> nombre;

}