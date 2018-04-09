/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.session;

import com.sar.model.UsuarioInge;
import java.util.List;

/**
 *
 * @author Luis Alejandro
 */
public interface UsuarioIngeFacadeLocal {
    void create(UsuarioInge requisicion);

    void edit(UsuarioInge requisicion);

    void remove(UsuarioInge requisicion);

    UsuarioInge find(Object id);

    List<UsuarioInge> findAll();

    List<UsuarioInge> findRange(int[] range);

    int count();
    
}
