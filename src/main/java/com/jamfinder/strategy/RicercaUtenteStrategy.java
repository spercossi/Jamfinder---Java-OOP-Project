package com.jamfinder.strategy;

import com.jamfinder.model.Utente;
import java.util.List;

/**
 * Strategy Pattern - Interfaccia per algoritmi di ricerca utenti
 */
public interface RicercaUtenteStrategy {
    
    /**
     * Cerca utenti secondo la strategia implementata
     */
    List<Utente> cerca(List<Utente> utenti, String criterio);
    
    /**
     * Descrizione della strategia
     */
    String getDescrizione();
}
