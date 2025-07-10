package test;

import model.Libro;
import model.Libreria;

public class MementoTest {
    public static void main(String[] args) {
        Libreria libreria = Libreria.getInstance();

        // Puliamo la libreria per non avere roba residua
        libreria.svuotaLibreria();

        // Aggiungo un libro e controllo che ci sia
        Libro libro1 = new Libro("111", "Libro Uno", "Autore A", "Fantasy", 4, "Letto");
        libreria.aggiungiLibro(libro1);
        System.out.println("Dopo aggiunta: " + libreria.getLibri().size()); // Deve stampare 1

        // Salvo stato e aggiungo un altro libro
        Libro libro2 = new Libro("222", "Libro Due", "Autore B", "Horror", 5, "Da leggere");
        libreria.aggiungiLibro(libro2);
        System.out.println("Dopo seconda aggiunta: " + libreria.getLibri().size()); // Deve stampare 2

        // Undo: torniamo allo stato prima del secondo libro
        libreria.annulla();
        System.out.println("Dopo undo: " + libreria.getLibri().size()); // Deve stampare 1

        // Redo: rifacciamo la seconda aggiunta
        libreria.rifai();
        System.out.println("Dopo redo: " + libreria.getLibri().size()); // Deve stampare 2

        // Undo due volte per svuotare la libreria (test limite)
        libreria.annulla();
        libreria.annulla();
        System.out.println("Dopo due undo: " + libreria.getLibri().size()); // Deve stampare 0 o messaggio "Niente da annullare"
    }
}
