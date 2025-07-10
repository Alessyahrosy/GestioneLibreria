package main;

import java.util.List;
import java.util.Scanner;

import factory.LibroFactory;
import factory.LibroFactoryImpl;
import model.Libreria;
import model.Libro;
import strategy.OrdinaPerTitolo;
import strategy.OrdinaPerAutore;
import strategy.OrdinaPerIsbn;
import strategy.FiltroPerGenere;
import strategy.FiltroPerStatoLettura;

import command.Comando;
import command.AggiungiLibroCommand;
import command.ModificaLibroCommand;
import command.RimuoviLibroCommand;

public class MainMenu {
    public static void main(String[] args) {
        Libreria libreria = Libreria.getInstance();
        LibroFactory factory = new LibroFactoryImpl();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            stampaMenu();

            int scelta = -1;
            try {
                System.out.print("Scelta: ");
                scelta = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Errore: inserisci un numero valido.");
                continua(scanner);
                continue;
            }

            switch (scelta) {
                case 1 -> {
                    System.out.print("Titolo: ");
                    String titolo = scanner.nextLine();
                    System.out.print("Autore: ");
                    String autore = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();

                    boolean esiste = libreria.getLibri().stream()
                            .anyMatch(l -> l.getIsbn().equals(isbn));
                    if (esiste) {
                        System.out.println("Errore: esiste già un libro con questo ISBN.");
                        continua(scanner);
                        break;
                    }

                    System.out.print("Genere: ");
                    String genere = scanner.nextLine();
                    int valutazione = leggiValutazione(scanner);
                    System.out.print("Stato lettura: ");
                    String stato = scanner.nextLine();

                    Libro libro = factory.creaLibro(titolo, autore, isbn, genere, valutazione, stato);

                    Comando comandoAggiungi = new AggiungiLibroCommand(libreria, libro);
                    comandoAggiungi.esegui();

                    System.out.println("Libro aggiunto con successo!");
                    continua(scanner);
                }
                case 2 -> {
                    System.out.print("Inserisci ISBN libro da modificare: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Nuovo titolo: ");
                    String titolo = scanner.nextLine();
                    System.out.print("Nuovo autore: ");
                    String autore = scanner.nextLine();
                    System.out.print("Nuovo genere: ");
                    String genere = scanner.nextLine();
                    int valutazione = leggiValutazione(scanner);
                    System.out.print("Nuovo stato lettura: ");
                    String stato = scanner.nextLine();

                    Comando comandoModifica = new ModificaLibroCommand(libreria, isbn, titolo, autore, genere, valutazione, stato);
                    comandoModifica.esegui();

                    System.out.println("Modifica completata.");
                    continua(scanner);
                }
                case 3 -> {
                    System.out.print("Inserisci ISBN libro da rimuovere: ");
                    String isbn = scanner.nextLine();
                    Libro daRimuovere = null;
                    for (Libro l : libreria.getLibri()) {
                        if (l.getIsbn().equals(isbn)) {
                            daRimuovere = l;
                            break;
                        }
                    }
                    if (daRimuovere != null) {
                        System.out.print("Sei sicuro di voler eliminare il libro? (S/N): ");
                        String conferma = scanner.nextLine().trim().toUpperCase();
                        if (conferma.equals("S")) {
                            Comando comandoRimuovi = new RimuoviLibroCommand(libreria, daRimuovere);
                            comandoRimuovi.esegui();
                            System.out.println("Libro rimosso con successo!");
                        } else {
                            System.out.println("Cancellazione annullata.");
                        }
                    } else {
                        System.out.println("Libro non trovato.");
                    }
                    continua(scanner);
                }
                case 4 -> {
                    System.out.println("Libri in libreria:");
                    if (libreria.getLibri().isEmpty()) {
                        System.out.println("Nessun libro presente.");
                    } else {
                        for (Libro l : libreria.getLibri()) {
                            System.out.println(l);
                        }
                    }
                    continua(scanner);
                }
                case 5 -> {
                    libreria.setCriterioOrdinamento(new OrdinaPerTitolo());
                    libreria.ordinaLibri();
                    System.out.println("Libri ordinati per titolo.");
                    continua(scanner);
                }
                case 6 -> {
                    libreria.setCriterioOrdinamento(new OrdinaPerAutore());
                    libreria.ordinaLibri();
                    System.out.println("Libri ordinati per autore.");
                    continua(scanner);
                }
                case 7 -> {
                    libreria.setCriterioOrdinamento(new OrdinaPerIsbn());
                    libreria.ordinaLibri();
                    System.out.println("Libri ordinati per ISBN.");
                    continua(scanner);
                }
                case 8 -> {
                    System.out.print("Inserisci genere da filtrare: ");
                    String genere = scanner.nextLine();
                    List<Libro> filtrati = libreria.filtraLibri(new FiltroPerGenere(genere));
                    if (filtrati.isEmpty()) {
                        System.out.println("Nessun libro trovato per il genere: " + genere);
                    } else {
                        System.out.println("Libri filtrati per genere " + genere + ":");
                        for (Libro l : filtrati) System.out.println(l);
                    }
                    continua(scanner);
                }
                case 9 -> {
                    System.out.print("Inserisci stato lettura da filtrare: ");
                    String stato = scanner.nextLine();
                    List<Libro> filtrati = libreria.filtraLibri(new FiltroPerStatoLettura(stato));
                    if (filtrati.isEmpty()) {
                        System.out.println("Nessun libro trovato per lo stato lettura: " + stato);
                    } else {
                        System.out.println("Libri filtrati per stato lettura " + stato + ":");
                        for (Libro l : filtrati) System.out.println(l);
                    }
                    continua(scanner);
                }
                case 10 -> {
                    System.out.print("Nome file per salvare (es. libreria.json): ");
                    String file = scanner.nextLine();
                    try {
                        libreria.salvaSuFile(file);
                        System.out.println("Dati salvati su file " + file);
                    } catch (Exception e) {
                        System.out.println("Errore nel salvataggio: " + e.getMessage());
                    }
                    continua(scanner);
                }
                case 11 -> {
                    System
                            .out.print("Nome file da caricare (es. libreria.json): ");
                    String file = scanner.nextLine();
                    try {
                        libreria.caricaDaFile(file);
                        System.out.println("Dati caricati da file " + file);
                    } catch (Exception e) {
                        System.out.println("Errore nel caricamento: " + e.getMessage());
                    }
                    continua(scanner);
                }
                case 12 -> {
                    libreria.annulla();
                    System.out.println("Undo effettuato.");
                    continua(scanner);
                }
                case 13 -> {
                    libreria.rifai();
                    System.out.println("Redo effettuato.");
                    continua(scanner);
                }
                case 0 -> {
                    System.out.println("Arrivederci!");
                    exit = true;
                }
                default -> {
                    System.out.println("Scelta non valida.");
                    continua(scanner);
                }
            }
        }
        scanner.close();
    }

    private static void stampaMenu() {
        System.out.println("\n--- Menu Libreria ---");
        System.out.println("1. Aggiungi libro");
        System.out.println("2. Modifica libro");
        System.out.println("3. Rimuovi libro");
        System.out.println("4. Visualizza libri");
        System.out.println("5. Ordina libri per titolo");
        System.out.println("6. Ordina libri per autore");
        System.out.println("7. Ordina libri per ISBN");
        System.out.println("8. Filtra per genere");
        System.out.println("9. Filtra per stato lettura");
        System.out.println("10. Salva su file");
        System.out.println("11. Carica da file");
        System.out.println("12. Undo");
        System.out.println("13. Redo");
        System.out.println("0. Esci");
    }

    private static int leggiValutazione(Scanner scanner) {
        int val = -1;
        do {
            try {
                System.out.print("Valutazione (1-5): ");
                val = Integer.parseInt(scanner.nextLine());
                if (val < 1 || val > 5) {
                    System.out.println("Valutazione deve essere tra 1 e 5.");
                    val = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Inserisci un numero valido per la valutazione.");
            }
        } while (val == -1);
        return val;
    }

    private static void continua(Scanner scanner) {
        System.out.println("\nPremi Invio per tornare al menu...");
        scanner.nextLine();
    }
}