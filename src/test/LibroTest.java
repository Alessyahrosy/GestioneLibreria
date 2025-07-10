import model.Libro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {

    @Test
    public void testCostruttoreEGetters() {
        Libro libro = new Libro("1984", "Orwell", "123", "Distopico", 5, "letto");
        assertEquals("1984", libro.getTitolo());
        assertEquals("Orwell", libro.getAutore());
        assertEquals("123", libro.getIsbn());
        assertEquals("Distopico", libro.getGenere());
        assertEquals(5, libro.getValutazione());
        assertEquals("letto", libro.getStatoLettura());
    }

    @Test
    public void testSetValutazioneLimiti() {
        Libro libro = new Libro("Titolo", "Autore", "456", "Genere", 3, "in lettura");
        libro.setValutazione(0);
        assertEquals(1, libro.getValutazione(), "Valutazione min deve essere 1");
        libro.setValutazione(10);
        assertEquals(5, libro.getValutazione(), "Valutazione max deve essere 5");
    }

    @Test
    public void testEqualsHashCode() {
        Libro libro1 = new Libro("Titolo1", "Autore1", "ISBN1", "Genere", 3, "da leggere");
        Libro libro2 = new Libro("Titolo2", "Autore2", "ISBN1", "Genere", 4, "letto");
        Libro libro3 = new Libro("Titolo3", "Autore3", "ISBN3", "Genere", 2, "in lettura");

        assertEquals(libro1, libro2, "Libri con stesso ISBN devono essere uguali");
        assertNotEquals(libro1, libro3, "Libri con ISBN diversi non devono essere uguali");
        assertEquals(libro1.hashCode(), libro2.hashCode(), "Hashcode per ISBN uguali deve essere uguale");
    }

    @Test
    public void testGetDescrizione() {
        Libro libro = new Libro("Il Nome della Rosa", "Eco", "999", "Storico", 4, "letto");
        String descrizione = libro.getDescrizione();
        assertTrue(descrizione.contains("Il Nome della Rosa"));
        assertTrue(descrizione.contains("Eco"));
        assertTrue(descrizione.contains("Storico"));
        assertTrue(descrizione.contains("4"));
        assertTrue(descrizione.contains("letto"));
    }
}
