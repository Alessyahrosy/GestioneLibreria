package strategy;

import model.Libro;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class OrdinaPerIsbn implements CriterioOrdinamento {
    @Override
    public void ordina(List<Libro> libri) {
        Collections.sort(libri, Comparator.comparing(Libro::getIsbn));
    }
}