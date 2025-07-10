package strategy;

import model.Libro;

public interface FiltroStrategy {
    boolean filtra(Libro libro);
}