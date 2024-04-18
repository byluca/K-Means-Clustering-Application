package data;

import java.io.Serializable;
import java.util.Set;

// La classe astratta 'Item' implementa Serializable e rappresenta un elemento con un attributo e un valore associato.
public abstract class Item implements Serializable {

    Attribute attribute; // Attributo coinvolto nell'item.
    Object value;        // Valore assegnato all'attributo.

    // Costruttore che inizializza un item con un attributo e un valore associato.
    Item(Attribute attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }

    // Metodo privato per ottenere l'attributo associato all'item.
    private Attribute getAttribute() {
        return this.attribute;
    }

    // Restituisce il valore dell'item.
    Object getValue() {
        return this.value;
    }

    // Metodo per convertire l'item in una stringa. Restituisce la rappresentazione in stringa del valore.
    public String toString() {
        return this.value.toString();
    }

    // Metodo astratto per calcolare la distanza tra questo item e un altro oggetto.
    abstract double distance(Object a);

    // Metodo per aggiornare il valore di questo item basandosi sul prototipo calcolato da un insieme di dati.
    public void update(Data data, Set<Integer> clusterData) {
        this.value = data.computePrototype(clusterData, this.attribute);
    }
}
