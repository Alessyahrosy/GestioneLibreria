package strategy;

import model.Libro;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class OrdinaPerValutazione implements CriterioOrdinamento {

    @Override
    public void ordina(List<Libro> libri) {
        Collections.sort(libri, Comparator.comparingInt(Libro::getValutazione).reversed());
    }
}