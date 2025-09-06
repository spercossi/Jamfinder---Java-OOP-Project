package com.jamfinder.io;

import com.jamfinder.model.Utente;
import com.jamfinder.model.Strumento;
import java.io.*;
import java.util.*;

/**
 * Gestisce il salvataggio e caricamento dati su file
 * Implementa Java I/O per persistenza
 */
public class DataManager {
    
    private static final String DATA_DIR = "data";
    private static final String UTENTI_FILE = DATA_DIR + "/utenti.txt";
    private static final String STRUMENTI_FILE = DATA_DIR + "/strumenti.txt";
    
    public DataManager() {
        // Crea directory data se non esiste
        File dataDirectory = new File(DATA_DIR);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }
    }
    
    /**
     * Salva lista utenti su file
     */
    public void salvaUtenti(List<Utente> utenti) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(UTENTI_FILE))) {
            writer.println("# File utenti JamFinder");
            writer.println("# Formato: nome|cognome|email");
            
            for (Utente utente : utenti) {
                writer.printf("%s|%s|%s%n", 
                    utente.getNome(), 
                    utente.getCognome(), 
                    utente.getEmail());
            }
            
            System.out.println("Utenti salvati in: " + UTENTI_FILE);
        }
    }
    
    /**
     * Carica lista utenti da file
     */
    public List<Utente> caricaUtenti() throws IOException {
        List<Utente> utenti = new ArrayList<>();
        File file = new File(UTENTI_FILE);
        
        if (!file.exists()) {
            System.out.println("File utenti non trovato, lista vuota");
            return utenti;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                
                // Salta commenti e righe vuote
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }
                
                try {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 3) {
                        Utente utente = new Utente(parts[0], parts[1], parts[2], "default");
                        utenti.add(utente);
                    }
                } catch (Exception e) {
                    System.err.println("Errore riga " + lineNumber + ": " + e.getMessage());
                }
            }
            
            System.out.println("Caricati " + utenti.size() + " utenti da: " + UTENTI_FILE);
        }
        
        return utenti;
    }
    
    /**
     * Salva lista strumenti su file
     */
    public void salvaStrumenti(Set<Strumento> strumenti) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STRUMENTI_FILE))) {
            writer.println("# File strumenti JamFinder");
            writer.println("# Formato: nome|categoria");
            
            for (Strumento strumento : strumenti) {
                writer.printf("%s|%s%n", 
                    strumento.getNome(), 
                    strumento.getCategoria());
            }
            
            System.out.println("Strumenti salvati in: " + STRUMENTI_FILE);
        }
    }
    
    /**
     * Carica set strumenti da file
     */
    public Set<Strumento> caricaStrumenti() throws IOException {
        Set<Strumento> strumenti = new HashSet<>();
        File file = new File(STRUMENTI_FILE);
        
        if (!file.exists()) {
            System.out.println("File strumenti non trovato, set vuoto");
            return strumenti;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                
                // Salta commenti e righe vuote
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }
                
                try {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 2) {
                        Strumento strumento = new Strumento(parts[0], parts[1]);
                        strumenti.add(strumento);
                    }
                } catch (Exception e) {
                    System.err.println("Errore riga " + lineNumber + ": " + e.getMessage());
                }
            }
            
            System.out.println("Caricati " + strumenti.size() + " strumenti da: " + STRUMENTI_FILE);
        }
        
        return strumenti;
    }
    
    /**
     * Esporta statistiche su file
     */
    public void esportaStatistiche(List<Utente> utenti, Set<Strumento> strumenti) throws IOException {
        String statsFile = DATA_DIR + "/statistiche.txt";
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(statsFile))) {
            writer.println("=== STATISTICHE JAMFINDER ===");
            writer.println("Data: " + new Date());
            writer.println();
            
            writer.println("UTENTI REGISTRATI: " + utenti.size());
            for (Utente u : utenti) {
                writer.println("- " + u.getNome() + " " + u.getCognome() + 
                              " (" + u.getStrumenti().size() + " strumenti)");
            }
            
            writer.println();
            writer.println("STRUMENTI DISPONIBILI: " + strumenti.size());
            Map<String, Integer> categorieCount = new HashMap<>();
            
            for (Strumento s : strumenti) {
                categorieCount.merge(s.getCategoria(), 1, Integer::sum);
            }
            
            for (Map.Entry<String, Integer> entry : categorieCount.entrySet()) {
                writer.println("- " + entry.getKey() + ": " + entry.getValue());
            }
            
            System.out.println("Statistiche esportate in: " + statsFile);
        }
    }
}
