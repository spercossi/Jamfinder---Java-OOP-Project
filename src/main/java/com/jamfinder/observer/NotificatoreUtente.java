package com.jamfinder.observer;

/**
 * Classe per notificare utenti
 */
public class NotificatoreUtente implements Observer {
    private String nome;
    
    public NotificatoreUtente(String nome) {
        this.nome = nome;
    }
    
    @Override
    public void update(String messaggio) {
        System.out.println("[" + nome + "] Notifica ricevuta: " + messaggio);
    }
    
    public String getNome() {
        return nome;
    }
}