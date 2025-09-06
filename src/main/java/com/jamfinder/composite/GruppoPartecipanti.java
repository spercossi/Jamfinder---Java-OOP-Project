package com.jamfinder.composite;

import java.util.ArrayList;
import java.util.List;

// Composite - contiene gruppi di partecipanti
public class GruppoPartecipanti implements ComponenteJamSession {
    private String nome;
    private List<ComponenteJamSession> componenti;
    
    public GruppoPartecipanti(String nome) {
        this.nome = nome;
        this.componenti = new ArrayList<>();
    }
    
    // Aggiunge un componente al gruppo
    public void aggiungi(ComponenteJamSession componente) {
        componenti.add(componente);
    }
    
    // Rimuove un componente dal gruppo
    public void rimuovi(ComponenteJamSession componente) {
        componenti.remove(componente);
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public String getDescrizione() {
        return nome + " (" + componenti.size() + " partecipanti)";
    }
    
    @Override
    public void mostra() {
        System.out.println("Gruppo: " + nome);
        for (ComponenteJamSession componente : componenti) {
            componente.mostra();
        }
    }
    
    public int size() {
        return componenti.size();
    }
}