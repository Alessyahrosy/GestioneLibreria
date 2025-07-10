package test;

import model.Libro;
import observer.Observer;
import model.Libreria;

public class ObserverTest implements Observer {
    private boolean aggiornato = false;

    @Override
    public void aggiorna() {
        System.out.println("ObserverTest: Notifica ricevuta!");
        aggiornato = true;
    }

    public boolean isAggiornato() {
        return aggiornato;
    }

    public static void main(String[] args) {
        Libreria lib = Libreria.getInstance();
        ObserverTest observer = new ObserverTest();

        lib.aggiungiObserver(observer);
        lib.aggiungiLibro(new Libro("1234567890", "Titolo Test", "Autore Test", "Genere", 5, "Letto"));

        if(observer.isAggiornato()) {
            System.out.println("Test OK: Observer notificato correttamente");
        } else {
            System.out.println("Test FAIL: Observer NON notificato");
        }

        lib.rimuoviObserver(observer);
    }
}
