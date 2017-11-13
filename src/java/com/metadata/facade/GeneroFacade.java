/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metadata.facade;

import com.metadata.prueba.Genero;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.metadata.prueba.Genero_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.metadata.prueba.Peliculas;
import java.util.Collection;

/**
 *
 * @author guill
 */
@Stateless
public class GeneroFacade extends AbstractFacade<Genero> {

    @PersistenceContext(unitName = "PruebaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GeneroFacade() {
        super(Genero.class);
    }

    public boolean isPeliculasCollectionEmpty(Genero entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Genero> genero = cq.from(Genero.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(genero, entity), cb.isNotEmpty(genero.get(Genero_.peliculasCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Peliculas> findPeliculasCollection(Genero entity) {
        Genero mergedEntity = this.getMergedEntity(entity);
        Collection<Peliculas> peliculasCollection = mergedEntity.getPeliculasCollection();
        peliculasCollection.size();
        return peliculasCollection;
    }
    
}
