/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igroup.blog.ejb;

import com.igroup.blog.model.BlogsReader;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jvanegas
 */
@Stateless
public class BlogsReaderFacade extends AbstractFacade<BlogsReader> implements BlogsReaderFacadeLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BlogsReaderFacade() {
        super(BlogsReader.class);
    }
    
}
