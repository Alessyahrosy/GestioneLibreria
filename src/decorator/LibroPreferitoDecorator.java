package decorator;

public class LibroPreferitoDecorator implements LibroComponent {

    private LibroComponent libro;

    public LibroPreferitoDecorator(LibroComponent libro) {
        this.libro = libro;
    }

    @Override
    public String getDescrizione() {
        return libro.getDescrizione() + " [Preferito]";
    }
}