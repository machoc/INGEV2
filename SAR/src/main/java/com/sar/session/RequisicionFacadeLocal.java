/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.session;

import com.sar.model.Requisicion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jason
 */
@Local
public interface RequisicionFacadeLocal
{

    void create(Requisicion requisicion);

    void edit(Requisicion requisicion);

    void remove(Requisicion requisicion);

    Requisicion find(Object id);

    List<Requisicion> findAll();

    List<Requisicion> findRange(int[] range);

    int count();

}
