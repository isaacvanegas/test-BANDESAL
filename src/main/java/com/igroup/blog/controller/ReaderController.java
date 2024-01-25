package com.igroup.blog.controller;

import com.igroup.blog.ejb.ReaderFacadeLocal;
import com.igroup.blog.ejb.UsuarioFacadeLocal;
import com.igroup.blog.model.Reader;
import com.igroup.blog.model.Usuario;
import com.igroup.blog.seguridad.Seguridad;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Data
@ViewScoped
public class ReaderController implements Serializable {
    @EJB
    private ReaderFacadeLocal readerEJB;

    private Reader reader;
    private List<Reader> listRreader;
    private String acction = "Register";

    @PostConstruct
    public void init() {
        listarReader();
        clean();
    }

    public void listarReader() {
        listRreader = readerEJB.findAll();
    }

    public void registrar() {
        String ms = "Registro Exitoso";
        try {

            if (reader != null) {
                readerEJB.create(reader);

                if (reader.getId() != null) {
                    readerEJB.edit(reader);
                    ms = "Se edito el registro";
                }
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Detalle", ms));
            clean();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Detalle", "Error al registrar"));
        }
    }

    public void eliminar() {

        try {

            if (reader != null) {
                readerEJB.remove(reader);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Detalle", "Registro eliminado"));
                clean();
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Detalle", "Error al registrar"));
        }
    }

    public void clean() {
        reader = new Reader();
    }

    public String getAction() {

        return reader != null ? "Editar" : acction;
    }

}
