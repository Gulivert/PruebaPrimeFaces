/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metadata.facade;

import com.metadata.prueba.Usuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.metadata.prueba.Usuarios_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.metadata.prueba.Peliculas;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author guill
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "PruebaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    public boolean isPeliculasCollectionEmpty(Usuarios entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuarios> usuarios = cq.from(Usuarios.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuarios, entity), cb.isNotEmpty(usuarios.get(Usuarios_.peliculasCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Peliculas> findPeliculasCollection(Usuarios entity) {
        Usuarios mergedEntity = this.getMergedEntity(entity);
        Collection<Peliculas> peliculasCollection = mergedEntity.getPeliculasCollection();
        peliculasCollection.size();
        return peliculasCollection;
    }
    
    /**
     * Busca usuarios por sus parametros de login
     * @param user
     * @param pass
     * @return 
     */
    public Usuarios findByLogin(String user, String pass) {
        Map<String, Object> condiciones = new HashMap<>();
        condiciones.put("usuario", user);
        condiciones.put("password", pass);
        List<Usuarios> usuarios = findExactRange(-1, -1, null, condiciones);
        if(usuarios.isEmpty())
            return null;
        return usuarios.get(0);
    }

}
