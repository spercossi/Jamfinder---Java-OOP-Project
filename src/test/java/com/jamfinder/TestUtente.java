package com.jamfinder;

import com.jamfinder.model.*;
import com.jamfinder.factory.*;

/**
 * Test semplici per la classe Utente
 */
public class TestUtente {
    
    public static void main(String[] args) {
        System.out.println("=== TEST CLASSE UTENTE ===");
        
        testCreazioneUtente();
        testAggiuntaStrumento();
        testValidazioneInput();
        
        System.out.println("Tutti i test completati!\n");
    }
    
    private static void testCreazioneUtente() {
        System.out.println("Test 1: Creazione utente");
        
        try {
            JamFinderFactory factory = StandardJamFinderFactory.getInstance();
            Utente mario = factory.creaUtente("Mario", "Rossi", "mario@email.com", "password123");
            
            // Verifica dati utente
            assert mario.getNome().equals("Mario") : "Nome non corretto";
            assert mario.getCognome().equals("Rossi") : "Cognome non corretto"; 
            assert mario.getEmail().equals("mario@email.com") : "Email non corretta";
            
            System.out.println("OK Creazione utente OK");
            
        } catch (Exception e) {
            System.err.println("X Errore creazione utente: " + e.getMessage());
        }
    }
    
    private static void testAggiuntaStrumento() {
        System.out.println("Test 2: Aggiunta strumento");
        
        try {
            JamFinderFactory factory = StandardJamFinderFactory.getInstance();
            Utente luca = factory.creaUtente("Luca", "Bianchi", "luca@email.com", "password456");
            Strumento chitarra = factory.creaStrumento("Chitarra", "Corde");
            
            luca.aggiungiStrumento(chitarra);
            
            // Verifica strumento aggiunto
            assert luca.getStrumenti().contains(chitarra) : "Strumento non aggiunto";
            assert luca.getStrumenti().size() == 1 : "Numero strumenti errato";
            
            System.out.println("OK Aggiunta strumento OK");
            
        } catch (Exception e) {
            System.err.println("X Errore aggiunta strumento: " + e.getMessage());
        }
    }
    
    private static void testValidazioneInput() {
        System.out.println("Test 3: Validazione input");
        
        try {
            JamFinderFactory factory = StandardJamFinderFactory.getInstance();
            
            // Test input null
            try {
                // Non usato intenzionalmente - test solo per eccezione
                factory.creaUtente(null, "Rossi", "test@email.com", "password");
                System.err.println("X Doveva lanciare eccezione per nome null");
            } catch (IllegalArgumentException e) {
                System.out.println("OK Validazione nome null OK");
            }
            
            // Test input vuoto
            try {
                // Non usato intenzionalmente - test solo per eccezione
                factory.creaUtente("", "Rossi", "test@email.com", "password");
                System.err.println("X Doveva lanciare eccezione per nome vuoto");
            } catch (IllegalArgumentException e) {
                System.out.println("OK Validazione nome vuoto OK");
            }
            
        } catch (Exception e) {
            System.err.println("X Errore test validazione: " + e.getMessage());
        }
    }
}
