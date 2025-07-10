package memento;

import model.Libro;
import java.util.ArrayList;
import java.util.List;

public class Memento {

    private List<Libro> statoSalvato;

    public Memento(List<Libro> statoDaSalvare) {
        // Copia profonda dei libri per evitare modifiche esterne
        statoSalvato = new ArrayList<>();
        for (Libro libro : statoDaSalvare) {
            statoSalvato.add(new Libro(
                    libro.getTitolo(),
                    libro.getAutore(),
                    libro.getIsbn(),
                    libro.getGenere(),
                    libro.getValutazione(),
                    libro.getStatoLettura()
            ));
        }
    }

    public List<Libro> getStatoSalvato() {
        return statoSalvato;
    }
}