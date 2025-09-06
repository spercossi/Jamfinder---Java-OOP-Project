package com.jamfinder.model;

import java.util.*;
import com.jamfinder.observer.Observer;

// Classe per rappresentare un utente
public class Utente implements Iterable<Strumento>, Observer {
    
    private String id;
    private String nome;
    private String cognome;
    private String email;
    private String passwordHash;
    private Set<Strumento> strumenti;
    private Set<String> generiPreferiti;
    
    // Costruttore
    public Utente(String nome, String cognome, String email, String passwordHash) {
        this.id = "user_" + System.currentTimeMillis();
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.passwordHash = passwordHash;
        this.strumenti = new HashSet<>();
        this.generiPreferiti = new HashSet<>();
    }
    
    // Metodi per gestire strumenti
    public void aggiungiStrumento(Strumento strumento) {
        strumenti.add(strumento);
    }
    
    public Set<Strumento> getStrumenti() {
        return strumenti;
    }
    
    // Metodi per gestire generi musicali
    public void aggiungiGenerePreferito(String genere) {
        generiPreferiti.add(genere);
    }
    
    public Set<String> getGeneriPreferiti() {
        return generiPreferiti;
    }
    
    // Permette di usare for-each sugli strumenti
    @Override
    public Iterator<Strumento> iterator() {
        return strumenti.iterator();
    }
    
    // Observer pattern - riceve notifiche
    @Override
    public void update(String messaggio) {
        System.out.println("Notifica per " + nome + ": " + messaggio);
    }
    
    // Getter base
    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    
    @Override
    public String toString() {
        return nome + " " + cognome + " (" + email + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Utente utente = (Utente) obj;
        return Objects.equals(email, utente.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
