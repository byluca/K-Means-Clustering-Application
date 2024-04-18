package data;

import java.io.Serializable;

// Classe astratta 'Attribute' che deve essere ereditata da tipi specifici di attributi. Implementa Serializable per la serializzazione degli oggetti.
abstract class Attribute implements Serializable {
    // 'name' è una stringa simbolica che rappresenta il nome dell'attributo.
    private final String name;
    // 'index' è un identificatore numerico per l'attributo all'interno di un dataset o di una struttura.
    private final int index;

    // Costruttore della classe. Inizializza 'name' e 'index' con i valori forniti.
    Attribute(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // Metodo getter per 'name'. Restituisce il nome simbolico dell'attributo.
    String getName() {
        return this.name;
    }

    // Metodo getter per 'index'. Restituisce l'indice numerico dell'attributo.
    int getIndex() {
        return this.index;
    }

    // Sovrascrive il metodo 'toString' per restituire il nome dell'attributo come rappresentazione sotto forma di stringa.
    public String toString() {
        return this.name;
    }
}
