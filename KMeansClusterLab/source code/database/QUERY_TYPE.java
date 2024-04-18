package database;

// L'enumerazione 'QUERY_TYPE' definisce costanti per specificare il tipo di query che può essere eseguita su un database.
// Questo enum è utilizzato per identificare se la query richiesta è per trovare il valore minimo o massimo.
public enum QUERY_TYPE {
    MIN, // Rappresenta una query che richiede il valore minimo di un certo campo o colonna.
    MAX  // Rappresenta una query che richiede il valore massimo di un certo campo o colonna.
}
