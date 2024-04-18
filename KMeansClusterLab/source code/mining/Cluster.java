package mining;

import data.Data;
import data.Tuple;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

// La classe 'Cluster' rappresenta un cluster nell'ambito di un algoritmo di clustering.
// Gestisce un insieme di dati clusterizzati e il loro centroide.
public class Cluster implements Serializable {
    private Tuple centroid; // Il centroide del cluster, rappresentato come una tupla di valori.

    private Set<Integer> clusteredData; // Insieme di indici dei dati che appartengono a questo cluster.

    // Costruttore che inizializza il cluster con un centroide specificato.
    Cluster(Tuple centroid) {
        this.centroid = centroid;
        this.clusteredData = new HashSet<Integer>(); // Inizializza un insieme vuoto per i dati clusterizzati.
    }

    // Restituisce il centroide del cluster.
    Tuple getCentroid() {
        return centroid;
    }

    // Calcola il nuovo centroide del cluster basandosi sui dati attualmente clusterizzati.
    void computeCentroid(Data data) {
        for (int i = 0; i < centroid.getLength(); i++) {
            centroid.get(i).update(data, clusteredData); // Aggiorna ogni componente del centroide.
        }
    }

    // Aggiunge un nuovo dato al cluster e restituisce true se il dato non era già presente.
    boolean addData(int id) {
        return clusteredData.add(id);
    }

    // Verifica se un dato è già clusterizzato in questo cluster.
    boolean contain(int id) {
        return clusteredData.contains(id);
    }

    // Rimuove un dato dal cluster se ha cambiato cluster.
    void removeTuple(int id) {
        clusteredData.remove(id);
    }

    // Restituisce una rappresentazione in stringa del cluster, mostrando il centroide.
    public String toString() {
        String str = "Centroid=(";
        for (int i = 0; i < centroid.getLength(); i++)
            str += centroid.get(i);
        str += ")";
        return str;
    }

    // Restituisce una rappresentazione in stringa dettagliata del cluster, includendo il centroide
    // e tutti i dati clusterizzati con le loro distanze dal centroide.
    public String toString(Data data) {
        String str = "Centroid=(";
        for (int i = 0; i < centroid.getLength(); i++)
            str += centroid.get(i) + " ";
        str += ")\nExamples:\n";
        int[] array = clusteredData.stream().mapToInt(Integer::intValue).toArray();
        for (int i = 0; i < array.length; i++) {
            str += "[";
            for (int j = 0; j < data.getNumberOfExplanatoryAttributes(); j++)
                str += data.getAttributeValue(array[i], j) + " ";
            str += "] dist=" + getCentroid().getDistance(data.getItemSet(array[i])) + "\n";
        }
        str += "\nAvgDistance=" + getCentroid().avgDistance(data, clusteredData);
        return str;
    }
}
