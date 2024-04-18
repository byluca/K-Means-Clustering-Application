package database;

// La classe 'EmptySetException' estende 'Exception'. È una classe di eccezione personalizzata
// usata per segnalare che un set di dati previsto come non vuoto risulta invece essere vuoto.
public class EmptySetException extends Exception {
    // Costruttore che accetta un messaggio di errore come parametro e lo passa al costruttore della superclasse 'Exception'.
    public EmptySetException(String msg) {
        super(msg); // Chiama il costruttore della classe base 'Exception' con il messaggio di errore fornito.
    }
}
