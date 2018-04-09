/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.session;

import com.sar.model.Seleccionados;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Luis Alejandro
 */
@Stateless
public class SeleccionadosFacade extends AbstractFacade<Seleccionados> implements SeleccionadosFacadeLocal {

    @PersistenceContext(unitName = "ingePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeleccionadosFacade() {
        super(Seleccionados.class);
    }
    
}
