package com.jamfinder.model;

import java.util.Objects;

// Classe per rappresentare uno strumento musicale
public class Strumento {
    
    private String nome;
    private String categoria;
    
    // Costruttore
    public Strumento(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }
    
    // Getter
    public String getNome() {
        return nome;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    @Override
    public String toString() {
        return nome + " (" + categoria + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Strumento strumento = (Strumento) obj;
        return Objects.equals(nome, strumento.nome) && 
               Objects.equals(categoria, strumento.categoria);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nome, categoria);
    }
}
