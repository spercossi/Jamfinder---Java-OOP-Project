package com.jamfinder.observer;

import java.util.List;
import java.util.ArrayList;

// Classe base Observable - gestisce gli osservatori
public abstract class Observable {
    private List<Observer> observers;
    
    protected Observable() {
        this.observers = new ArrayList<>();
    }
    
    // Aggiunge un osservatore
    public void addObserver(Observer observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }
    
    // Rimuove un osservatore
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    // Notifica tutti gli osservatori
    protected void notificaOsservatori(String messaggio) {
        for (Observer observer : observers) {
            observer.update(messaggio);
        }
    }
}