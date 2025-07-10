package test;

import factory.LibroFactory;
import factory.LibroFactoryImpl;
import model.Libro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactoryTest {

    @Test
    public void testCreaLibro() {
        LibroFactory factory = new LibroFactoryImpl();
        Libro libro = factory.creaLibro("Dune", "Herbert", "9781234567890", "Fantascienza", 5, "letto");

        assertEquals("Dune", libro.getTitolo());
        assertEquals("Herbert", libro.getAutore());
        assertEquals("9781234567890", libro.getIsbn());
        assertEquals("Fantascienza", libro.getGenere());
        assertEquals(5, libro.getValutazione());
        assertEquals("letto", libro.getStatoLettura());
    }
}
