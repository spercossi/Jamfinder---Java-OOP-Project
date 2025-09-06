package com.jamfinder.factory;

import com.jamfinder.model.Utente;
import com.jamfinder.model.JamSession;
import com.jamfinder.model.Strumento;
import java.time.LocalDateTime;

// Factory Pattern - classe astratta per creare oggetti
public abstract class JamFinderFactory {
    
    // Metodi astratti che le sottoclassi devono implementare
    public abstract Utente creaUtente(String nome, String cognome, String email, String password);
    
    public abstract JamSession creaJamSession(String titolo, String descrizione, LocalDateTime dataOra, 
                                            String luogo, String organizzatoreId);
    
    public abstract Strumento creaStrumento(String nome, String categoria);
    
    // Metodo statico per ottenere l'istanza (Factory method)
    public static JamFinderFactory getInstance() {
        return StandardJamFinderFactory.getInstance();
    }
}