package com.jamfinder;

import com.jamfinder.io.DataManager;
import com.jamfinder.model.Utente;
import com.jamfinder.model.Strumento;
import java.util.*;
import java.io.IOException;

/**
 * Test per funzionalità Java I/O
 */
public class TestJavaIO {
    
    public static void main(String[] args) {
        System.out.println("=== TEST JAVA I/O ===");
        
        testSalvataggioCaricamento();
        testGestioneErrori();
        
        System.out.println("Tutti i test I/O completati!");
    }
    
    private static void testSalvataggioCaricamento() {
        System.out.println("Test 1: Salvataggio e caricamento dati");
        
        try {
            DataManager dataManager = new DataManager();
            
            // Crea dati di test
            List<Utente> utenti = Arrays.asList(
                new Utente("Mario", "Rossi", "mario@test.com", "pass1"),
                new Utente("Anna", "Verdi", "anna@test.com", "pass2")
            );
            
            Set<Strumento> strumenti = new HashSet<>(Arrays.asList(
                new Strumento("Chitarra", "Corde"),
                new Strumento("Piano", "Tastiera"),
                new Strumento("Violino", "Corde")
            ));
            
            // Test salvataggio
            dataManager.salvaUtenti(utenti);
            dataManager.salvaStrumenti(strumenti);
            dataManager.esportaStatistiche(utenti, strumenti);
            
            // Test caricamento
            List<Utente> utentiCaricati = dataManager.caricaUtenti();
            Set<Strumento> strumentiCaricati = dataManager.caricaStrumenti();
            
            // Verifica
            assert utentiCaricati.size() == 2 : "Numero utenti caricati errato";
            assert strumentiCaricati.size() == 3 : "Numero strumenti caricati errato";
            
            System.out.println("OK Test salvataggio/caricamento OK");
            
        } catch (IOException e) {
            System.err.println("Errore test I/O: " + e.getMessage());
        }
    }
    
    private static void testGestioneErrori() {
        System.out.println("Test 2: Gestione errori I/O");
        
        DataManager dataManager = new DataManager();
        
        try {
            // Test caricamento file inesistente
            List<Utente> utenti = dataManager.caricaUtenti();
            Set<Strumento> strumenti = dataManager.caricaStrumenti();
            
            // Dovrebbe gestire gracefully file mancanti
            assert utenti != null : "Lista utenti non dovrebbe essere null";
            assert strumenti != null : "Set strumenti non dovrebbe essere null";
            
            System.out.println("OK Gestione errori I/O OK");
            
        } catch (Exception e) {
            System.err.println("Errore inaspettato: " + e.getMessage());
        }
    }
}
