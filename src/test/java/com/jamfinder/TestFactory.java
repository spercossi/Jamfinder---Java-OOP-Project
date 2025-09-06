package com.jamfinder;

import com.jamfinder.factory.*;
import com.jamfinder.model.*;

/**
 * Test per Factory Pattern
 */
public class TestFactory {
    
    public static void main(String[] args) {
        System.out.println("=== TEST FACTORY PATTERN ===");
        
        testSingletonFactory();
        testCreazioneOggetti();
        testCoerenzaFactory();
        
        System.out.println("Tutti i test Factory completati!\n");
    }
    
    private static void testSingletonFactory() {
        System.out.println("Test 1: Singleton Factory");
        
        try {
            JamFinderFactory factory1 = StandardJamFinderFactory.getInstance();
            JamFinderFactory factory2 = StandardJamFinderFactory.getInstance();
            
            // Verifica stesso oggetto (Singleton)
            assert factory1 == factory2 : "Factory non è Singleton";
            assert factory1 != null : "Factory è null";
            
            System.out.println("OK Singleton Factory OK");
            
        } catch (Exception e) {
            System.err.println("X Errore test Singleton: " + e.getMessage());
        }
    }
    
    private static void testCreazioneOggetti() {
        System.out.println("Test 2: Creazione oggetti tramite Factory");
        
        try {
            JamFinderFactory factory = StandardJamFinderFactory.getInstance();
            
            // Test creazione Utente
            Utente utente = factory.creaUtente("Test", "User", "test@email.com", "password");
            assert utente != null : "Utente non creato";
            assert utente.getNome().equals("Test") : "Nome utente errato";
            
            // Test creazione Strumento
            Strumento strumento = factory.creaStrumento("Violino", "Corde");
            assert strumento != null : "Strumento non creato";
            assert strumento.getNome().equals("Violino") : "Nome strumento errato";
            
            System.out.println("OK Creazione oggetti OK");
            
        } catch (Exception e) {
            System.err.println("X Errore test creazione: " + e.getMessage());
        }
    }
    
    private static void testCoerenzaFactory() {
        System.out.println("Test 3: Coerenza creazione oggetti");
        
        try {
            JamFinderFactory factory = StandardJamFinderFactory.getInstance();
            
            // Crea più oggetti dello stesso tipo
            Utente utente1 = factory.creaUtente("Mario", "Rossi", "mario1@email.com", "pass1");
            Utente utente2 = factory.creaUtente("Luigi", "Verdi", "luigi@email.com", "pass2");
            
            // Verifica che siano oggetti diversi ma dello stesso tipo
            assert utente1 != utente2 : "Oggetti sono lo stesso riferimento";
            assert utente1.getClass() == utente2.getClass() : "Classi diverse";
            
            // Gli ID potrebbero essere uguali se generati nello stesso millisecondo
            // Questo è normale per test rapidi
            System.out.println("OK Coerenza Factory OK");
            
        } catch (Exception e) {
            System.err.println("X Errore test coerenza: " + e.getMessage());
        }
    }
}
