package persistence;

import model.Libreria;

/**
 * Classe astratta che definisce il template method per operazioni di I/O
 * su file, con i metodi apriFile(), chiudiFile() comuni e
 * i metodi astratti scriviDati() e leggiDati() da implementare nelle sottoclassi.
 */
public abstract class GestoreFile {

    protected Libreria libreria;
    protected String percorsoCorrente;  // percorso impostato dal client

    public GestoreFile(Libreria libreria) {
        this.libreria = libreria;
    }

    /**
     * Template method per salvare su file.
     * Esegue in sequenza: apriFile, scriviDati, chiudiFile.
     * @param percorso percorso del file dove salvare
     */
    public void salva(String percorso) {
        this.percorsoCorrente = percorso;
        apriFile(percorso);
        scriviDati();
        chiudiFile();
    }

    /**
     * Template method per caricare da file.
     * Esegue in sequenza: apriFile, leggiDati, chiudiFile.
     * @param percorso percorso del file da cui leggere
     */
    public void carica(String percorso) {
        this.percorsoCorrente = percorso;
        apriFile(percorso);
        leggiDati();
        chiudiFile();
    }

    /**
     * Metodo "gancio" per aprire il file.
     * Può essere sovrascritto nelle sottoclassi per logiche specifiche.
     * @param percorso percorso file
     */
    protected void apriFile(String percorso) {
        System.out.println("Apro file " + percorso);
    }

    /**
     * Metodo "gancio" per chiudere il file.
     * Può essere sovrascritto nelle sottoclassi per logiche specifiche.
     */
    protected void chiudiFile() {
        System.out.println("Chiudo file");
    }

    // Metodi astratti da implementare nelle sottoclassi per scrivere e leggere dati.
    protected abstract void scriviDati();
    protected abstract void leggiDati();
}
