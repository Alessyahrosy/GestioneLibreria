package test;

import decorator.LibroPreferitoDecorator;
import decorator.LibroComponent;
import model.Libro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DecoratorTest {

    // Un piccolo "stub" che implementa LibroComponent, restituisce una descrizione fissa
    private static class LibroBaseStub implements LibroComponent {
        private String descrizione;
        public LibroBaseStub(String descrizione) {
            this.descrizione = descrizione;
        }
        @Override
        public String getDescrizione() {
            return descrizione;
        }
    }

    @Test
    public void testDescrizioneConDecorator() {
        LibroComponent base = new LibroBaseStub("Il Grande Gatsby");
        LibroComponent decorato = new LibroPreferitoDecorator(base);

        String descrizione = decorato.getDescrizione();

        assertEquals("Il Grande Gatsby [Preferito]", descrizione);
    }
}
