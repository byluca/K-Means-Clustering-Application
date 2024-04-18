package mining;

import mining.Cluster;
import data.Data;
import data.OutOfRangeSampleSize;
import data.Tuple;

import java.io.Serializable;

// La classe 'ClusterSet' implementa Serializable e gestisce un insieme di cluster.
public class ClusterSet implements Serializable {

    private Cluster[] C; // Array di cluster
    private int i = 0; // Indice per la creazione di un nuovo cluster

    // Costruttore che inizializza il ClusterSet con un numero specificato di cluster.
    ClusterSet(int k) throws OutOfRangeSampleSize {
        if (k <= 0) throw new OutOfRangeSampleSize("Numero di cluster errato"); // Verifica che il numero di cluster sia valido
        C = new Cluster[k]; // Inizializza l'array di Cluster
        i = 0; // Reimposta il contatore di posizione
    }

    // Metodo privato per aggiungere un cluster all'array.
    private void add(Cluster c) {
        C[i] = c;
        i++;
    }

    // Restituisce il cluster all'indice specificato.
    private Cluster get(int i) {
        return C[i];
    }

    // Inizializza i centroidi dei cluster scegliendo tuple casuali dai dati.
    void initializeCentroids(Data data) throws OutOfRangeSampleSize {
        int[] centroidIndexes = data.sampling(C.length); // Ottiene indici casuali per i centroidi
        for (int centroidIndex : centroidIndexes) {
            Tuple centroidI = data.getItemSet(centroidIndex); // Ottiene la tupla dal dataset
            add(new Cluster(centroidI)); // Crea un nuovo cluster con il centroide specificato
        }
    }

    // Restituisce il cluster più vicino alla tupla specificata.
    Cluster nearestCluster(Tuple tuple) {
        double minimo = tuple.getDistance(C[0].getCentroid());
        Cluster c = C[0]; // Assume che il primo cluster sia il più vicino
        double var;
        for (int i = 1; i < C.length; i++) {
            var = tuple.getDistance(C[i].getCentroid());
            if (var < minimo) {
                minimo = var;
                c = C[i]; // Trova il cluster più vicino
            }
        }
        return c;
    }

    // Restituisce il cluster corrente di una tupla identificata dall'id, se esiste; altrimenti restituisce null.
    Cluster currentCluster(int id) {
        for (int i = 0; i < C.length; i++) {
            if (C[i].contain(id)) {
                return C[i];
            }
        }
        return null;
    }

    // Aggiorna i centroidi di tutti i cluster basandosi sui dati attuali.
    void updateCentroids(Data data) {
        for (int i = 0; i < C.length; i++) {
            C[i].computeCentroid(data);
        }
    }

    // Restituisce una rappresentazione in stringa del ClusterSet, mostrando tutti i cluster.
    public String toString() {
        String str = " ";
        for (int i = 0; i < C.length; i++) {
            str += C[i].toString();
        }
        return str;
    }

    // Restituisce una rappresentazione in stringa dettagliata del ClusterSet, includendo dati specifici.
    public String toString(Data data) {
        String str = "";
        for (int i = 0; i < C.length; i++) {
            if (C[i] != null) {
                str += i + ":" + C[i].toString(data) + "\n";
            }
        }
        return str;
    }
}
