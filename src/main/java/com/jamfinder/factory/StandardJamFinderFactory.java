package com.jamfinder.factory;

import com.jamfinder.model.Utente;
import com.jamfinder.model.JamSession;
import com.jamfinder.model.Strumento;
import com.jamfinder.util.JamFinderLogger;
import java.time.LocalDateTime;

// Implementazione concreta della Factory (Singleton)
public class StandardJamFinderFactory extends JamFinderFactory {
    private static StandardJamFinderFactory instance;
    
    private StandardJamFinderFactory() {
        // Costruttore privato per Singleton pattern
    }
    
    // Singleton pattern - una sola istanza
    public static StandardJamFinderFactory getInstance() {
        if (instance == null) {
            instance = new StandardJamFinderFactory();
        }
        return instance;
    }
    
    @Override
    public Utente creaUtente(String nome, String cognome, String email, String password) {
        // Validazione input (gestione eccezioni)
        if (nome == null || nome.trim().isEmpty()) {
            JamFinderLogger.warning("Tentativo creazione utente con nome non valido: " + nome);
            throw new IllegalArgumentException("Nome non valido");
        }
        if (email == null || email.trim().isEmpty()) {
            JamFinderLogger.warning("Tentativo creazione utente con email non valida: " + email);
            throw new IllegalArgumentException("Email non valida");
        }
        
        Utente utente = new Utente(nome, cognome, email, password);
        JamFinderLogger.logFactoryCreation("Utente", utente.getId());
        return utente;
    }
    
    @Override
    public JamSession creaJamSession(String titolo, String descrizione, LocalDateTime dataOra, 
                                   String luogo, String organizzatoreId) {
        if (titolo == null || titolo.trim().isEmpty()) {
            JamFinderLogger.warning("Tentativo creazione JamSession con titolo non valido: " + titolo);
            throw new IllegalArgumentException("Titolo non valido");
        }
        
        JamSession session = new JamSession(titolo, descrizione, dataOra, luogo, organizzatoreId);
        JamFinderLogger.logFactoryCreation("JamSession", session.getId());
        return session;
    }
    
    @Override
    public Strumento creaStrumento(String nome, String categoria) {
        if (nome == null || nome.trim().isEmpty()) {
            JamFinderLogger.warning("Tentativo creazione Strumento con nome non valido: " + nome);
            throw new IllegalArgumentException("Nome strumento non valido");
        }
        
        Strumento strumento = new Strumento(nome, categoria);
        JamFinderLogger.logFactoryCreation("Strumento", nome);
        return strumento;
    }
}