package data;

// La classe 'DiscreteItem' estende 'Item' ed è progettata per gestire elementi con dati discreti.
public class DiscreteItem extends Item {

    // Costruttore di DiscreteItem, inizializza con un attributo discreto e il suo valore.
    DiscreteItem(DiscreteAttribute attribute, String value) {
        super(attribute, value); // Chiama il costruttore della superclasse 'Item'.
    }

    // Calcola e restituisce la distanza tra il valore di questo elemento e un altro valore 'a'.
    // Questo metodo è specificamente progettato per valori discreti, dove la distanza è 0 se i valori sono uguali, altrimenti 1.
    double distance(Object a) {
        return getValue().equals(a) ? 0 : 1; // Ritorna 0 se i valori sono uguali, altrimenti 1.
    }
}
