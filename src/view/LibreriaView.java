package view;

import model.Libreria;
import model.Libro;
import observer.Observer;
import decorator.LibroComponent;
import decorator.LibroPreferitoDecorator;

public class LibreriaView implements Observer {

    private Libreria libreria;

    public LibreriaView(Libreria libreria) {
        this.libreria = libreria;
        this.libreria.aggiungiObserver(this);
    }

    @Override
    public void aggiorna() {
        System.out.println("Vista aggiornata: elenco dei libri:");
        for (Libro l : libreria.getLibri()) {
            LibroComponent componente = l; // il libro di base implementa LibroComponent
            if (l.getValutazione() == 5) { // esempio: se valutazione massima, lo decoro come preferito
                componente = new LibroPreferitoDecorator(componente);
            }
            System.out.println("- " + componente.getDescrizione());
        }
    }
}