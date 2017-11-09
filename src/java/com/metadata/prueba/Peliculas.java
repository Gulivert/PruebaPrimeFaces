/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metadata.prueba;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guill
 */
@Entity
@Table(catalog = "prueba", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peliculas.findAll", query = "SELECT p FROM Peliculas p")
    , @NamedQuery(name = "Peliculas.findByIdPelicula", query = "SELECT p FROM Peliculas p WHERE p.idPelicula = :idPelicula")
    , @NamedQuery(name = "Peliculas.findByNombre", query = "SELECT p FROM Peliculas p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Peliculas.findByDescripcion", query = "SELECT p FROM Peliculas p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Peliculas.findByFechaVisualizacion", query = "SELECT p FROM Peliculas p WHERE p.fechaVisualizacion = :fechaVisualizacion")
    , @NamedQuery(name = "Peliculas.findByNota", query = "SELECT p FROM Peliculas p WHERE p.nota = :nota")})
public class Peliculas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pelicula")
    private Integer idPelicula;
    @Size(max = 128)
    private String nombre;
    @Size(max = 1024)
    private String descripcion;
    @Column(name = "fecha_visualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaVisualizacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private BigDecimal nota;
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero")
    @ManyToOne(optional = false)
    private Genero idGenero;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;

    public Peliculas() {
    }

    public Peliculas(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaVisualizacion() {
        return fechaVisualizacion;
    }

    public void setFechaVisualizacion(Date fechaVisualizacion) {
        this.fechaVisualizacion = fechaVisualizacion;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public Genero getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Genero idGenero) {
        this.idGenero = idGenero;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPelicula != null ? idPelicula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peliculas)) {
            return false;
        }
        Peliculas other = (Peliculas) object;
        if ((this.idPelicula == null && other.idPelicula != null) || (this.idPelicula != null && !this.idPelicula.equals(other.idPelicula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metadata.prueba.Peliculas[ idPelicula=" + idPelicula + " ]";
    }
    
}
