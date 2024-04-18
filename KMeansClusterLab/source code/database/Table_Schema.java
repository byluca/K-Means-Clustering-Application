package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// La classe 'Table_Schema' è utilizzata per ottenere e gestire lo schema di una tabella specifica nel database.
public class Table_Schema {
    DbAccess db; // Oggetto per l'accesso al database.

    // Classe interna 'Column' rappresenta una colonna all'interno della tabella.
    public class Column {
        private String name; // Nome della colonna.
        private String type; // Tipo di dato della colonna (adattato per Java).

        // Costruttore della classe Column.
        Column(String name, String type) {
            this.name = name;
            this.type = type;
        }

        // Restituisce il nome della colonna.
        public String getColumnName() {
            return name;
        }

        // Determina se il tipo di colonna è numerico.
        public boolean isNumber() {
            return type.equals("number");
        }

        // Restituisce una rappresentazione in stringa della colonna.
        public String toString() {
            return name + ":" + type;
        }
    }

    List<Column> tableSchema = new ArrayList<Column>(); // Lista delle colonne dello schema della tabella.

    // Costruttore di 'Table_Schema' che inizializza lo schema basandosi sul nome della tabella.
    public Table_Schema(DbAccess db, String tableName) throws SQLException {
        this.db = db;
        HashMap<String, String> mapSQL_JAVATypes = new HashMap<String, String>();
        // Mapping dei tipi SQL ai tipi Java appropriati.
        mapSQL_JAVATypes.put("CHAR", "string");
        mapSQL_JAVATypes.put("VARCHAR", "string");
        mapSQL_JAVATypes.put("LONGVARCHAR", "string");
        mapSQL_JAVATypes.put("BIT", "string");
        mapSQL_JAVATypes.put("SHORT", "number");
        mapSQL_JAVATypes.put("INT", "number");
        mapSQL_JAVATypes.put("LONG", "number");
        mapSQL_JAVATypes.put("FLOAT", "number");
        mapSQL_JAVATypes.put("DOUBLE", "number");

        Connection con = db.getConnection();
        DatabaseMetaData meta = con.getMetaData();
        ResultSet res = meta.getColumns(null, null, tableName, null);

        // Ciclo che percorre il ResultSet delle colonne della tabella, aggiungendo ciascuna colonna allo schema.
        while (res.next()) {
            if (mapSQL_JAVATypes.containsKey(res.getString("TYPE_NAME")))
                tableSchema.add(new Column(
                        res.getString("COLUMN_NAME"),
                        mapSQL_JAVATypes.get(res.getString("TYPE_NAME")))
                );
        }
        res.close();
    }

    // Restituisce il numero di attributi (colonne) nello schema della tabella.
    public int getNumberOfAttributes() {
        return tableSchema.size();
    }

    // Restituisce la colonna all'indice specificato.
    public Column getColumn(int index) {
        return tableSchema.get(index);
    }
}
