package com.metadata.controller;

import com.metadata.prueba.Usuarios;
import com.metadata.prueba.Peliculas;
import java.util.Collection;
import com.metadata.facade.UsuariosFacade;
import com.metadata.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "usuariosController")
@ViewScoped
public class UsuariosController extends AbstractController<Usuarios> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isPeliculasCollectionEmpty;

    public UsuariosController() {
        // Inform the Abstract parent controller of the concrete Usuarios Entity
        super(Usuarios.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsPeliculasCollectionEmpty();
    }

    public boolean getIsPeliculasCollectionEmpty() {
        return this.isPeliculasCollectionEmpty;
    }

    private void setIsPeliculasCollectionEmpty() {
        Usuarios selected = this.getSelected();
        if (selected != null) {
            UsuariosFacade ejbFacade = (UsuariosFacade) this.getFacade();
            this.isPeliculasCollectionEmpty = ejbFacade.isPeliculasCollectionEmpty(selected);
        } else {
            this.isPeliculasCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Peliculas entities that
     * are retrieved from Usuarios and returns the navigation outcome.
     *
     * @return navigation outcome for Peliculas page
     */
    public String navigatePeliculasCollection() {
        Usuarios selected = this.getSelected();
        if (selected != null) {
            UsuariosFacade ejbFacade = (UsuariosFacade) this.getFacade();
            Collection<Peliculas> selectedPeliculasCollection = ejbFacade.findPeliculasCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Peliculas_items", selectedPeliculasCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/peliculas/index";
    }

}
