/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.igroup.blog.ejb;

import com.igroup.blog.model.Blog;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jvanegas
 */
@Local
public interface BlogFacadeLocal {

    void create(Blog blog);

    void edit(Blog blog);

    void remove(Blog blog);

    Blog find(Object id);

    List<Blog> findAll();

    List<Blog> findRange(int[] range);

    int count();
    
}
