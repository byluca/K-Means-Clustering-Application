package data;

// La classe 'ContinuousAttribute' estende 'Attribute' per gestire attributi che hanno un intervallo continuo di valori.
public class ContinuousAttribute extends Attribute {

    // 'max' e 'min' sono i valori massimo e minimo che questo attributo può effettivamente assumere.
    private final double max;
    private final double min;

    // Il costruttore di 'ContinuousAttribute' inizializza l'attributo con un nome, un indice e l'intervallo di valori che può assumere.
    public ContinuousAttribute(String name, int index, double min, double max) {
        super(name, index);  // Chiama il costruttore della superclasse (Attribute).
        this.min = min;      // Imposta il valore minimo per questo attributo.
        this.max = max;      // Imposta il valore massimo per questo attributo.
    }

    // Il metodo 'getScaledValue' calcola e restituisce un valore scalato di 'v'.
    // La scalatura è basata sull'intervallo (max - min) dove il valore originale 'v' è normalizzato tra 0 e 1.
    public double getScaledValue(double v) {
        // Normalizza 'v' sottraendo 'min' e poi dividendo per l'intervallo 'max' - 'min'.
        v = (v - this.min) / (this.max - this.min);
        return v;  // Restituisce il valore normalizzato.
    }
}
