package database;

// La classe 'DatabaseConnectionException' estende 'Exception'. È una classe di eccezione personalizzata
// utilizzata per segnalare errori specifici relativi alla connessione al database.
public class DatabaseConnectionException extends Exception {

    // Costruttore che accetta un messaggio di errore come parametro e lo passa al costruttore della superclasse 'Exception'.
    public DatabaseConnectionException(String message) {
        super(message); // Chiama il costruttore della classe base 'Exception' con il messaggio di errore fornito.
    }
}
