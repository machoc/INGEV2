/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.session;

import com.sar.model.Entrevistados;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Luis Alejandro
 */
@Local
public interface EntrevistadosFacadeLocal {

    void create(Entrevistados entrevistados);

    void edit(Entrevistados entrevistados);

    void remove(Entrevistados entrevistados);

    Entrevistados find(Object id);

    List<Entrevistados> findAll();

    List<Entrevistados> findRange(int[] range);

    int count();
    
}
