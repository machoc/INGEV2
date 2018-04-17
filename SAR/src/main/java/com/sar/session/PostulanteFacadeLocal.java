/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.session;

import com.sar.model.Postulante;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author manuel
 */
@Local
public interface PostulanteFacadeLocal {

    void create(Postulante postulante);

    void edit(Postulante postulante);

    void remove(Postulante postulante);

    Postulante find(Object id);

    List<Postulante> findAll();

    List<Postulante> findRange(int[] range);

    int count();
    
}
