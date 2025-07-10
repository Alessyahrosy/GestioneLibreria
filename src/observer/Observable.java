package observer;

public interface Observable {
    void aggiungiObserver(Observer o);
    void rimuoviObserver(Observer o);
    void notificaObservers();
}