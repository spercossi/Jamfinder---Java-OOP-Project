# JamFinder - Sistema di Gestione Jam Session

## Descrizione del Progetto

JamFinder è un'applicazione Java per la gestione di jam session musicali. Il progetto implementa 4 Design Pattern fondamentali (Factory, Observer, Composite, Iterator) e utilizza le Java Collections Framework con Generics per gestire utenti, strumenti musicali e sessioni musicali.

## Funzionalità Principali

- **Gestione Utenti**: Creazione e gestione di musicisti
- **Gestione Strumenti**: Catalogazione strumenti musicali  
- **Jam Session**: Organizzazione di sessioni musicali
- **Sistema Notifiche**: Notifiche automatiche agli utenti interessati

## Design Pattern Implementati

### 1. Factory Pattern
**Posizione**: `src/main/java/com/jamfinder/factory/`
- `JamFinderFactory` (interfaccia)
- `StandardJamFinderFactory` (implementazione)

Gestisce la creazione centralizzata degli oggetti del sistema.

### 2. Observer Pattern  
**Posizione**: `src/main/java/com/jamfinder/observer/`
- `Observer` (interfaccia)
- `Observable` (interfaccia) 
- `NotificatoreUtente` (implementazione)

Permette di notificare automaticamente gli utenti quando avvengono cambiamenti nelle jam session.

### 3. Composite Pattern
**Posizione**: `src/main/java/com/jamfinder/composite/`
- `ComponenteJamSession` (interfaccia)
- `Partecipante` (foglia)
- `GruppoPartecipanti` (composito)

Gestisce partecipanti singoli e gruppi di partecipanti in modo uniforme.

### 4. Iterator Pattern
Implementato attraverso le Java Collections Framework per navigare tra le collezioni di oggetti.

## Modello dei Dati

**Posizione**: `src/main/java/com/jamfinder/model/`

### Classi Principali:

- **`Utente`**: Rappresenta un musicista
  - Nome, cognome, livello esperienza
  - Set di strumenti suonati
  - Implementa Observer
  
- **`Strumento`**: Rappresenta uno strumento musicale  
  - Nome, categoria, livello richiesto
  
- **`JamSession`**: Rappresenta una sessione musicale
  - Titolo, data, luogo, livello
  - Lista partecipanti, set strumenti richiesti
  - Implementa Observable

## Java Collections Framework

Il progetto utilizza le Collections con Generics:

- **Set&lt;Strumento&gt;**: Strumenti dell'utente (no duplicati)
- **Set&lt;String&gt;**: Categorie strumenti  
- **List&lt;ComponenteJamSession&gt;**: Partecipanti jam session
- **HashSet** e **ArrayList** per gestione efficiente dei dati

## Gestione Eccezioni

Il progetto include gestione base delle eccezioni:
- **IllegalArgumentException** per input non validi
- **try-catch** per validazione dati
- Controlli di validazione su parametri null

## Java I/O - Persistenza Dati

Il progetto implementa funzionalità di salvataggio e caricamento dati:

**Posizione**: `src/main/java/com/jamfinder/io/`

### Classe DataManager
- **Salvataggio utenti**: Esporta lista utenti in formato testo
- **Caricamento utenti**: Importa utenti da file con gestione errori
- **Salvataggio strumenti**: Persiste collezione strumenti
- **Caricamento strumenti**: Ripristina strumenti da file
- **Esporta statistiche**: Genera report con conteggi e categorie

### Caratteristiche I/O
- **File formato**: File di testo con separatori pipe (|)
- **Gestione errori**: Try-catch per IOException
- **File mancanti**: Gestione graceful di file inesistenti
- **Directory automatica**: Creazione cartella `data/` se mancante
- **Encoding sicuro**: Gestione caratteri speciali

## Compilazione ed Esecuzione

### Prerequisiti
- Java 11 o superiore

### Avvio Rapido (Windows)
```batch
esegui-app.bat
```

