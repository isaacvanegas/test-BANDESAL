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

    @Inject
    Seguridad seguridad;

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario registrar(Usuario prUsuario) throws Exception {
        String consulta;

            consulta= ("From Usuario u where u.nombre = :nombre ");

            Query query = em.createQuery(consulta);
            query.setParameter("nombre",prUsuario.getNombre());
            Usuario usuario = (Usuario) query.getSingleResult();

            if(usuario!=null){
               throw  new Exception("El usuario ya existe") ;
            }else{
                em.persist(prUsuario);
            }

        return prUsuario;
    }

    @Override
    public Usuario inicarSeccion(Usuario prUsuario){
        String consulta;

           consulta= ("From Usuario u where u.nombre = :nombre ");

            Query query = em.createQuery(consulta);
            query.setParameter("nombre",prUsuario.getNombre());
            Usuario usuario = (Usuario) query.getSingleResult();

            return verificarUsuario(usuario,prUsuario);
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

    private Usuario verificarUsuario(Usuario usuario, Usuario usuarioActual){
        if (usuario != null && seguridad.checkpw(usuarioActual.getPassword(), usuario.getPassword(),getEncryptionMethod())){
            usuario = usuarioActual;
        }
     return  usuario;
    }

}
