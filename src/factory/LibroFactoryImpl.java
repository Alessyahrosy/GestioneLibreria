package factory;

import model.Libro;

public class LibroFactoryImpl implements LibroFactory {

    @Override
    public Libro creaLibro(String titolo, String autore, String isbn, String genere, int valutazione, String statoLettura) {
        // Qui puoi aggiungere controlli o logica di creazione complessa
        return new Libro(titolo, autore, isbn, genere, valutazione, statoLettura);
    }
}