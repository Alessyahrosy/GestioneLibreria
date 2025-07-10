package strategy;

import model.Libro;

public class FiltroPerGenere implements FiltroStrategy {
    private String genere;

    public FiltroPerGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public boolean filtra(Libro libro) {
        return libro.getGenere().equalsIgnoreCase(genere);
    }
}