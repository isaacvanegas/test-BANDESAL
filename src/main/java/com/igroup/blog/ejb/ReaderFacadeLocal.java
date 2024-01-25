/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.igroup.blog.ejb;

import com.igroup.blog.model.Reader;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jvanegas
 */
@Local
public interface ReaderFacadeLocal {

    void create(Reader reader);

    void edit(Reader reader);

    void remove(Reader reader);

    Reader find(Object id);

    List<Reader> findAll();

    List<Reader> findRange(int[] range);

    int count();
    
}
