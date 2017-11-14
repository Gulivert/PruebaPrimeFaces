package com.metadata.controller;

import com.metadata.prueba.Peliculas;
import com.metadata.facade.PeliculasFacade;
import com.metadata.controller.util.MobilePageController;
import com.metadata.facade.GeneroFacade;
import com.metadata.facade.UsuariosFacade;
import com.metadata.prueba.Genero;
import com.metadata.prueba.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "peliculasController")
@ViewScoped
public class PeliculasController extends AbstractController<Peliculas> {

    @Inject
    private GeneroController idGeneroController;
    @Inject
    private UsuariosController idUsuarioController;
    @Inject
    private MobilePageController mobilePageController;
    @Inject
    private LoginController loginController;
    @Inject 
    private UsuariosFacade usuariosFacade;
    @Inject 
    private GeneroFacade generoFacade;

    public PeliculasController() {
        // Inform the Abstract parent controller of the concrete Peliculas Entity
        super(Peliculas.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idGeneroController.setSelected(null);
        idUsuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Genero controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdGenero(ActionEvent event) {
        Peliculas selected = this.getSelected();
        if (selected != null && idGeneroController.getSelected() == null) {
            idGeneroController.setSelected(selected.getIdGenero());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuarios controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdUsuario(ActionEvent event) {
        Peliculas selected = this.getSelected();
        if (selected != null && idUsuarioController.getSelected() == null) {
            idUsuarioController.setSelected(selected.getIdUsuario());
        }
    }
    
    @Override
    public void saveNew(ActionEvent event) {
        Usuarios usuario = loginController.getUser();
        if((usuario!=null)&&(usuario.getEsAdministrador()!=1)){
            Peliculas pelicula = (Peliculas) super.getSelected();
            pelicula.setIdUsuario(usuario);
        }
        super.saveNew(event);
    }
    
    public List<String> getUsuarios(){
        List<String> nombre = new ArrayList<>();
        usuariosFacade.findAll().forEach((usuario) -> {
            nombre.add(usuario.getNombre());
        });
        return nombre;
    }
    
    public List<String> getGeneros(){
        List<String> nombre = new ArrayList<>();
        generoFacade.findAll().forEach((genero) -> {
            nombre.add(genero.getNombre());
        });
        return nombre;
    }

}
