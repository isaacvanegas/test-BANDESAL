/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.igroup.blog.ejb;

import com.igroup.blog.model.BlogsReader;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jvanegas
 */
@Local
public interface BlogsReaderFacadeLocal {

    void create(BlogsReader blogsReader);

    void edit(BlogsReader blogsReader);

    void remove(BlogsReader blogsReader);

    BlogsReader find(Object id);

    List<BlogsReader> findAll();

    List<BlogsReader> findRange(int[] range);

    int count();
    
}
