package strategy;

import model.Libro;
import java.util.List;

public interface CriterioOrdinamento {
    void ordina(List<Libro> libri);
}