# Gestore di Libreria Personale

Traccia: https://docs.google.com/document/d/1s6NYRgh_kZRcw7me8sZH1V8Yo75dlVyaIMZgaAIRp1w/edit?tab=t.0#heading=h.crkmgrqoootx

**Overview**

Questo progetto è stato sviluppato in Java come parte del corso di Ingegneria del Software presso l'Università della Calabria. L’applicazione consente di gestire una collezione personale di libri, permettendo all’utente di aggiungere, modificare, rimuovere, filtrare e ordinare libri in modo flessibile, con supporto alla persistenza dei dati e alle operazioni di undo/redo.
Il progetto adotta diversi design pattern per garantire modularità, riusabilità e manutenibilità del codice.


 **Features**
- Gestione dei libri: inserimento, modifica e rimozione con validazione (es. ISBN univoco).
- Ordinamento e filtro: ordinamento dinamico per titolo, autore, ISBN; filtro per genere e stato lettura.
- Persistenza: salvataggio e caricamento dei dati su file JSON.
- Undo/Redo: possibilità di annullare e ripristinare operazioni tramite Memento.
- Notifiche automatiche: aggiornamento automatico delle viste con pattern Observer.
- Interfaccia testuale: semplice CLI per interagire con tutte le funzionalità.
- Design modulare: organizzazione in pacchetti tematici (model, view, command...).


**Design Patterns Utilizzati**
- **Singleton**: per la gestione centralizzata della libreria.
- **Strategy**: per ordinamento e filtraggio configurabili.
- **Observer**: per notificare le modifiche alla vista.
- **Template Method**: per la persistenza astratta (salva/carica).
- **Factory**: per la creazione centralizzata di oggetti Libro.
- **Command**: per eseguire operazioni utente in modo riutilizzabile.
- **Memento**: per lo storico delle operazioni (undo/redo).
- **Decorator** (opzionale): per estensioni della visualizzazione o funzionalità future.


**Technical Details**
Linguaggio: Java 21 (LTS)
Struttura dati: lista di oggetti Libro gestita tramite ArrayList
Persistenza: lettura/scrittura su file .json
Testing: classi di test per Observer, Memento, persistenza
IDE consigliato: IntelliJ IDEA (o compatibili)

**Requisiti Funzionali e Non Funzionali**
**Funzionali**
Tutte le operazioni richieste dalla traccia sono implementate: CRUD su libri, filtro, ordinamento, salvataggio/caricamento, undo/redo.
Implementazione di un'interfaccia utente testuale autonoma.

**Non Funzionali**
Usabilità: CLI chiara e semplice, senza dipendenze da interfacce grafiche.
Robustezza: gestione errori, duplicati e input non validi.
Modularità: architettura a pacchetti, uso dei pattern di progettazione.
Testabilità: struttura del codice adatta a test unitari e di integrazione.


 **Testing** 
Il progetto include test su:
Undo/Redo tramite Memento
Notifiche tramite Observer
Persistenza con lettura e scrittura su JSON
Comandi eseguibili (Command Pattern)

Esempio di test:
@Test
void testUndoRedoModificaLibro() {
    // crea libro, modifica, annulla, rifai, assert
}


 **Esecuzione**
1. Avvio dell’applicazione
2. Compila il progetto (Java 21).

**Esegui main.MainMenu.**
cd src
javac main/MainMenu.java
java main.MainMenu


**File JSON**
Puoi caricare la tua libreria personale da un file JSON formattato così:
[
  {
    "titolo": "1984",
    "autore": "George Orwell",
    "isbn": "9780451524935",
    "genere": "Distopia",
    "valutazione": 4,
    "statoLettura": "Letto"
  }
]

<img width="605" height="732" alt="Image" src="https://github.com/user-attachments/assets/a4091f7d-7f71-46a5-b671-a70248cbc5a3" />

<img width="774" height="722" alt="Image" src="https://github.com/user-attachments/assets/51d5cbc9-00ec-402b-a596-473386d271ce" />

<img width="539" height="525" alt="Image" src="https://github.com/user-attachments/assets/ee2c9746-3681-4b21-bf12-94e2349bde0b" />

<img width="884" height="274" alt="Image" src="https://github.com/user-attachments/assets/de49c967-a0a0-4221-83d4-cfedde68c1ef" />

<img width="886" height="193" alt="Image" src="https://github.com/user-attachments/assets/3bd57832-225d-4c09-a157-5c551ba9feaf" />


**Usage Notes**

Per aggiungere libri, basta seguire le istruzioni della CLI.
Tutti i dati sono persistenti, quindi potrai caricare le tue librerie da file.
I comandi supportano anche controllo di errori, ISBN duplicati, e gestione dello stato dell’applicazione.
