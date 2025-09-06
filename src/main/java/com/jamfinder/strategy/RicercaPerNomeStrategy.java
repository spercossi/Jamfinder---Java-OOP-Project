package com.jamfinder.strategy;

import com.jamfinder.model.Utente;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Strategia concreta - Ricerca per nome
 */
public class RicercaPerNomeStrategy implements RicercaUtenteStrategy {
    
    @Override
    public List<Utente> cerca(List<Utente> utenti, String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return utenti;
        }
        
        String criterioBasso = criterio.toLowerCase().trim();
        
        return utenti.stream()
                .filter(utente -> 
                    utente.getNome().toLowerCase().contains(criterioBasso) ||
                    utente.getCognome().toLowerCase().contains(criterioBasso))
                .collect(Collectors.toList());
    }
    
    @Override
    public String getDescrizione() {
        return "Ricerca utenti per nome/cognome";
    }
}
