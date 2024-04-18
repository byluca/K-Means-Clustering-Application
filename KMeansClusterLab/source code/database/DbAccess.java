package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;

// La classe 'DbAccess' fornisce i metodi per gestire la connessione al database.
public class DbAccess {
    // Costanti per la configurazione della connessione al database.
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver"; // Nome della classe del driver JDBC per MySQL.
    private static final String DBMS = "jdbc:mysql"; // Tipo di DBMS (Sistema di Gestione del Database).
    private static final String SERVER = "localhost"; // Indirizzo del server del database.
    private static final String DATABASE = "MapDB"; // Nome del database a cui connettersi.
    private static final int PORT = 3306; // Porta del server del database.
    private static final String USER_ID = "MapUser"; // Nome utente per l'accesso al database.
    private static final String PASSWORD = "map"; // Password per l'accesso al database.
    private Connection conn; // Variabile per mantenere la connessione al database.

    // Metodo per inizializzare la connessione al database.
    public void initConnection() throws DatabaseConnectionException {
        try {
            // Costruzione dell'URL per la connessione al database.
            String url = DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE;
            // Caricamento del driver JDBC.
            Class.forName(DRIVER_CLASS_NAME);
            // Apertura della connessione al database con le credenziali fornite.
            conn = DriverManager.getConnection(url, USER_ID, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            // Lancio di un'eccezione personalizzata in caso di errore durante la connessione.
            throw new DatabaseConnectionException("Errore durante la connessione al database.");
        }
    }

    // Metodo per ottenere l'oggetto Connection.
    public Connection getConnection() {
        return conn;
    }

    // Metodo per chiudere la connessione al database.
    public void closeConnection() {
        try {
            // Verifica che la connessione sia aperta prima di chiuderla.
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            // Gestione dell'eccezione in caso di errore durante la chiusura della connessione.
            e.printStackTrace();
        }
    }
}
