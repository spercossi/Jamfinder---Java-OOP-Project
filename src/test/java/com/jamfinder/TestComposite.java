package com.jamfinder;

import com.jamfinder.composite.*;
import com.jamfinder.model.*;
import com.jamfinder.factory.*;

/**
 * Test per il Pattern Composite
 */
public class TestComposite {
    
    public static void main(String[] args) {
        System.out.println("=== TEST COMPOSITE PATTERN ===");
        
        testPartecipanteSingolo();
        testGruppoPartecipanti();
        testGruppoNested();
        
        System.out.println("Tutti i test Composite completati!\n");
    }
    
    private static void testPartecipanteSingolo() {
        System.out.println("Test 1: Partecipante singolo");
        
        try {
            JamFinderFactory factory = StandardJamFinderFactory.getInstance();
            Utente mario = factory.creaUtente("Mario", "Rossi", "mario@email.com", "password123");
            Strumento chitarra = factory.creaStrumento("Chitarra", "Corde");
            
            ComponenteJamSession partecipante = new Partecipante(mario, chitarra);
            
            // Test metodi interfaccia
            assert partecipante.getNome().equals("Mario") : "Nome partecipante errato";
            partecipante.mostra();
            
            System.out.println("OK Partecipante singolo OK");
            
        } catch (Exception e) {
            System.err.println("X Errore test partecipante: " + e.getMessage());
        }
    }
    
    private static void testGruppoPartecipanti() {
        System.out.println("Test 2: Gruppo partecipanti");
        
        try {
            JamFinderFactory factory = StandardJamFinderFactory.getInstance();
            
            // Crea utenti e strumenti
            Utente luca = factory.creaUtente("Luca", "Verdi", "luca@email.com", "password456");
            Utente anna = factory.creaUtente("Anna", "Blu", "anna@email.com", "password789");
            Strumento basso = factory.creaStrumento("Basso", "Corde");
            Strumento batteria = factory.creaStrumento("Batteria", "Percussioni");
            
            // Crea partecipanti
            ComponenteJamSession partecipante1 = new Partecipante(luca, basso);
            ComponenteJamSession partecipante2 = new Partecipante(anna, batteria);
            
            // Crea gruppo
            GruppoPartecipanti gruppo = new GruppoPartecipanti("Rock Band");
            gruppo.aggiungi(partecipante1);
            gruppo.aggiungi(partecipante2);
            
            // Test operazioni gruppo
            assert gruppo.getNome().equals("Rock Band") : "Nome gruppo errato";
            gruppo.mostra();
            
            System.out.println("OK Gruppo partecipanti OK");
            
        } catch (Exception e) {
            System.err.println("X Errore test gruppo: " + e.getMessage());
        }
    }
    
    private static void testGruppoNested() {
        System.out.println("Test 3: Gruppi annidati");
        
        try {
            JamFinderFactory factory = StandardJamFinderFactory.getInstance();
            
            // Crea utenti e strumenti
            Utente marco = factory.creaUtente("Marco", "Neri", "marco@email.com", "password111");
            Utente sara = factory.creaUtente("Sara", "Rosa", "sara@email.com", "password222");
            Strumento piano = factory.creaStrumento("Piano", "Tastiere");
            Strumento violino = factory.creaStrumento("Violino", "Corde");
            
            // Crea partecipanti
            ComponenteJamSession partecipante1 = new Partecipante(marco, piano);
            ComponenteJamSession partecipante2 = new Partecipante(sara, violino);
            
            // Crea sotto-gruppo
            GruppoPartecipanti sottoGruppo = new GruppoPartecipanti("Sezione Ritmica");
            sottoGruppo.aggiungi(partecipante1);
            sottoGruppo.aggiungi(partecipante2);
            
            // Crea gruppo principale
            GruppoPartecipanti gruppoPrincipale = new GruppoPartecipanti("Band Completa");
            gruppoPrincipale.aggiungi(sottoGruppo);
            
            // Test struttura annidata
            assert gruppoPrincipale.getNome().equals("Band Completa") : "Nome gruppo principale errato";
            gruppoPrincipale.mostra();
            
            System.out.println("OK Gruppi annidati OK");
            
        } catch (Exception e) {
            System.err.println("X Errore test gruppi nested: " + e.getMessage());
        }
    }
}
