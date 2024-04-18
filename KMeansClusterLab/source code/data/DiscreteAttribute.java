package data;

import java.util.Arrays;
import java.util.Set;
import java.util.Iterator;
import java.util.TreeSet;

// La classe 'DiscreteAttribute' estende 'Attribute' e implementa 'Iterable<String>' per gestire attributi che possono assumere un set discreto di valori.
public class DiscreteAttribute extends Attribute implements Iterable<String>{

    // TreeSet 'values' contiene un set ordinato dei valori che l'attributo può assumere.
    private TreeSet<String> values;

    // Costruttore di 'DiscreteAttribute' inizializza l'attributo con un nome, un indice e un array di valori possibili.
    public DiscreteAttribute(String name, int index, String[] values) {
        super(name, index); // Chiama il costruttore della superclasse 'Attribute'.
        this.values = new TreeSet<String>(); // Inizializza il TreeSet.
        this.values.addAll(Arrays.asList(values)); // Aggiunge tutti i valori dall'array al TreeSet.
    }

    // Implementa il metodo 'iterator' per permettere iterazioni sul set dei valori.
    public Iterator<String> iterator() {
        return this.values.iterator();
    }

    // Metodo 'frequency' calcola la frequenza di un particolare valore 'v' all'interno di un set di ID specificato, utilizzando un dato dataset 'data'.
    int frequency(Data data, Set<Integer> idList, String v) {
        int cont = 0; // Contatore per la frequenza del valore.
        for (int i : idList) {
            // Verifica se il valore dell'attributo per l'indice 'i' corrisponde a 'v'.
            if (data.getAttributeValue(i, this.getIndex()).equals(v)) {
                cont++; // Incrementa il contatore se il valore corrisponde.
            }
        }
        return cont; // Ritorna il conteggio totale della frequenza del valore.
    }

}
