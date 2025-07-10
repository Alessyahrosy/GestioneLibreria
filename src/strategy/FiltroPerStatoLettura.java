package strategy;

import model.Libro;

public class FiltroPerStatoLettura implements FiltroStrategy {
    private String stato;

    public FiltroPerStatoLettura(String stato) {
        this.stato = stato;
    }

    @Override
    public boolean filtra(Libro libro) {
        return libro.getStatoLettura().equalsIgnoreCase(stato);
    }
}