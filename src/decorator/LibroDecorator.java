package decorator;

public abstract class LibroDecorator implements LibroComponent {
    protected LibroComponent libro;

    public LibroDecorator(LibroComponent libro) {
        this.libro = libro;
    }

    @Override
    public String getDescrizione() {
        return libro.getDescrizione();
    }
}