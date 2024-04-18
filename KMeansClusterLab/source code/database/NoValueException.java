package database;

// La classe 'NoValueException' estende 'Exception'. È una classe di eccezione personalizzata
// usata per segnalare che un valore atteso non è stato trovato o è assente.
public class NoValueException extends Exception {
    // Costruttore che accetta un messaggio di errore come parametro e lo passa al costruttore della superclasse 'Exception'.
    public NoValueException(String msg) {
        super(msg); // Chiama il costruttore della classe base 'Exception' con il messaggio di errore fornito.
    }
}
