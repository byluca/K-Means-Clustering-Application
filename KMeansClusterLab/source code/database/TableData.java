package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import database.Table_Schema.Column;

// La classe 'TableData' fornisce metodi per interagire con i dati delle tabelle nel database.
public class TableData {
    private final DbAccess db; // Riferimento a DbAccess per l'accesso al database.

    // Costruttore che inizializza la classe con un'istanza di DbAccess.
    public TableData(DbAccess db) {
        this.db = db;
    }

    // Metodo che restituisce una lista di transazioni distinte dalla tabella specificata.
    public List<Example> getDistinctTransazioni(String table) throws SQLException, EmptySetException {
        List<Example> distinctTransazioni = new ArrayList<>();
        String query = "SELECT DISTINCT * FROM " + table;
        try (Statement stmt = db.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (!rs.next()) {
                throw new EmptySetException("La tabella è vuota");
            } else {
                do {
                    Example example = new Example();
                    // Aggiungi il codice per estrarre i valori dalle colonne del result set
                    // e aggiungerli all'oggetto Example
                    distinctTransazioni.add(example);
                } while (rs.next());
            }
        }
        return distinctTransazioni;
    }

    // Metodo che restituisce un insieme di valori distinti per una colonna specificata in una tabella.
    public Set<Object> getDistinctColumnValues(String table, Column column) throws SQLException {
        Set<Object> distinctValues = new HashSet<>();
        String query = "SELECT DISTINCT " + column.getColumnName() + " FROM " + table;
        try (Statement stmt = db.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object value = rs.getObject(column.getColumnName());
                distinctValues.add(value);
            }
        }
        return distinctValues;
    }

    // Metodo che calcola e restituisce un valore aggregato (minimo o massimo) per una colonna specificata in una tabella.
    public Object getAggregateColumnValue(String table, Column column, QUERY_TYPE aggregate) throws SQLException, NoValueException {
        Object aggregateValue = null;
        String aggregateFunction = "";

        switch (aggregate) {
            case MIN:
                aggregateFunction = "MIN";
                break;
            case MAX:
                aggregateFunction = "MAX";
                break;
            default:
                throw new IllegalArgumentException("Operatore di aggregazione non supportato: " + aggregate);
        }

        String query = "SELECT " + aggregateFunction + "(" + column.getColumnName() + ") FROM " + table;

        try (Statement stmt = db.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                aggregateValue = rs.getObject(1);

                if (aggregateValue == null) {
                    throw new NoValueException("Il valore aggregato calcolato è pari a null.");
                }
            } else {
                throw new NoValueException("Il resultset è vuoto per la tabella");
            }
        }

        return aggregateValue;
    }

    // Enumerazione interna che definisce i tipi di query aggregata supportati.
    public enum QUERY_TYPE {
        MIN,
        MAX
    }
}
