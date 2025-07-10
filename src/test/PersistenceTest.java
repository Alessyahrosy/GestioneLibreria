package test;

import model.Libreria;
import model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersistenceTest {

    private Libreria libreria;
    private final String testFile = "test_libreria.json";

    @BeforeEach
    public void setup() {
        libreria = Libreria.getInstance();
        libreria.svuotaLibreria();
        // Elimina file di test se esiste
        File f = new File(testFile);
        if (f.exists()) f.delete();
    }

    @Test
    public void testSalvaCarica() throws Exception {
        Libro libro1 = new Libro("1984", "Orwell", "12345", "Distopico", 5, "letto");
        Libro libro2 = new Libro("Il Nome della Rosa", "Eco", "67890", "Storico", 4, "in corso");

        libreria.aggiungiLibro(libro1);
        libreria.aggiungiLibro(libro2);

        libreria.salvaSuFile(testFile);

        // Puliamo la libreria per simulare caricamento
        libreria.svuotaLibreria();
        assertTrue(libreria.getLibri().isEmpty());

        libreria.caricaDaFile(testFile);

        List<Libro> caricati = libreria.getLibri();
        assertEquals(2, caricati.size(), "Dovrebbero esserci 2 libri caricati");
        assertTrue(caricati.stream().anyMatch(l -> l.getIsbn().equals("12345")));
        assertTrue(caricati.stream().anyMatch(l -> l.getIsbn().equals("67890")));
    }
}
