package com.jamfinder;

import com.jamfinder.strategy.*;
import com.jamfinder.model.Utente;
import com.jamfinder.model.Strumento;
import java.util.*;

/**
 * Test per Strategy Pattern
 */
public class TestStrategy {
    
    public static void main(String[] args) {
        System.out.println("=== TEST STRATEGY PATTERN ===");
        
        testRicercaPerNome();
        testRicercaPerStrumento();
        testCambioStrategia();
        
        System.out.println("Tutti i test Strategy completati!");
    }
    
    private static void testRicercaPerNome() {
        System.out.println("Test 1: Ricerca per nome");
        
        // Crea utenti di test
        List<Utente> utenti = Arrays.asList(
            new Utente("Mario", "Rossi", "mario@test.com", "pass1"),
            new Utente("Anna", "Verdi", "anna@test.com", "pass2"),
            new Utente("Marco", "Bianchi", "marco@test.com", "pass3")
        );
        
        RicercaUtenteContext context = new RicercaUtenteContext(new RicercaPerNomeStrategy());
        
        // Test ricerca esistente
        List<Utente> risultati = context.ricerca(utenti, "Mario");
        assert risultati.size() == 1 : "Dovrebbe trovare 1 utente con nome Mario";
        assert risultati.get(0).getNome().equals("Mario") : "Dovrebbe trovare Mario";
        
        // Test ricerca multipla
        risultati = context.ricerca(utenti, "Mar");
        assert risultati.size() == 2 : "Dovrebbe trovare 2 utenti con 'Mar'";
        
        System.out.println("OK Ricerca per nome OK");
    }
    
    private static void testRicercaPerStrumento() {
        System.out.println("Test 2: Ricerca per strumento");
        
        // Crea utenti con strumenti
        Utente utente1 = new Utente("Mario", "Rossi", "mario@test.com", "pass1");
        utente1.aggiungiStrumento(new Strumento("Chitarra", "Corde"));
        
        Utente utente2 = new Utente("Anna", "Verdi", "anna@test.com", "pass2");
        utente2.aggiungiStrumento(new Strumento("Piano", "Tastiera"));
        
        Utente utente3 = new Utente("Luca", "Bianchi", "luca@test.com", "pass3");
        utente3.aggiungiStrumento(new Strumento("Basso", "Corde"));
        
        List<Utente> utenti = Arrays.asList(utente1, utente2, utente3);
        
        RicercaUtenteContext context = new RicercaUtenteContext(new RicercaPerStrumentoStrategy());
        
        // Test ricerca per strumento specifico
        List<Utente> risultati = context.ricerca(utenti, "Chitarra");
        assert risultati.size() == 1 : "Dovrebbe trovare 1 utente con Chitarra";
        
        // Test ricerca per categoria
        risultati = context.ricerca(utenti, "Corde");
        assert risultati.size() == 2 : "Dovrebbe trovare 2 utenti con strumenti a Corde";
        
        System.out.println("OK Ricerca per strumento OK");
    }
    
    private static void testCambioStrategia() {
        System.out.println("Test 3: Cambio strategia runtime");
        
        RicercaUtenteContext context = new RicercaUtenteContext();
        
        // Test strategia iniziale
        assert context.getDescrizioneStrategia().contains("nome") : "Strategia default dovrebbe essere per nome";
        
        // Cambio strategia
        context.setStrategy(new RicercaPerStrumentoStrategy());
        assert context.getDescrizioneStrategia().contains("strumento") : "Strategia dovrebbe essere cambiata";
        
        System.out.println("OK Cambio strategia OK");
    }
}
