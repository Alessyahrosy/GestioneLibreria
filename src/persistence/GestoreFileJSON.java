package persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Libreria;
import model.Libro;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


/**
 * Implementazione concreta di GestoreFile per serializzare
 * e deserializzare la libreria in formato JSON usando Gson.
 * Estende il template method definito in GestoreFile.
 */
public class GestoreFileJSON extends GestoreFile {

    private final Gson gson = new Gson();
    // Tipo per serializzare/deserializzare la lista di Libro
    private final Type listType = new TypeToken<List<Libro>>() {}.getType();
    // Percorso correntemente aperto, gestito dal template method
    private String percorsoAttuale;

    public GestoreFileJSON(Libreria libreria) {
        super(libreria);
    }

    @Override
    protected void scriviDati() {
        try (FileWriter writer = new FileWriter(percorsoAttuale)) {
            // Converte la lista di libri in JSON
            gson.toJson(libreria.getLibri(), listType, writer);
            System.out.println("Dati serializzati in JSON correttamente su " + percorsoAttuale);
        } catch (IOException e) {
            System.err.println("Errore scrittura JSON: " + e.getMessage());
        }
    }

    @Override
    protected void leggiDati() {
        try (FileReader reader = new FileReader(percorsoAttuale)) {
            List<Libro> caricati = gson.fromJson(reader, listType);
            if (caricati != null) {
                // Usa il metodo setLibri per aggiornare la lista interna
                libreria.setLibri(caricati);
            }
            System.out.println("Dati deserializzati da JSON correttamente da " + percorsoAttuale);
        } catch (IOException e) {
            System.err.println("Errore lettura JSON: " + e.getMessage());
        }
    }

    /**
     * Override per aggiornare percorso prima di chiamare template method salva.
     */
    @Override
    public final void salva(String percorso) {
        this.percorsoAttuale = percorso;
        super.salva(percorso);
    }

    /**
     * Override per aggiornare percorso prima di chiamare template method carica.
     */
    @Override
    public final void carica(String percorso) {
        this.percorsoAttuale = percorso;
        super.carica(percorso);
    }
}
