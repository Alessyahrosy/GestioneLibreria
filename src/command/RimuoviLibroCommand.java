package command;

import model.Libro;
import model.Libreria;

public class RimuoviLibroCommand implements Comando {

    private Libreria libreria;
    private Libro libro;

    public RimuoviLibroCommand(Libreria libreria, Libro libro) {
        this.libreria = libreria;
        this.libro = libro;
    }

    @Override
    public void esegui() {
        libreria.rimuoviLibro(libro);
    }
}