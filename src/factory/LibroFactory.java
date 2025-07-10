package factory;

import model.Libro;

public interface LibroFactory {
    Libro creaLibro(String titolo, String autore, String isbn, String genere, int valutazione, String statoLettura);
}