package command;

import model.Libro;
import model.Libreria;

public class AggiungiLibroCommand implements Comando {
    private Libreria libreria;
    private Libro libro;

    public AggiungiLibroCommand(Libreria libreria, Libro libro) {
        this.libreria = libreria;
        this.libro = libro;
    }

    @Override
    public void esegui() {
        libreria.aggiungiLibro(libro);
    }
}