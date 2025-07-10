package model;

import observer.Observable;
import observer.Observer;
import strategy.CriterioOrdinamento;
import strategy.FiltroStrategy;

import persistence.GestoreFileJSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Classe Singleton che gestisce la collezione di libri.
 * Implementa Observable per notificare le viste in caso di modifiche.
 * Supporta ordinamento e filtro tramite Strategy.
 * Gestisce undo/redo con pattern Memento semplificato.
 */

public class Libreria implements Observable {

    private static Libreria instance = null;
    private final List<Libro> libri = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();

    private CriterioOrdinamento criterioOrdinamento;

    // Stack per undo/redo - Memento Pattern
    private final Stack<LibreriaMemento> undoStack = new Stack<>();
    private final Stack<LibreriaMemento> redoStack = new Stack<>();

    private Libreria() {}

    /**
     * Restituisce l'unica istanza di Libreria (Singleton).
     * Metodo sincronizzato per sicurezza in contesti multi-thread.
     */
    public static synchronized Libreria getInstance() {
        if (instance == null) {
            instance = new Libreria();
        }
        return instance;
    }

    // Classe interna Memento per salvare snapshot dello stato
    private static class LibreriaMemento {
        private final List<Libro> stato;

        private LibreriaMemento(List<Libro> stato) {
            this.stato = new ArrayList<>(stato);
        }

        private List<Libro> getStato() {
            return new ArrayList<>(stato);
        }
    }

    /**
     * Salva lo stato attuale della libreria per supportare undo.
     * Pulisce lo stack redo perché nuove azioni invalidano le "rifai".
     */
    private void salvaStato() {
        undoStack.push(new LibreriaMemento(libri));
        redoStack.clear();
    }

    /**
     * Annulla l'ultima operazione, se possibile.
     */
    public void annulla() {
        if (!undoStack.isEmpty()) {
            LibreriaMemento memento = undoStack.pop();
            redoStack.push(new LibreriaMemento(libri));
            libri.clear();
            libri.addAll(memento.getStato());
            notificaObservers();
        } else {
            System.out.println("Niente da annullare");
        }
    }

    /**
     * Ripristina l'ultimo stato annullato (redo).
     */
    public void rifai() {
        if (!redoStack.isEmpty()) {
            LibreriaMemento memento = redoStack.pop();
            undoStack.push(new LibreriaMemento(libri));
            libri.clear();
            libri.addAll(memento.getStato());
            notificaObservers();
        } else {
            System.out.println("Niente da rifare");
        }
    }

    /**
     * Aggiunge un libro alla libreria salvando lo stato per undo.
     */
    public void aggiungiLibro(Libro libro) {
        salvaStato();
        libri.add(libro);
        notificaObservers();
    }

    /**
     * Rimuove un libro dalla libreria salvando lo stato per undo.
     */
    public void rimuoviLibro(Libro libro) {
        salvaStato();
        libri.remove(libro);
        notificaObservers();
    }

    /**
     * Restituisce una lista immutabile di libri.
     */
    public List<Libro> getLibri() {
        return Collections.unmodifiableList(new ArrayList<>(libri));
    }
 //metodo pubblico per sostituire o aggiornare i libri
    public void setLibri(List<Libro> nuoviLibri) {
        libri.clear();           // svuota la lista finale
      libri.addAll(nuoviLibri); // ci mette dentro i nuovi libri
      notificaObservers();
    }

    // Strategy per ordinamento
    public void setCriterioOrdinamento(CriterioOrdinamento criterio) {
        this.criterioOrdinamento = criterio;
    }

    /**
     * Ordina la lista dei libri in base al criterio impostato.
     */
    public void ordinaLibri() {
        if (criterioOrdinamento != null) {
            criterioOrdinamento.ordina(libri);
            notificaObservers();
        }
    }

    // Strategy per filtro
    public List<Libro> filtraLibri(FiltroStrategy filtro) {
        return libri.stream()
                .filter(filtro::filtra)
                .collect(Collectors.toList());
    }

    /**
     * Modifica i dati di un libro identificato da ISBN.
     * @return true se il libro è stato trovato e modificato, false altrimenti.
     */
    public boolean modificaLibro(String isbn, String titolo, String autore, String genere, int valutazione, String stato) {
        for (Libro libro : libri) {
            if (libro.getIsbn().equals(isbn)) {
                libro.setTitolo(titolo);
                libro.setAutore(autore);
                libro.setGenere(genere);
                libro.setValutazione(valutazione);
                libro.setStatoLettura(stato);
                return true; // Modifica effettuata
            }
        }
        return false; // Libro non trovato
    }

    // Observable interface
    @Override
    public void aggiungiObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void rimuoviObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notificaObservers() {
        for (Observer o : observers) {
            o.aggiorna();
        }
    }

    /**
     * Salva la libreria su file JSON tramite il template method.
     * @param percorso percorso file
     */
    public void salvaSuFile(String percorso) {
        GestoreFileJSON gestore = new GestoreFileJSON(this);
        gestore.salva(percorso);
    }

    /**
     * Carica la libreria da file JSON tramite il template method.
     * @param percorso percorso file
     */
    public void caricaDaFile(String percorso) {
        GestoreFileJSON gestore = new GestoreFileJSON(this);
        gestore.carica(percorso);
    }

    // Metodo per test - svuota la lista libri EXTRA
     public void svuotaLibreria() {
        libri.clear();
        undoStack.clear();
        redoStack.clear();
        notificaObservers();
    }
}
