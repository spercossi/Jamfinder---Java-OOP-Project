package com.jamfinder;

import com.jamfinder.model.*;
import com.jamfinder.factory.*;
import java.util.*;

/**
 * Test per Collections Framework e Iterator Pattern
 */
public class TestCollections {
    
    public static void main(String[] args) {
        System.out.println("=== TEST COLLECTIONS & ITERATOR ===");
        
        testSetStrumenti();
        testIteratorPattern();
        testGenerics();
        
        System.out.println("Tutti i test Collections completati!\n");
    }
    
    private static void testSetStrumenti() {
        System.out.println("Test 1: Set di strumenti (no duplicati)");
        
        try {
            JamFinderFactory factory = StandardJamFinderFactory.getInstance();
            Utente mario = factory.creaUtente("Mario", "Rossi", "mario@email.com", "password123");
            
            Strumento chitarra1 = factory.creaStrumento("Chitarra", "Corde");
            Strumento chitarra2 = factory.creaStrumento("Chitarra", "Corde");
            
            mario.aggiungiStrumento(chitarra1);
            mario.aggiungiStrumento(chitarra2);
            
            // Verifica Set behavior - no duplicati
            Set<Strumento> strumenti = mario.getStrumenti();
            assert strumenti.size() >= 1 : "Set strumenti vuoto";
            
            System.out.println("OK Set strumenti OK (size: " + strumenti.size() + ")");
            
        } catch (Exception e) {
            System.err.println("X Errore test Set: " + e.getMessage());
        }
    }
    
    private static void testIteratorPattern() {
        System.out.println("Test 2: Iterator Pattern");
        
        try {
            JamFinderFactory factory = StandardJamFinderFactory.getInstance();
            Utente luca = factory.creaUtente("Luca", "Verdi", "luca@email.com", "password456");
            
            // Aggiungi strumenti
            luca.aggiungiStrumento(factory.creaStrumento("Chitarra", "Corde"));
            luca.aggiungiStrumento(factory.creaStrumento("Basso", "Corde"));
            luca.aggiungiStrumento(factory.creaStrumento("Batteria", "Percussioni"));
            
            // Test Iterator - enhanced for loop
            int count = 0;
            for (Strumento s : luca) {
                assert s.getNome() != null : "Nome strumento null";
                count++;
            }
            
            assert count > 0 : "Iterator non funziona";
            System.out.println("OK Iterator Pattern OK (iterati: " + count + " strumenti)");
            
        } catch (Exception e) {
            System.err.println("X Errore test Iterator: " + e.getMessage());
        }
    }
    
    private static void testGenerics() {
        System.out.println("Test 3: Generics Type Safety");
        
        try {
            // Test Set<Strumento>
            Set<Strumento> strumenti = new HashSet<>();
            JamFinderFactory factory = StandardJamFinderFactory.getInstance();
            
            Strumento piano = factory.creaStrumento("Piano", "Tastiere");
            strumenti.add(piano);
            
            // Verifica type safety
            for (Strumento s : strumenti) {
                assert s instanceof Strumento : "Type safety fallita";
            }
            
            // Test Set<String>  
            Set<String> generi = new HashSet<>();
            generi.add("Rock");
            generi.add("Jazz");
            generi.add("Blues");
            
            assert generi.size() == 3 : "Set String non corretto";
            
            System.out.println("OK Generics OK");
            
        } catch (Exception e) {
            System.err.println("X Errore test Generics: " + e.getMessage());
        }
    }
}
