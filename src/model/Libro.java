package model;

import decorator.LibroComponent; // importa l'interfaccia


/**
 * Rappresenta un libro con tutte le sue caratteristiche essenziali:
 * titolo, autore, ISBN, genere, valutazione e stato di lettura.
 */
public class Libro implements LibroComponent { // implementa l'interfaccia

    // Attributi privati che rappresentano le proprietà principali di un libro
    private String titolo;
    private String autore;
    private String isbn;
    private String genere;
    private int valutazione;        // da 1 a 5
    private String statoLettura;    // es: "letto", "da leggere", "in lettura"

    // Costruttore per inizializzare un libro con tutte le informazioni richieste
    public Libro(String titolo, String autore, String isbn, String genere, int valutazione, String statoLettura) {
        this.titolo = titolo;
        this.autore = autore;
        this.isbn = isbn;
        this.genere = genere;
        this.valutazione = valutazione;
        this.statoLettura = statoLettura;
    }

    // Metodi getter per accedere ai dati privati
    public String getTitolo() { return titolo; }
    public String getAutore() { return autore; }
    public String getIsbn() { return isbn; }
    public String getGenere() { return genere; }
    public int getValutazione() { return valutazione; }
    public String getStatoLettura() { return statoLettura; }

    // Metodi setter per modificare gli attributi del libro
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    //limitiamo la valutazione nel range [1,5]
    public void setValutazione(int valutazione) {
        if(valutazione < 1) {
            this.valutazione = 1;
        } else if(valutazione > 5) {
            this.valutazione = 5;
        } else {
            this.valutazione = valutazione;
        }
    }

    public void setStatoLettura(String statoLettura) {
        this.statoLettura = statoLettura;
    }

    // Implementazione del metodo dell’interfaccia LibroComponent richiesto dal Decorator
    @Override
    public String getDescrizione() {
        return "Titolo: " + titolo + ", Autore: " + autore + ", Genere: " + genere +
                ", Valutazione: " + valutazione + ", Stato lettura: " + statoLettura;
    }

    // equals confronta i libri tramite ISBN, garantendo che due libri con lo stesso ISBN siano considerati uguali
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return isbn != null && isbn.equals(libro.isbn);
    }

    // hashCode coerente con equals, basato su ISBN
    @Override
    public int hashCode() {
        return isbn != null ? isbn.hashCode() : 0;
    }

    // toString fornisce una rappresentazione leggibile e dettagliata del libro
    @Override
    public String toString() {
        return String.format("%s di %s (ISBN: %s) - Genere: %s - Valutazione: %d - Stato: %s",
                titolo, autore, isbn, genere, valutazione, statoLettura);
    }

}