### Compilazione Manuale
```batch
javac -d bin -sourcepath src/main/java src/main/java/com/jamfinder/**/*.java
java -cp bin com.jamfinder.JamFinderMain
```

## Struttura del Progetto

```
JAMFINDER/
├── src/
│   ├── main/java/com/jamfinder/
│   │   ├── JamFinderMain.java          # Applicazione principale
│   │   ├── model/                      # Classi del dominio
│   │   │   ├── Utente.java
│   │   │   ├── Strumento.java
│   │   │   └── JamSession.java
│   │   ├── factory/                    # Factory Pattern
│   │   │   ├── JamFinderFactory.java
│   │   │   └── StandardJamFinderFactory.java
│   │   ├── observer/                   # Observer Pattern
│   │   │   ├── Observer.java
│   │   │   ├── Observable.java
│   │   │   └── NotificatoreUtente.java
│   │   ├── composite/                  # Composite Pattern
│   │   │   ├── ComponenteJamSession.java
│   │   │   ├── Partecipante.java
│   │   │   └── GruppoPartecipanti.java
│   │   └── io/                         # Java I/O Operations
│   │       └── DataManager.java
│   └── test/java/com/jamfinder/        # Test Suite
│       ├── RunAllTests.java            # Esecuzione tutti i test
│       ├── TestUtente.java             # Test classe Utente
│       ├── TestFactory.java            # Test Factory Pattern
│       ├── TestObserver.java           # Test Observer Pattern
│       ├── TestComposite.java          # Test Composite Pattern
│       ├── TestCollections.java        # Test Collections/Iterator
│       └── TestJavaIO.java             # Test Java I/O operations
├── bin/                               # File compilati (.class)
├── data/                              # File dati persistenti
│   ├── utenti.txt                     # Dati utenti salvati
│   ├── strumenti.txt                  # Dati strumenti salvati
│   └── statistiche.txt                # Report statistiche
├── esegui-app.bat                     # Script di avvio applicazione
├── esegui-test.bat                    # Script di avvio test suite
└── README.md                          # Questo file
```

## Test Suite

Il progetto include una test suite completa per verificare il corretto funzionamento di tutti i Design Pattern e delle classi principali.

### Struttura Test

#### Test delle Classi Model
- **`TestUtente.java`**: Test creazione utenti, gestione strumenti, validazione input
- **`TestCollections.java`**: Test Collections Framework, Generics, Iterator Pattern
- **`TestJavaIO.java`**: Test salvataggio/caricamento dati, gestione errori I/O

#### Test dei Design Patterns  
- **`TestFactory.java`**: Verifica Singleton Factory, creazione oggetti, coerenza
- **`TestObserver.java`**: Test sistema notifiche, multipli observer
- **`TestComposite.java`**: Test pattern Composite, partecipanti e gruppi

#### Test Runner
- **`RunAllTests.java`**: Esegue tutti i test in sequenza con riepilogo finale

### Esecuzione Test

#### Avvio Rapido (Windows)
```batch
esegui-test.bat
```

#### Esecuzione Manuale
```batch
# Compilazione
javac -d bin -sourcepath src/main/java src/main/java/com/jamfinder/**/*.java
javac -d bin -sourcepath src/test/java -cp bin src/test/java/com/jamfinder/*.java

# Esecuzione test (con assertions abilitate)
java -ea -cp bin com.jamfinder.RunAllTests
```

### Caratteristiche Test Suite

- **Assertions**: Uso di `assert` per validazione risultati
- **Exception Testing**: Verifica gestione eccezioni
- **Pattern Validation**: Test specifici per ogni Design Pattern
- **Type Safety**: Verifica Generics e Collections Framework
- **No External Dependencies**: Test puri Java senza framework esterni

### Output Test

