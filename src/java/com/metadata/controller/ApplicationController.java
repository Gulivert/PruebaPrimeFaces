/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metadata.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author guill
 */
@Named(value = "applicationController")
@ApplicationScoped
public class ApplicationController {
    private int numeroLogueados;

    public int getNumeroLogueados() {
        return numeroLogueados;
    }

    public void setNumeroLogueados(int numeroLogueados) {
        this.numeroLogueados = numeroLogueados;
    }
    
    public void incrementarNumeroLogueados(){
        this.numeroLogueados++;
    }
}
