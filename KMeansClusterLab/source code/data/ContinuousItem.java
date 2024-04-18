package data;

// La classe 'ContinuousItem' estende 'Item' ed è progettata per gestire elementi con dati continui.
public class ContinuousItem extends Item {
    // Costruttore di ContinuousItem, inizializza con un attributo e il suo valore.
    ContinuousItem(Attribute attribute, Double value) {
        super(attribute, value); // Chiama il costruttore della superclasse 'Item'.
    }

    // Calcola e restituisce la distanza tra il valore di questo elemento e un altro valore 'a'.
    // Questo metodo è specificamente progettato per gestire valori continui tramite la loro scalatura.
    public double distance(Object a) {
        // Verifica se l'oggetto fornito 'a' è un'istanza di Double. Se non lo è, lancia un'eccezione.
        if (!(a instanceof Double)) {
            throw new IllegalArgumentException("Il parametro deve essere un'istanza di Double.");
        }

        // Effettua il cast dell'attributo a 'ContinuousAttribute' per utilizzare i suoi metodi specifici per dati continui.
        ContinuousAttribute continuousAttribute = (ContinuousAttribute) this.attribute;

        // Ottiene il valore scalato di questo elemento. La scalatura è necessaria per normalizzare i valori ai fini del confronto.
        double thisScaledValue = continuousAttribute.getScaledValue((Double) this.value);

        // Ottiene il valore scalato dell'oggetto 'a' da confrontare con il valore di questo elemento.
        double otherScaledValue = continuousAttribute.getScaledValue((Double) a);

        // Restituisce la differenza assoluta tra i valori scalati, che rappresenta la distanza.
        return Math.abs(thisScaledValue - otherScaledValue);
    }
}
