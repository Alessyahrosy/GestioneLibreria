package test;

import model.Libreria;
import model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibreriaTest {

    private Libreria libreria;

    @BeforeEach
    public void setup() {
        libreria = Libreria.getInstance();
        libreria.svuotaLibreria(); // resetto la libreria prima di ogni test
    }

    @Test
    public void testAggiungiLibro() {
        Libro libro = new Libro("1984", "Orwell", "12345", "Distopico", 5, "letto");
        libreria.aggiungiLibro(libro);

        assertEquals(1, libreria.getLibri().size(), "La libreria dovrebbe avere 1 libro");
        assertTrue(libreria.getLibri().contains(libro), "La libreria dovrebbe contenere il libro aggiunto");
    }

    @Test
    public void testRimuoviLibro() {
        Libro libro = new Libro("1984", "Orwell", "12345", "Distopico", 5, "letto");
        libreria.aggiungiLibro(libro);
        libreria.rimuoviLibro(libro);

        assertEquals(0, libreria.getLibri().size(), "La libreria dovrebbe essere vuota dopo la rimozione");
        assertFalse(libreria.getLibri().contains(libro), "La libreria non dovrebbe contenere il libro rimosso");
    }

    @Test
    public void testModificaLibro() {
        Libro libro = new Libro("1984", "Orwell", "12345", "Distopico", 5, "letto");
        libreria.aggiungiLibro(libro);

        boolean modificato = libreria.modificaLibro("12345", "Animal Farm", "Orwell", "Satira", 4, "in lettura");

        assertTrue(modificato, "Il libro dovrebbe essere stato modificato");
        Libro modificatoLibro = libreria.getLibri().get(0);
        assertEquals("Animal Farm", modificatoLibro.getTitolo(), "Il titolo dovrebbe essere stato aggiornato");
        assertEquals("Satira", modificatoLibro.getGenere(), "Il genere dovrebbe essere stato aggiornato");
        assertEquals(4, modificatoLibro.getValutazione(), "La valutazione dovrebbe essere stata aggiornata");
        assertEquals("in lettura", modificatoLibro.getStatoLettura(), "Lo stato lettura dovrebbe essere stato aggiornato");
    }

    @Test
    public void testUndoERedo() {
        Libro libro1 = new Libro("1984", "Orwell", "12345", "Distopico", 5, "letto");
        Libro libro2 = new Libro("Il piccolo principe", "Saint-Exupéry", "54321", "Fiaba", 4, "letto");

        libreria.aggiungiLibro(libro1);
        libreria.aggiungiLibro(libro2);

        assertEquals(2, libreria.getLibri().size(), "Dovrebbero esserci 2 libri");

        libreria.annulla(); // Annulla aggiunta libro2
        assertEquals(1, libreria.getLibri().size(), "Dopo undo dovrebbe esserci 1 libro");
        assertTrue(libreria.getLibri().contains(libro1), "Dovrebbe rimanere solo il primo libro");

        libreria.rifai(); // Ripristina libro2
        assertEquals(2, libreria.getLibri().size(), "Dopo redo dovrebbero esserci di nuovo 2 libri");
        assertTrue(libreria.getLibri().contains(libro2), "Il secondo libro dovrebbe essere stato ripristinato");
    }

    @Test
    public void testFiltraPerGenere() {
        Libro libro1 = new Libro("1984", "Orwell", "123", "Distopico", 5, "letto");
        Libro libro2 = new Libro("It", "Stephen King", "456", "Horror", 4, "da leggere");
        Libro libro3 = new Libro("Brave New World", "Huxley", "789", "Distopico", 5, "letto");

        libreria.aggiungiLibro(libro1);
        libreria.aggiungiLibro(libro2);
        libreria.aggiungiLibro(libro3);

        var filtrati = libreria.filtraLibri(new strategy.FiltroPerGenere("Distopico"));
        assertEquals(2, filtrati.size(), "Dovrebbero essere filtrati 2 libri di genere Distopico");
        assertTrue(filtrati.contains(libro1));
        assertTrue(filtrati.contains(libro3));
    }

    @Test
    public void testFiltraPerStatoLettura() {
        Libro libro1 = new Libro("Il signore degli anelli", "Tolkien", "111", "Fantasy", 5, "in lettura");
        Libro libro2 = new Libro("Dune", "Herbert", "222", "Fantascienza", 4, "letto");
        Libro libro3 = new Libro("La strada", "McCarthy", "333", "Post-apocalittico", 5, "in lettura");

        libreria.aggiungiLibro(libro1);
        libreria.aggiungiLibro(libro2);
        libreria.aggiungiLibro(libro3);

        var filtrati = libreria.filtraLibri(new strategy.FiltroPerStatoLettura("in lettura"));
        assertEquals(2, filtrati.size(), "Dovrebbero essere filtrati 2 libri con stato 'in lettura'");
        assertTrue(filtrati.contains(libro1));
        assertTrue(filtrati.contains(libro3));
    }

    @Test
    public void testOrdinaPerAutore() {
        Libro libro1 = new Libro("Libro C", "Zorro", "001", "Fantasy", 3, "letto");
        Libro libro2 = new Libro("Libro A", "Aaron", "002", "Fantasy", 4, "letto");
        Libro libro3 = new Libro("Libro B", "Marco", "003", "Fantasy", 5, "letto");

        libreria.aggiungiLibro(libro1);
        libreria.aggiungiLibro(libro2);
        libreria.aggiungiLibro(libro3);

        libreria.setCriterioOrdinamento(new strategy.OrdinaPerAutore());
        libreria.ordinaLibri();

        assertEquals("Aaron", libreria.getLibri().get(0).getAutore());
        assertEquals("Marco", libreria.getLibri().get(1).getAutore());
        assertEquals("Zorro", libreria.getLibri().get(2).getAutore());
    }

    @Test
    public void testOrdinaPerTitolo() {
        Libro libro1 = new Libro("Zorro", "Autore1", "001", "Azione", 3, "letto");
        Libro libro2 = new Libro("Alpha", "Autore2", "002", "Fantasy", 4, "letto");
        Libro libro3 = new Libro("Beta", "Autore3", "003", "Giallo", 5, "letto");

        libreria.aggiungiLibro(libro1);
        libreria.aggiungiLibro(libro2);
        libreria.aggiungiLibro(libro3);

        libreria.setCriterioOrdinamento(new strategy.OrdinaPerTitolo());
        libreria.ordinaLibri();

        assertEquals("Alpha", libreria.getLibri().get(0).getTitolo());
        assertEquals("Beta", libreria.getLibri().get(1).getTitolo());
        assertEquals("Zorro", libreria.getLibri().get(2).getTitolo());
    }

    @Test
    public void testOrdinaPerIsbn() {
        Libro libro1 = new Libro("Libro Uno", "AutoreA", "003", "Fantasy", 4, "letto");
        Libro libro2 = new Libro("Libro Due", "AutoreB", "001", "Giallo", 3, "letto");
        Libro libro3 = new Libro("Libro Tre", "AutoreC", "002", "Romanzo", 5, "letto");

        libreria.aggiungiLibro(libro1);
        libreria.aggiungiLibro(libro2);
        libreria.aggiungiLibro(libro3);

        libreria.setCriterioOrdinamento(new strategy.OrdinaPerIsbn());
        libreria.ordinaLibri();

        assertEquals("001", libreria.getLibri().get(0).getIsbn());
        assertEquals("002", libreria.getLibri().get(1).getIsbn());
        assertEquals("003", libreria.getLibri().get(2).getIsbn());
    }

    // i libri con voto più alto vengono prima
    @Test
    public void testOrdinaPerValutazione() {
        Libro libro1 = new Libro("Libro A", "AutoreA", "100", "Fantasy", 3, "letto");
        Libro libro2 = new Libro("Libro B", "AutoreB", "101", "Giallo", 5, "letto");
        Libro libro3 = new Libro("Libro C", "AutoreC", "102", "Romanzo", 1, "letto");

        libreria.aggiungiLibro(libro1);
        libreria.aggiungiLibro(libro2);
        libreria.aggiungiLibro(libro3);

        libreria.setCriterioOrdinamento(new strategy.OrdinaPerValutazione());
        libreria.ordinaLibri();

        assertEquals(5, libreria.getLibri().get(0).getValutazione());
        assertEquals(3, libreria.getLibri().get(1).getValutazione());
        assertEquals(1, libreria.getLibri().get(2).getValutazione());
    }
}