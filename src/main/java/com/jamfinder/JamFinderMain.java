package com.jamfinder;

import com.jamfinder.model.*;
import com.jamfinder.factory.*;
import com.jamfinder.observer.*;
import com.jamfinder.io.DataManager;
import com.jamfinder.util.JamFinderLogger;
import com.jamfinder.strategy.*;
import java.util.*;
import java.io.IOException;

/**
 * Applicazione principale per JamFinder.
 */
public class JamFinderMain {
    
    public static void main(String[] args) {
        try {
            // Inizializza logging
            JamFinderLogger.initializeLogging();
            JamFinderLogger.info("=== AVVIO JAMFINDER ===");
            
            System.out.println("=== AVVIO JAMFINDER ===\n");
            
            avviaApplicazione();
            
            JamFinderLogger.info("=== JAMFINDER TERMINATO ===");
            System.out.println("\n=== JAMFINDER TERMINATO ===");
            
        } catch (Exception e) {
            JamFinderLogger.error("Errore nell'applicazione", e);
            System.err.println("Errore nell'applicazione: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void avviaApplicazione() {
        // Creazione oggetti con factory
        System.out.println("CREAZIONE OGGETTI:");
        JamFinderFactory factory = StandardJamFinderFactory.getInstance();
        
        Utente utente1 = factory.creaUtente("Marco", "Rossi", "marco@email.com", "password123");
        Utente utente2 = factory.creaUtente("Lucia", "Verdi", "lucia@email.com", "password456");
        JamSession session = factory.creaJamSession("Rock Session", "Jam di rock", 
            java.time.LocalDateTime.now().plusDays(1), "Milano", "org1");
        
        System.out.println("Creati utenti e session tramite Factory\n");
        
        // Sistema di notifiche
        System.out.println("NOTIFICHE:");
        NotificatoreUtente notificatore1 = new NotificatoreUtente("NotificatoreMarco");
        NotificatoreUtente notificatore2 = new NotificatoreUtente("NotificatoreLucia");
        
        session.addObserver(notificatore1);
        session.addObserver(notificatore2);
        
        session.aggiungiGenere("Rock");
        System.out.println("");
        
        // Collections e Generics
        System.out.println("COLLECTIONS CON GENERICS:");
        Strumento chitarra = new Strumento("Chitarra", "Corde");
        Strumento basso = new Strumento("Basso", "Corde");
        Strumento batteria = new Strumento("Batteria", "Percussioni");
        
        utente1.aggiungiStrumento(chitarra);
        utente1.aggiungiStrumento(basso);
        utente2.aggiungiStrumento(batteria);
        
        System.out.println("Strumenti aggiunti\n");
        
        // Java I/O - Persistenza dati
        System.out.println("JAVA I/O - PERSISTENZA DATI:");
        JamFinderLogger.info("Iniziando operazioni I/O per persistenza dati");
        DataManager dataManager = new DataManager();
        
        try {
            // Salvataggio dati
            List<Utente> utenti = Arrays.asList(utente1, utente2);
            Set<Strumento> strumenti = new HashSet<>(Arrays.asList(chitarra, basso, batteria));
            
            dataManager.salvaUtenti(utenti);
            JamFinderLogger.logIO("Salvataggio utenti", "data/utenti.txt", true);
            
            dataManager.salvaStrumenti(strumenti);
            JamFinderLogger.logIO("Salvataggio strumenti", "data/strumenti.txt", true);
            
            dataManager.esportaStatistiche(utenti, strumenti);
            JamFinderLogger.logIO("Esportazione statistiche", "data/statistiche.txt", true);
            
            // Caricamento dati (test)
            List<Utente> utentiCaricati = dataManager.caricaUtenti();
            Set<Strumento> strumentiCaricati = dataManager.caricaStrumenti();
            
            JamFinderLogger.info("Caricati " + utentiCaricati.size() + " utenti e " + 
                               strumentiCaricati.size() + " strumenti da file");
            
            System.out.println("Caricati " + utentiCaricati.size() + " utenti e " + 
                             strumentiCaricati.size() + " strumenti da file");
            
        } catch (IOException e) {
            JamFinderLogger.error("Errore I/O durante persistenza", e);
            System.err.println("Errore I/O: " + e.getMessage());
        }
        System.out.println("");
        
        // Scorrimento collezioni
        System.out.println("SCORRIMENTO STRUMENTI:");
        System.out.println("Strumenti di " + utente1.getNome() + ":");
        Iterator<Strumento> iterator = utente1.getStrumenti().iterator();
        while (iterator.hasNext()) {
            Strumento s = iterator.next();
            System.out.println("- " + s.getNome() + " (" + s.getCategoria() + ")");
        }
        System.out.println("");
        
        // Gestione partecipanti
        System.out.println("GESTIONE PARTECIPANTI:");
        session.aggiungiPartecipante(utente1, chitarra);
        session.aggiungiPartecipante(utente2, batteria);
        System.out.println("Partecipanti aggiunti alla session: " + session.getTitolo());
        session.getPartecipanti().mostra();
        System.out.println("");
        
        // Exception Handling
        System.out.println("EXCEPTION HANDLING:");
        try {
            factory.creaUtente("", "", "email-non-valida", "");
        } catch (IllegalArgumentException e) {
            System.out.println("Eccezione gestita: " + e.getMessage());
        }
        
        // Strategy Pattern - Sistema di ricerca
        System.out.println("\nSTRATEGY PATTERN - RICERCA UTENTI:");
        List<Utente> tuttiUtenti = Arrays.asList(utente1, utente2);
        
        // Ricerca per nome
        RicercaUtenteContext ricercaContext = new RicercaUtenteContext(new RicercaPerNomeStrategy());
        System.out.println("Strategia: " + ricercaContext.getDescrizioneStrategia());
        List<Utente> risultatiNome = ricercaContext.ricerca(tuttiUtenti, "Marco");
        System.out.println("Trovati " + risultatiNome.size() + " utenti con nome 'Marco'");
        
        // Cambio strategia - ricerca per strumento
        ricercaContext.setStrategy(new RicercaPerStrumentoStrategy());
        System.out.println("Strategia: " + ricercaContext.getDescrizioneStrategia());
        List<Utente> risultatiStrumento = ricercaContext.ricerca(tuttiUtenti, "Chitarra");
        System.out.println("Trovati " + risultatiStrumento.size() + " utenti che suonano 'Chitarra'");
    }
}
