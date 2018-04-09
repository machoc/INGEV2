/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.session;

import com.sar.model.Seleccionados;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Luis Alejandro
 */
@Local
public interface SeleccionadosFacadeLocal {

    void create(Seleccionados seleccionados);

    void edit(Seleccionados seleccionados);

    void remove(Seleccionados seleccionados);

    Seleccionados find(Object id);

    List<Seleccionados> findAll();

    List<Seleccionados> findRange(int[] range);

    int count();
    
}
