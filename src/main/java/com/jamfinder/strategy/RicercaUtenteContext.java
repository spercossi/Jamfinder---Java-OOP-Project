package com.jamfinder.strategy;

import com.jamfinder.model.Utente;
import com.jamfinder.util.JamFinderLogger;
import java.util.List;

/**
 * Context per Strategy Pattern - Gestisce le strategie di ricerca
 */
public class RicercaUtenteContext {
    
    private RicercaUtenteStrategy strategy;
    
    /**
     * Costruttore con strategia di default
     */
    public RicercaUtenteContext() {
        this.strategy = new RicercaPerNomeStrategy();
    }
    
    /**
     * Costruttore con strategia specifica
     */
    public RicercaUtenteContext(RicercaUtenteStrategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     * Cambia strategia di ricerca
     */
    public void setStrategy(RicercaUtenteStrategy strategy) {
        this.strategy = strategy;
        JamFinderLogger.info("Strategia ricerca cambiata: " + strategy.getDescrizione());
    }
    
    /**
     * Esegue ricerca usando la strategia corrente
     */
    public List<Utente> ricerca(List<Utente> utenti, String criterio) {
        JamFinderLogger.debug("Eseguendo ricerca con: " + strategy.getDescrizione() + 
                            " - Criterio: " + criterio);
        
        List<Utente> risultati = strategy.cerca(utenti, criterio);
        
        JamFinderLogger.info("Ricerca completata: " + risultati.size() + " risultati trovati");
        return risultati;
    }
    
    /**
     * Ottiene descrizione strategia corrente
     */
    public String getDescrizioneStrategia() {
        return strategy.getDescrizione();
    }
}
