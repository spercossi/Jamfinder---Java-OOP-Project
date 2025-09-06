package com.jamfinder;

import com.jamfinder.model.*;
import com.jamfinder.factory.*;
import java.time.LocalDateTime;

/**
 * Test per il Pattern Observer
 */
public class TestObserver {
    
    public static void main(String[] args) {
        System.out.println("=== TEST OBSERVER PATTERN ===");
        
        testNotificheUtente();
        testMultipliObserver();
        
        System.out.println("Tutti i test Observer completati!\n");
    }
    
    private static void testNotificheUtente() {
        System.out.println("Test 1: Notifiche a singolo utente");
        
        try {
            JamFinderFactory factory = StandardJamFinderFactory.getInstance();
            
            // Crea utente e session
            Utente marco = factory.creaUtente("Marco", "Verdi", "marco@email.com", "password123");
            JamSession session = factory.creaJamSession("Rock Night", "Session rock", 
                                                       LocalDateTime.now(), "Studio A", "org1");
            
            // Registra observer
            session.addObserver(marco);
            
            // Trigger notifica
            session.aggiungiGenere("Rock");
            
            System.out.println("OK Notifica utente singolo OK");
            
        } catch (Exception e) {
            System.err.println("X Errore test notifiche: " + e.getMessage());
        }
    }
    
    private static void testMultipliObserver() {
        System.out.println("Test 2: Notifiche a multipli observer");
        
        try {
            JamFinderFactory factory = StandardJamFinderFactory.getInstance();
            
            // Crea utenti
            Utente anna = factory.creaUtente("Anna", "Blu", "anna@email.com", "password456");
            Utente luca = factory.creaUtente("Luca", "Rossi", "luca@email.com", "password789");
            
            // Crea session
            JamSession session = factory.creaJamSession("Jazz Night", "Session jazz", 
                                                       LocalDateTime.now(), "Studio B", "org2");
            
            // Registra multipli observer
            session.addObserver(anna);
            session.addObserver(luca);
            
            // Trigger notifica
            session.aggiungiGenere("Jazz");
            
            System.out.println("OK Notifiche multipli observer OK");
            
        } catch (Exception e) {
            System.err.println("X Errore test multipli observer: " + e.getMessage());
        }
    }
}
