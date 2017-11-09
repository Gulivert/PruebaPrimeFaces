/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metadata.prueba;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author guill
 */
@Entity
@Table(catalog = "prueba", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findById", query = "SELECT u FROM Usuarios u WHERE u.id = :id")
    , @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario")
    , @NamedQuery(name = "Usuarios.findByPassword", query = "SELECT u FROM Usuarios u WHERE u.password = :password")
    , @NamedQuery(name = "Usuarios.findByNombre", query = "SELECT u FROM Usuarios u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuarios.findByApellido1", query = "SELECT u FROM Usuarios u WHERE u.apellido1 = :apellido1")
    , @NamedQuery(name = "Usuarios.findByApellido2", query = "SELECT u FROM Usuarios u WHERE u.apellido2 = :apellido2")
    , @NamedQuery(name = "Usuarios.findBySexo", query = "SELECT u FROM Usuarios u WHERE u.sexo = :sexo")
    , @NamedQuery(name = "Usuarios.findByTelefono", query = "SELECT u FROM Usuarios u WHERE u.telefono = :telefono")
    , @NamedQuery(name = "Usuarios.findByCorreo", query = "SELECT u FROM Usuarios u WHERE u.correo = :correo")
    , @NamedQuery(name = "Usuarios.findByEsAdministrador", query = "SELECT u FROM Usuarios u WHERE u.esAdministrador = :esAdministrador")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 45)
    private String usuario;
    @Size(max = 45)
    private String password;
    @Size(max = 128)
    private String nombre;
    @Size(max = 128)
    private String apellido1;
    @Size(max = 128)
    private String apellido2;
    @Size(max = 1)
    private String sexo;
    @Size(max = 128)
    private String telefono;
    @Size(max = 128)
    private String correo;
    @Column(name = "es_administrador")
    private Short esAdministrador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Peliculas> peliculasCollection;

    public Usuarios() {
    }

    public Usuarios(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Short getEsAdministrador() {
        return esAdministrador;
    }

    public void setEsAdministrador(Short esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

    @XmlTransient
    public Collection<Peliculas> getPeliculasCollection() {
        return peliculasCollection;
    }

    public void setPeliculasCollection(Collection<Peliculas> peliculasCollection) {
        this.peliculasCollection = peliculasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metadata.prueba.Usuarios[ id=" + id + " ]";
    }
    
}