La test suite produce output dettagliato per ogni test:
```
=== JAMFINDER - TEST SUITE ===

1. FACTORY PATTERN
OK Singleton Factory OK
OK Creazione oggetti OK  
OK Coerenza Factory OK

2. MODEL CLASSES
OK Creazione utente OK
OK Aggiunta strumento OK
OK Validazione input OK

3. COLLECTIONS FRAMEWORK
OK Set strumenti OK
OK Iterator Pattern OK
OK Generics OK

4. OBSERVER PATTERN
OK Notifica utente singolo OK
OK Notifiche multipli observer OK

TUTTI I TEST COMPLETATI!
```

## Esempio di Utilizzo

L'applicazione dimostra tutti i pattern implementati:

```java
// Factory Pattern - Creazione oggetti
JamFinderFactory factory = StandardJamFinderFactory.getInstance();
Utente mario = factory.creaUtente("Mario", "Rossi", "INTERMEDIO");

// Observer Pattern - Sistema notifiche  
JamSession session = factory.creaJamSession("Rock Night", "2024-01-20", "Studio A");
session.addObserver(mario); // Mario riceverà notifiche

// Composite Pattern - Gestione partecipanti
ComponenteJamSession partecipante = new Partecipante(mario);
GruppoPartecipanti gruppo = new GruppoPartecipanti("Rock Band");
gruppo.aggiungi(partecipante);

// Collections Framework - Gestione strumenti
Set<Strumento> strumenti = new HashSet<>();
strumenti.add(new Strumento("Chitarra", "Corde", "PRINCIPIANTE"));

// Iterator Pattern - Navigazione collezioni
for (Strumento s : strumenti) {
    System.out.println(s.getNome());
}
```

## Caratteristiche Tecniche

### Design Pattern
- **Factory**: Creazione centralizzata oggetti
- **Observer**: Sistema notifiche automatiche  
- **Composite**: Gestione strutture ad albero
- **Iterator**: Navigazione collezioni

### Collections Framework
- **Generics**: Type safety (`Set<Strumento>`, `List<ComponenteJamSession>`)
- **HashSet**: Collezioni senza duplicati
- **ArrayList**: Liste ordinate con accesso indicizzato

### Gestione Errori
- Validazione input con `IllegalArgumentException`
- Controlli null sui parametri
- Try-catch per operazioni critiche

## Note di Sviluppo

Il progetto è stato sviluppato seguendo i principi base della programmazione orientata agli oggetti:

- **Encapsulation**: Campi privati con getter/setter
- **Inheritance**: Gerarchia di classi per riuso codice  
- **Polymorphism**: Interfacce per comportamenti intercambiabili
- **Abstraction**: Separazione implementazione da interfaccia

Ogni Design Pattern è implementato in modo semplice ma funzionale, dimostrando l'applicazione pratica dei concetti teorici studiati.

## Justification for Technologies and Patterns

### Design Patterns Justification

#### Factory Pattern
**Perché utilizzato**: Centralizza la creazione degli oggetti del sistema, nascondendo la complessità di istanziazione e garantendo coerenza nella creazione di Utenti, Strumenti e JamSession.
**Benefici**: Riduce l'accoppiamento, facilita la manutenzione e permette modifiche future alla logica di creazione senza impattare il codice client.

#### Observer Pattern  
**Perché utilizzato**: Le jam session necessitano di un sistema di notifiche automatiche per informare i partecipanti di cambiamenti (nuovi partecipanti, modifiche orario, etc.).
**Benefici**: Implementa il principio di loose coupling, permettendo comunicazione asincrona tra oggetti senza dipendenze dirette.

#### Composite Pattern
**Perché utilizzato**: Gestisce uniformemente partecipanti singoli e gruppi di partecipanti, permettendo operazioni come "mostra informazioni" su entrambi i tipi.
**Benefici**: Semplifica il codice client che può trattare oggetti singoli e compositi con la stessa interfaccia.

