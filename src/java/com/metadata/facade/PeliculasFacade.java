/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metadata.facade;

import com.metadata.controller.LoginController;
import com.metadata.prueba.Peliculas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.metadata.prueba.Peliculas_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.metadata.prueba.Genero;
import com.metadata.prueba.Usuarios;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author guill
 */
@Stateless
public class PeliculasFacade extends AbstractFacade<Peliculas> {

    @PersistenceContext(unitName = "PruebaPU")
    private EntityManager em;

    @Inject
    private UsuariosFacade usuariosFacade;

    @Inject
    private LoginController loginController;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeliculasFacade() {
        super(Peliculas.class);
    }

    public boolean isIdGeneroEmpty(Peliculas entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Peliculas> peliculas = cq.from(Peliculas.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(peliculas, entity), cb.isNotNull(peliculas.get(Peliculas_.idGenero)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Genero findIdGenero(Peliculas entity) {
        return this.getMergedEntity(entity).getIdGenero();
    }

    public boolean isIdUsuarioEmpty(Peliculas entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Peliculas> peliculas = cq.from(Peliculas.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(peliculas, entity), cb.isNotNull(peliculas.get(Peliculas_.idUsuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuarios findIdUsuario(Peliculas entity) {
        return this.getMergedEntity(entity).getIdUsuario();
    }

    public List<Peliculas> findPeliculasByUser(Usuarios usuario) {
        Map<String, Object> condiciones = new HashMap<>();
        condiciones.put("idUsuario", usuario);
        return findExactRange(-1, -1, null, condiciones);
    }

    @Override
    public List<Peliculas> findAll() {
        Usuarios sessionUser = loginController.getUser();
        if ((sessionUser != null) && (sessionUser.getEsAdministrador() == 0)) {
            return findPeliculasByUser(sessionUser);
        } else {
            return super.findAll();
        }
    }
}
