package data;

// La classe 'OutOfRangeSampleSize' estende 'Exception'. È una classe di eccezione personalizzata utilizzata per segnalare
// che la dimensione del campione specificata è fuori dall'intervallo accettabile.
public class OutOfRangeSampleSize extends Exception {

    // Costruttore che accetta un messaggio di errore come parametro e lo passa al costruttore della superclasse 'Exception'.
    public OutOfRangeSampleSize(String msg) {
        super(msg); // Chiama il costruttore della classe base 'Exception' con il messaggio di errore fornito.
    }
}