#### Iterator Pattern
**Perché utilizzato**: Fornisce accesso sequenziale agli elementi delle collezioni (strumenti, partecipanti) senza esporre la struttura interna.
**Benefici**: Nasconde la complessità di navigazione e permette diversi algoritmi di traversal.

### Java Collections Framework
**Perché utilizzato**: 
- **Set<Strumento>**: Garantisce unicità degli strumenti per utente
- **List<ComponenteJamSession>**: Mantiene ordine dei partecipanti
- **Generics**: Fornisce type safety a compile-time

## UML Diagrams

### Class Diagram
```
┌──────────────────┐    ┌──────────────────┐    ┌──────────────────┐
│     Utente       │    │    Strumento     │    │   JamSession     │
├──────────────────┤    ├──────────────────┤    ├──────────────────┤
│ - nome: String   │    │ - nome: String   │    │ - titolo: String │
│ - cognome: String│    │ - categoria: Str │    │ - data: String   │
│ - livello: String│    │ - livello: String│    │ - luogo: String  │
│ - strumenti: Set │    └──────────────────┘    │ - livello: String│
├──────────────────┤                            │ - partecipanti:  │
│ + getNome()      │                            │   List<Comp..>   │
│ + aggiStrumento()│                            │ - strumentiReq:  │
│ + update()       │                            │   Set<String>    │
└──────────────────┘                            ├──────────────────┤
        │                                       │ + addObserver()  │
        │                                       │ + notifyObs()    │
        ▼                                       │ + aggiungiPart() │
┌─────────────────┐                             └──────────────────┘
│    Observer     │                                    │
├─────────────────┤                                    │ 
│ + update()      │                                    ▼
└─────────────────┘                           ┌─────────────────┐
                                              │   Observable    │
┌─────────────────┐    ┌─────────────────┐    ├─────────────────┤
│ComponenteJamSess│◄───┤  Partecipante   │    │ + addObserver() │
├─────────────────┤    ├─────────────────┤    │ + removeObs()   │
│ + mostraInfo()  │    │ - utente: Utente│    │ + notifyObs()   │
│ + aggiungi()    │    ├─────────────────┤    └─────────────────┘
└─────────────────┘    │ + mostraInfo()  │
        ▲              └─────────────────┘
        │
        │
┌──────────────────┐
│GruppoPartecipanti│
├──────────────────┤
│ - nome: String   │
│ - membri: List   │
├──────────────────┤
│ + aggiungi()     │
│ + mostraInfo()   │
└──────────────────┘

┌───────────────────┐    ┌───────────────────┐
│JamFinderFactory   │◄───┤StandardJamFinder  │
├───────────────────┤    │    Factory        │
│ + creaUtente()    │    ├───────────────────┤
│ + creaStrumento() │    │ - instance        │
│ + creaJamSession()│    ├───────────────────┤
└───────────────────┘    │ + getInstance()   │
                         │ + creaUtente()    │
                         │ + creaStrumento() │
                         │ + creaJamSession()│
                         └───────────────────┘
```

### Architectural Diagram
```
┌─────────────────────────────────────────────────────────────┐
│                    JamFinderMain                            │
│                 (Application Layer)                         │
└─────────────────────┬───────────────────────────────────────┘
                      │
              ┌───────▼─────────┐
              │  Factory Layer  │
              │ ┌─────────────┐ │
              │ │StandardJam  │ │
              │ │FinderFactory│ │
              │ └─────────────┘ │
              └───────┬─────────┘
                      │
    ┌─────────────────▼───────────────────────────┐
    │                Model Layer                  │
    │  ┌─────────┐  ┌─────────┐  ┌─────────────┐  │
    │  │ Utente  │  │Strumento│  │ JamSession  │  │
    │  └─────────┘  └─────────┘  └─────────────┘  │
    └─────────────────┬───────────────────────────┘
                      │
    ┌─────────────────▼────────────────────────────────┐
    │              Pattern Layer                       │
    │                                                  │
    │ ┌─────────────┐ ┌─────────────┐ ┌──────────────┐ │
    │ │  Observer   │ │  Composite  │ │ Iterator     │ │
    │ │   Pattern   │ │   Pattern   │ │ (Collections)│ │
    │ └─────────────┘ └─────────────┘ └──────────────┘ │
    └──────────────────────────────────────────────────┘
```

