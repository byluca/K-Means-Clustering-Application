package mining;

import data.Data;
import data.OutOfRangeSampleSize;
import java.io.*;

// La classe 'KMeansMiner' gestisce il processo di clustering utilizzando l'algoritmo K-means.
public class KMeansMiner {
    private ClusterSet C; // Insieme di cluster gestiti da questa istanza.

    // Costruttore che inizializza l'insieme di cluster con un numero specificato di cluster.
    public KMeansMiner(int k) throws OutOfRangeSampleSize {
        C = new ClusterSet(k);
    }

    // Restituisce l'insieme di cluster corrente.
    public ClusterSet getC() {
        return C;
    }

    // Costruttore che carica l'insieme di cluster da un file.
    public KMeansMiner(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        try (ObjectInputStream pp = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            C = (ClusterSet) pp.readObject(); // Legge l'oggetto ClusterSet dal file.
        }
    }

    // Metodo per eseguire l'algoritmo K-means sul set di dati fornito.
    public int kmeans(Data data) throws OutOfRangeSampleSize {
        int numberOfIterations = 0;
        boolean changedCluster; // Indica se c'è stata una modifica nei cluster durante l'iterazione.
        // Inizializza i centroidi dei cluster.
        C.initializeCentroids(data);
        do {
            numberOfIterations++; // Incrementa il contatore delle iterazioni.
            changedCluster = false;
            for (int i = 0; i < data.getNumberOfExamples(); i++) {
                Cluster nearestCluster = C.nearestCluster(data.getItemSet(i)); // Trova il cluster più vicino per l'esempio i.
                Cluster oldCluster = C.currentCluster(i); // Trova il cluster corrente che contiene l'esempio i.
                boolean currentChange = nearestCluster.addData(i); // Aggiunge l'esempio i al cluster più vicino.
                if (currentChange)
                    changedCluster = true; // Se l'esempio i è stato aggiunto a un nuovo cluster, segna che c'è stato un cambiamento.
                if (currentChange && oldCluster != null)
                    oldCluster.removeTuple(i); // Rimuove l'esempio i dal suo vecchio cluster se necessario.
            }
            // Aggiorna i centroidi dei cluster dopo ogni iterazione.
            C.updateCentroids(data);
        } while (changedCluster); // Continua fino a quando non ci sono più cambiamenti nei cluster.
        return numberOfIterations; // Restituisce il numero totale di iterazioni eseguite.
    }

    // Metodo per salvare l'insieme di cluster corrente in un file.
    public void salva(String fileName) throws FileNotFoundException, IOException {
        try (ObjectOutputStream salvataggio = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            salvataggio.writeObject(C); // Scrive l'oggetto ClusterSet nel file.
        }
    }
}
