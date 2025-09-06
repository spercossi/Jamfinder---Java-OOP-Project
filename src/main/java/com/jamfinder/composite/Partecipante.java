package com.jamfinder.composite;

import com.jamfinder.model.Utente;
import com.jamfinder.model.Strumento;

// Leaf del Composite pattern - rappresenta un partecipante
public class Partecipante implements ComponenteJamSession {
    private Utente utente;
    private Strumento strumento;
    
    public Partecipante(Utente utente, Strumento strumento) {
        this.utente = utente;
        this.strumento = strumento;
    }
    
    @Override
    public String getNome() {
        return utente.getNome();
    }
    
    @Override
    public String getDescrizione() {
        return utente.getNome() + " suona " + strumento.getNome();
    }
    
    @Override
    public void mostra() {
        System.out.println("  - " + getDescrizione());
    }
    
    public Utente getUtente() {
        return utente;
    }
    
    public Strumento getStrumento() {
        return strumento;
    }
}
