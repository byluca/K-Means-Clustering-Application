package database;

import java.util.ArrayList;
import java.util.List;

// La classe 'Example' implementa l'interfaccia 'Comparable<Example>' per permettere il confronto tra istanze di questa classe.
public class Example implements Comparable<Example> {
    // Lista di Object per memorizzare i valori di un esempio o di una tupla.
    private List<Object> example = new ArrayList<Object>();

    // Metodo per aggiungere un oggetto alla lista 'example'.
    public void add(Object o) {
        example.add(o);
    }

    // Metodo per ottenere un oggetto dalla lista 'example' in base all'indice 'i'.
    public Object get(int i) {
        return example.get(i);
    }

    // Implementazione del metodo 'compareTo' dell'interfaccia 'Comparable'.
    // Questo metodo è utilizzato per confrontare questa istanza con un'altra istanza di 'Example'.
    public int compareTo(Example ex) {
        int i = 0;
        for (Object o : ex.example) {
            if (!o.equals(this.example.get(i)))
                // Cast dell'oggetto a 'Comparable' e confronto con l'oggetto corrispondente in 'this.example'.
                return ((Comparable)o).compareTo(example.get(i));
            i++;
        }
        return 0; // Ritorna 0 se tutti gli oggetti nelle due liste sono uguali.
    }

    // Sovrascrittura del metodo 'toString' per fornire una rappresentazione in stringa dell'istanza.
    public String toString() {
        String str = "";
        for (Object o : example) {
            str += o.toString() + " "; // Concatena la rappresentazione in stringa di ogni oggetto nella lista 'example'.
        }
        return str.trim(); // Ritorna la stringa finale rimuovendo gli spazi extra alla fine.
    }
}
