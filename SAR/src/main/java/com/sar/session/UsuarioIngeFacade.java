/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.session;

import com.sar.model.UsuarioInge;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author manuel
 */
@Stateless
public class UsuarioIngeFacade extends AbstractFacade<UsuarioInge> implements UsuarioIngeFacadeLocal {

    @PersistenceContext(unitName = "ingePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioIngeFacade() {
        super(UsuarioInge.class);
    }
    
    @Override
    public UsuarioInge iniciarSesion(UsuarioInge us){
        UsuarioInge usuario = null;
        String consulta;
        try{
            // FROM UsuarioInge u WHERE u.cedula
            consulta = "FROM UsuarioInge u WHERE u.cedula = ?1 and u.contraseña = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getCedula());
            query.setParameter(2, us.getContraseña());
            List<UsuarioInge> lista = query.getResultList();
            if(!lista.isEmpty()){
                usuario = lista.get(0);
            }
        }catch(Exception e){
            throw e;
        }
        return usuario;
    }
    
}
