package command;

import model.Libreria;

public class ModificaLibroCommand implements Comando {

    private Libreria libreria;
    private String isbn;
    private String titolo;
    private String autore;
    private String genere;
    private int valutazione;
    private String statoLettura;

    public ModificaLibroCommand(Libreria libreria, String isbn, String titolo, String autore, String genere, int valutazione, String statoLettura) {
        this.libreria = libreria;
        this.isbn = isbn;
        this.titolo = titolo;
        this.autore = autore;
        this.genere = genere;
        this.valutazione = valutazione;
        this.statoLettura = statoLettura;
    }

    @Override
    public void esegui() {
        libreria.modificaLibro(isbn, titolo, autore, genere, valutazione, statoLettura);
    }
}