## Known Limitations

### Current Implementation Limitations

1. **Persistenza Dati**
   - I dati non vengono salvati su file o database
   - Tutte le informazioni vengono perse alla chiusura dell'applicazione
   - Non c'è meccanismo di backup o recovery

2. **Interfaccia Utente**
   - Applicazione solo console-based
   - Nessuna GUI per migliorare l'esperienza utente
   - Input limitato a dati hardcoded nell'applicazione

3. **Validazione Dati**
   - Validazione base limitata a controlli null
   - Nessuna validazione di formato per date
   - Controlli di business logic limitati

4. **Concorrenza**
   - Nessuna gestione di accesso concorrente
   - Non thread-safe per utilizzo multi-utente
   - Potenziali race conditions con Observer Pattern

5. **Scalabilità**
   - Strutture dati in memoria limitano il numero di oggetti gestibili
   - Nessuna ottimizzazione per grandi volumi di dati
   - Algoritmi di ricerca lineari

6. **Gestione Errori**
   - Eccezioni limitate a IllegalArgumentException
   - Mancanza di logging strutturato
   - Recovery limitato da errori runtime



## Future Works

### Miglioramenti Pianificati

#### 1. Persistenza e Database
- **Integrazione Database**: Implementare persistenza
- **Serializzazione**: Aggiungere salvataggio/caricamento da file
- **Backup Automatico**: Sistema di backup periodico dei dati

#### 2. Interfaccia Utente
- **GUI Swing/JavaFX**: Interfaccia grafica user-friendly
- **Web Interface**: Estensione Versione web
- **Mobile App**: Estensione per dispositivi mobili

#### 3. Funzionalità Avanzate
- **Sistema di Matching**: Algoritmo per matching automatico musicisti
- **Calendario Integrato**: Gestione appuntamenti e disponibilità
- **Chat/Messaggistica**: Comunicazione tra partecipanti
- **Rating System**: Valutazione musicisti e sessioni

#### 4. Pattern Aggiuntivi
- **Strategy Pattern**: Algoritmi di ricerca intercambiabili
- **Command Pattern**: Sistema di undo/redo per operazioni
- **Decorator Pattern**: Estensione funzionalità dinamiche
- **State Pattern**: Gestione stati delle jam session

#### 5. Tecnologie Moderne
- **Spring Framework**: Dependency injection e configurazione
- **REST API**: Servizi web per integrazione esterna
- **Microservizi**: Architettura distribuita scalabile
- **Cloud Integration**: Deploy su AWS

#### 6. Testing e Qualità
- **Test Coverage**: Espansione test coverage oltre i pattern base
- **Performance Testing**: Benchmark e ottimizzazioni
- **Integration Testing**: Test di integrazione tra componenti
- **Automated Testing**: Framework di test automatizzati

#### 7. Security e Compliance
- **Autenticazione**: Sistema login/password
- **Autorizzazione**: Controllo accessi basato su ruoli
- **Crittografia**: Protezione dati sensibili
- **GDPR Compliance**: Gestione privacy dati

### Roadmap Implementazione

**Fase 1 (Breve termine - 1-2 mesi)**
- Persistenza file base
- Validazione dati migliorata
- GUI Swing semplice

**Fase 2 (Medio termine - 3-6 mesi)**
- Database integration
- Pattern aggiuntivi (Strategy, Command)
- Sistema di matching base

**Fase 3 (Lungo termine - 6-12 mesi)**
- Architettura web completa
- Microservizi
- Mobile application
- Cloud deployment
