/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igroup.blog.controller;

import com.igroup.blog.ejb.UsuarioFacadeLocal;
import com.igroup.blog.model.Usuario;
import java.io.Serializable;
import java.net.URL;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.igroup.blog.seguridad.Seguridad;
import com.igroup.blog.utils.EncryptionMethod;
import lombok.Data;

/**
 *
 * @author jvanegas
 */
@Named
@ViewScoped
public class UsuarioController implements Serializable {
  @EJB
  private UsuarioFacadeLocal usuarioEJB;
  @Inject
  Usuario usuario;



    @PostConstruct
    public void init(){

        clean();
    }

    public void registrar(){
       Seguridad seguridad= new Seguridad();
        try{
            usuario.setPassword(seguridad.encode(usuario.getPassword(),getEncryptionMethod()));
            usuarioEJB.create(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Detalle","Registro Exitoso"));
            clean();
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Detalle","Error al registrar"));
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    private void clean(){
        usuario= new Usuario();
    }

    public EncryptionMethod getEncryptionMethod(){
        EncryptionMethod encryptionMethod = EncryptionMethod.BCrypt;
        try{
            Properties props = new Properties();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL urlResource = classLoader.getResource("configuration.properties");

            if (urlResource != null) {
                props.load(urlResource.openStream());
                encryptionMethod = getEnumValueEncryptionMethod(props.getProperty("seguridad.metodoEncriptacion"));
            }

        }catch (Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Detalle","Error en proceso"));

        }

        return encryptionMethod;
    }


    private EncryptionMethod getEnumValueEncryptionMethod(String property) {
        return EncryptionMethod.valueOf(property.trim());
    }





}
