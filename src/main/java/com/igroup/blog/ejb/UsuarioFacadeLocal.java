/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.igroup.blog.ejb;

import com.igroup.blog.model.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jvanegas
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();


    Usuario inicarSeccion(Usuario prUsuario);

    Usuario registrar(Usuario prUsuario) throws Exception;
    
}
