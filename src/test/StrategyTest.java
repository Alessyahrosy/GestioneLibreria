package test;

import model.Libreria;
import model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyTest {

    private Libreria libreria;

    @BeforeEach
    public void setup() {
        libreria = Libreria.getInstance();
        libreria.svuotaLibreria();

        libreria.aggiungiLibro(new Libro("C", "Zeta", "3", "Genere1", 3, "letto"));
        libreria.aggiungiLibro(new Libro("A", "Beta", "1", "Genere2", 5, "in corso"));
        libreria.aggiungiLibro(new Libro("B", "Alpha", "2", "Genere1", 1, "letto"));
    }

    @Test
    public void testOrdinaPerTitolo() {
        libreria.setCriterioOrdinamento(new OrdinaPerTitolo());
        libreria.ordinaLibri();
        List<Libro> libri = libreria.getLibri();

        assertEquals("A", libri.get(0).getTitolo());
        assertEquals("B", libri.get(1).getTitolo());
        assertEquals("C", libri.get(2).getTitolo());
    }

    @Test
    public void testOrdinaPerAutore() {
        libreria.setCriterioOrdinamento(new OrdinaPerAutore());
        libreria.ordinaLibri();
        List<Libro> libri = libreria.getLibri();

        assertEquals("Alpha", libri.get(0).getAutore());
        assertEquals("Beta", libri.get(1).getAutore());
        assertEquals("Zeta", libri.get(2).getAutore());
    }

    @Test
    public void testFiltroPerGenere() {
        List<Libro> filtrati = libreria.filtraLibri(new FiltroPerGenere("Genere1"));
        assertEquals(2, filtrati.size());
        assertTrue(filtrati.stream().allMatch(l -> l.getGenere().equals("Genere1")));
    }

    @Test
    public void testFiltroPerStatoLettura() {
        List<Libro> filtrati = libreria.filtraLibri(new FiltroPerStatoLettura("letto"));
        assertEquals(2, filtrati.size());
        assertTrue(filtrati.stream().allMatch(l -> l.getStatoLettura().equals("letto")));
    }
}