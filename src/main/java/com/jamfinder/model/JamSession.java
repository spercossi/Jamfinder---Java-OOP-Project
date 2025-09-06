package com.jamfinder.model;

import java.time.LocalDateTime;
import java.util.*;
import com.jamfinder.composite.GruppoPartecipanti;
import com.jamfinder.composite.Partecipante;
import com.jamfinder.observer.Observable;

// Classe per rappresentare una jam session
public class JamSession extends Observable {
    
    private String id;
    private String titolo;
    private String descrizione;
    private LocalDateTime dataOra;
    private String luogo;
    private String organizzatoreId;
    private Set<String> generiMusicali;
    private GruppoPartecipanti partecipanti;
    
    // Costruttore
    public JamSession(String titolo, String descrizione, LocalDateTime dataOra, 
                     String luogo, String organizzatoreId) {
        this.id = "jam_" + System.currentTimeMillis();
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataOra = dataOra;
        this.luogo = luogo;
        this.organizzatoreId = organizzatoreId;
        this.generiMusicali = new HashSet<>();
        this.partecipanti = new GruppoPartecipanti("Partecipanti " + titolo);
    }
    
    // Gestisce partecipanti
    public void aggiungiPartecipante(Utente utente, Strumento strumento) {
        try {
            Partecipante p = new Partecipante(utente, strumento);
            partecipanti.aggiungi(p);
            // Notifica i cambiamenti
            notificaOsservatori("Nuovo partecipante: " + utente.getNome());
        } catch (Exception e) {
            System.err.println("Errore aggiungendo partecipante: " + e.getMessage());
        }
    }
    
    public GruppoPartecipanti getPartecipanti() {
        return partecipanti;
    }
    
    // Collections con generics
    public void aggiungiGenere(String genere) {
        generiMusicali.add(genere);
        notificaOsservatori("Aggiunto genere: " + genere);
    }
    
    public Set<String> getGeneriMusicali() {
        return generiMusicali;
    }
    
    // Getter
    public String getId() { return id; }
    public String getTitolo() { return titolo; }
    public String getDescrizione() { return descrizione; }
    public LocalDateTime getDataOra() { return dataOra; }
    public String getLuogo() { return luogo; }
    public String getOrganizzatoreId() { return organizzatoreId; }
    
    @Override
    public String toString() {
        return titolo + " - " + luogo;
    }
}
