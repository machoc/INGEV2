/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.session;

import com.sar.model.UsuarioInge;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jason
 */
@Stateless
public class UsuarioIngeFacade extends AbstractFacade<UsuarioInge> implements UsuarioIngeFacadeLocal
{

    @PersistenceContext(unitName = "ingePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public UsuarioIngeFacade()
    {
        super(UsuarioInge.class);
    }

}
