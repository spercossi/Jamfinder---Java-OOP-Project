package com.jamfinder;

/**
 * Classe principale per eseguire tutti i test
 * Test suite semplice senza framework esterni
 */
public class RunAllTests {
    
    public static void main(String[] args) {
        System.out.println("===================================");
        System.out.println("    JAMFINDER - TEST SUITE        ");
        System.out.println("===================================\n");
        
        // Flag per abilitare assertions
        boolean assertionsEnabled = false;
        assert assertionsEnabled = true; // Se assertions abilitate, questo sarà true
        
        if (!assertionsEnabled) {
            System.out.println("ATTENZIONE: Le assertions non sono abilitate!");
            System.out.println("Esegui con: java -ea com.jamfinder.RunAllTests\n");
        }
        
        try {
            // Test Factory Pattern
            System.out.println("1. FACTORY PATTERN");
            TestFactory.main(args);
            
            // Test Model (Utente)
            System.out.println("2. MODEL CLASSES");
            TestUtente.main(args);
            
            // Test Collections & Iterator
            System.out.println("3. COLLECTIONS FRAMEWORK");
            TestCollections.main(args);
            
            // Test Observer Pattern  
            System.out.println("4. OBSERVER PATTERN");
            TestObserver.main(args);
            
            // Test Composite Pattern
            System.out.println("5. COMPOSITE PATTERN");
            TestComposite.main(args);
            
            // Test Java I/O
            System.out.println("6. JAVA I/O");
            TestJavaIO.main(args);
            
            // Test Strategy Pattern
            System.out.println("7. STRATEGY PATTERN");
            TestStrategy.main(args);
            
            // Riepilogo finale
            System.out.println("===================================");
            System.out.println("TUTTI I TEST COMPLETATI!");
            System.out.println("===================================");
            System.out.println("\nPattern testati:");
            System.out.println("+ Factory Pattern");
            System.out.println("+ Observer Pattern");
            System.out.println("+ Composite Pattern");
            System.out.println("+ Iterator Pattern (Collections)");
            System.out.println("+ Strategy Pattern");
            System.out.println("+ Java Collections Framework con Generics");
            System.out.println("+ Java I/O per persistenza dati");
            System.out.println("+ Logging con java.util.logging");
            System.out.println("\nClassi testate:");
            System.out.println("+ Utente, Strumento, JamSession");
            System.out.println("+ StandardJamFinderFactory");
            System.out.println("+ Observer/Observable interfaces");
            System.out.println("+ DataManager per I/O operations");
            System.out.println("+ RicercaUtenteContext e strategie");
            
        } catch (Exception e) {
            System.err.println("\nERRORE NEI TEST: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
