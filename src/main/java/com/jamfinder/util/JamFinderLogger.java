package com.jamfinder.util;

import java.util.logging.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Gestione centralizzata del logging per JamFinder
 * Implementa java.util.logging framework
 */
public class JamFinderLogger {
    
    private static final Logger logger = Logger.getLogger("JamFinder");
    private static boolean initialized = false;
    
    /**
     * Inizializza il sistema di logging
     */
    public static void initializeLogging() {
        if (initialized) return;
        
        try {
            // Configura il logger
            logger.setLevel(Level.ALL);
            
            // Console Handler con formato personalizzato
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            consoleHandler.setFormatter(new SimpleFormatter() {
                @Override
                public String format(LogRecord record) {
                    return String.format("[%s] %s - %s: %s%n",
                        record.getLevel(),
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                        record.getLoggerName(),
                        record.getMessage());
                }
            });
            
            // File Handler per logging su file
            try {
                FileHandler fileHandler = new FileHandler("data/jamfinder.log", true);
                fileHandler.setLevel(Level.ALL);
                fileHandler.setFormatter(new SimpleFormatter() {
                    @Override
                    public String format(LogRecord record) {
                        return String.format("%s [%s] %s - %s%n",
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                            record.getLevel(),
                            record.getLoggerName(),
                            record.getMessage());
                    }
                });
                
                logger.addHandler(fileHandler);
                logger.addHandler(consoleHandler);
                
            } catch (IOException e) {
                // Se non può creare il file, usa solo console
                logger.addHandler(consoleHandler);
                logger.warning("Impossibile creare file di log, uso solo console: " + e.getMessage());
            }
            
            // Disabilita il logger parent per evitare output duplicati
            logger.setUseParentHandlers(false);
            
            initialized = true;
            logger.info("Sistema di logging JamFinder inizializzato");
            
        } catch (Exception e) {
            System.err.println("Errore inizializzazione logging: " + e.getMessage());
        }
    }
    
    /**
     * Log informazioni generali
     */
    public static void info(String message) {
        initializeLogging();
        logger.info(message);
    }
    
    /**
     * Log warning
     */
    public static void warning(String message) {
        initializeLogging();
        logger.warning(message);
    }
    
    /**
     * Log errori
     */
    public static void error(String message, Throwable throwable) {
        initializeLogging();
        logger.log(Level.SEVERE, message, throwable);
    }
    
    /**
     * Log errori semplici
     */
    public static void error(String message) {
        initializeLogging();
        logger.severe(message);
    }
    
    /**
     * Log debug (solo se FINE level abilitato)
     */
    public static void debug(String message) {
        initializeLogging();
        logger.fine(message);
    }
    
    /**
     * Log operazioni di I/O
     */
    public static void logIO(String operation, String filename, boolean success) {
        if (success) {
            info(String.format("I/O Success: %s - %s", operation, filename));
        } else {
            warning(String.format("I/O Failed: %s - %s", operation, filename));
        }
    }
    
    /**
     * Log creazione oggetti (Factory)
     */
    public static void logFactoryCreation(String objectType, String objectId) {
        debug(String.format("Factory created: %s [ID: %s]", objectType, objectId));
    }
    
    /**
     * Log notifiche Observer
     */
    public static void logObserverNotification(String observerName, String message) {
        debug(String.format("Observer [%s] notified: %s", observerName, message));
    }
}
