package com.igroup.blog.controller;

import com.igroup.blog.ejb.BlogFacadeLocal;
import com.igroup.blog.ejb.ReaderFacadeLocal;
import com.igroup.blog.model.Blog;
import com.igroup.blog.model.Reader;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Data
@Named
@ViewScoped
public class BlogController implements Serializable {
    @EJB
    private BlogFacadeLocal blogEJB;

    private Blog blog;
    private List<Blog> listBlog;
    private String acction= "Register";

    @PostConstruct
    public void init(){
        listarBlog();
        clean();
    }

    public void listarBlog(){
        listBlog =  blogEJB.findAll();
    }

    public void registrar(){
        String ms = "Registro Exitoso";
        try{

            if(blog!=null){
                blogEJB.create(blog);

                if (blog.getId()!=null){
                    blogEJB.edit(blog);
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

            if(blog!=null){
                blogEJB.remove(blog);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Detalle","Registro eliminado"));
                clean();
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Detalle","Error al registrar"));
        }
    }

    private void clean()
    {
        blog=new Blog();
    }

    public String getAction(){
        return blog!=null ? "Editar": acction;
    }


}
