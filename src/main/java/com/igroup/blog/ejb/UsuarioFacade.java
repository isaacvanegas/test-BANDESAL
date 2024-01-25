/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igroup.blog.ejb;

import com.igroup.blog.model.Usuario;
import com.igroup.blog.seguridad.Seguridad;
import com.igroup.blog.utils.EncryptionMethod;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author jvanegas
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {


    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario inicarSeccion(Usuario prUsuario){
        Usuario usuario = null;
        String consulta;
        Seguridad seguridad = new Seguridad();

        try{
           consulta= ("From Usuario u where u.nombre = :nombre ");

            Query query = em.createQuery(consulta);
            query.setParameter("nombre",prUsuario.getNombre());
            List<Usuario> list =query.getResultList();

            usuario = list.isEmpty() ? usuario :
                    seguridad.checkpw(prUsuario.getPassword(), list.get(0).getPassword(),getEncryptionMethod())
                            ? list.get(0) : usuario;

            return list.isEmpty() ? usuario : list.get(0);
        }catch (Exception e){
            throw e;
        }

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
