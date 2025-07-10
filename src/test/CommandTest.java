package test;

import model.Libreria;
import model.Libro;
import command.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    private Libreria libreria;

    @BeforeEach
    public void setup() {
        libreria = Libreria.getInstance();
        libreria.svuotaLibreria();
    }

    @Test
    public void testAggiungiLibroCommand() {
        Libro libro = new Libro("Test", "Autore", "0001", "Genere", 3, "letto");
        Comando comando = new AggiungiLibroCommand(libreria, libro);
        comando.esegui();

        assertEquals(1, libreria.getLibri().size());
        assertTrue(libreria.getLibri().contains(libro));
    }

    @Test
    public void testModificaLibroCommand() {
        Libro libro = new Libro("Vecchio", "Autore", "0002", "Genere", 3, "letto");
        libreria.aggiungiLibro(libro);

        Comando comando = new ModificaLibroCommand(libreria, "0002", "Nuovo", "Autore2", "Genere2", 5, "non letto");
        comando.esegui();

        Libro modificato = libreria.getLibri().stream().filter(l -> l.getIsbn().equals("0002")).findFirst().orElse(null);
        assertNotNull(modificato);
        assertEquals("Nuovo", modificato.getTitolo());
        assertEquals("Autore2", modificato.getAutore());
        assertEquals(5, modificato.getValutazione());
        assertEquals("non letto", modificato.getStatoLettura());
    }

    @Test
    public void testRimuoviLibroCommand() {
        Libro libro = new Libro("Test", "Autore", "0003", "Genere", 3, "letto");
        libreria.aggiungiLibro(libro);

        Comando comando = new RimuoviLibroCommand(libreria, libro);
        comando.esegui();

        assertFalse(libreria.getLibri().contains(libro));
    }
}
