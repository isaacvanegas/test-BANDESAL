package com.igroup.blog.controller;

import com.igroup.blog.ejb.BlogFacadeLocal;
import com.igroup.blog.ejb.BlogsReaderFacadeLocal;
import com.igroup.blog.ejb.ReaderFacadeLocal;
import com.igroup.blog.model.Blog;
import com.igroup.blog.model.BlogsReader;
import com.igroup.blog.model.Reader;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Data
@Named
@SessionScoped
public class BlogReaderController implements Serializable {

    @EJB
    private BlogsReaderFacadeLocal blogReaderEJB;
    @EJB
    private BlogFacadeLocal blogEJB;
    @EJB
    private ReaderFacadeLocal readerEJB;

    private BlogsReader blogReader;
    private List<BlogsReader> listBlogReader;
    private List<Blog> listBlog;
    private String blog;
    private List<Reader> listReader;
    private String reader;
    private String acction= "Register";

    @PostConstruct
    public void init(){
        listarBlogReader();
        clean();
    }

    public void listarBlogReader(){
        listBlogReader =  blogReaderEJB.findAll();
    }

    public void registrar(){
        String ms = "Registro Exitoso";
        try{

            if(blog!=null && reader!=null){

                blogReader.setBlogs(blogEJB.find(Integer.parseInt(blog)));
                blogReader.setReaders(readerEJB.find(Integer.parseInt(blog)));

                blogReaderEJB.create(blogReader);

                if (blogReader.getId()!=null){
                    blogReaderEJB.edit(blogReader);
                    ms= "Se edito el registro";
                }
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Detalle",ms));
            clean();
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Detalle","Error al registrar"));
        }
    }
    public void eliminar(){

        try{

            if(blogReader!=null){
                blogReaderEJB.remove(blogReader);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Detalle","Registro eliminado"));
                clean();
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Detalle","Error al registrar"));
        }
    }

    private void clean()
    {
        blogReader=new BlogsReader();
    }

    public String getAction(){
        return blogReader!=null ? "Editar": acction;
    }


}

