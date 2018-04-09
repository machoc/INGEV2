/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.session;

import com.sar.model.Evaluados;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author manuel
 */
@Local
public interface EvaluadosFacadeLocal {

    void create(Evaluados evaluados);

    void edit(Evaluados evaluados);

    void remove(Evaluados evaluados);

    Evaluados find(Object id);

    List<Evaluados> findAll();

    List<Evaluados> findRange(int[] range);

    int count();
    
}
