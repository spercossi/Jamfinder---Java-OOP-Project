package com.jamfinder.strategy;

import com.jamfinder.model.Utente;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Strategia concreta - Ricerca per strumento
 */
public class RicercaPerStrumentoStrategy implements RicercaUtenteStrategy {
    
    @Override
    public List<Utente> cerca(List<Utente> utenti, String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return utenti;
        }
        
        String criterioBasso = criterio.toLowerCase().trim();
        
        return utenti.stream()
                .filter(utente -> 
                    utente.getStrumenti().stream()
                        .anyMatch(strumento -> 
                            strumento.getNome().toLowerCase().contains(criterioBasso) ||
                            strumento.getCategoria().toLowerCase().contains(criterioBasso)))
                .collect(Collectors.toList());
    }
    
    @Override
    public String getDescrizione() {
        return "Ricerca utenti per strumento suonato";
    }
}
