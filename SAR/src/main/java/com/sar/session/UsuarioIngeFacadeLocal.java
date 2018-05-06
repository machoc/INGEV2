/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.session;

import com.sar.model.UsuarioInge;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Luis Alejandro
 */
@Local
public interface UsuarioIngeFacadeLocal {

    void create(UsuarioInge usuarioInge);

    void edit(UsuarioInge usuarioInge);

    void remove(UsuarioInge usuarioInge);

    UsuarioInge find(Object id);

    List<UsuarioInge> findAll();

    List<UsuarioInge> findRange(int[] range);

    int count();
    
    UsuarioInge iniciarSesion(UsuarioInge us);
    
}
