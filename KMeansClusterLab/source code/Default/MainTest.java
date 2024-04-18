package Default;

import data.Data;
import data.OutOfRangeSampleSize;
import keyboardinput.Keyboard;
import mining.KMeansMiner;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainTest {

    // Costanti per i nomi dei file comuni e le risposte
    private static final String FILE_NAME = "Save.dat"; // Nome del file per salvare/caricare il set di cluster
    private static final String YES = "yes";            // Risposta per azioni affermative
    private static final String NO = "no";              // Risposta per azioni negative

    // Metodo principale del programma
    public static void main(String[] args) {
        Data data = new Data(); // Crea un'istanza di Data
        System.out.println("Welcome to the clustering system"); // Messaggio di benvenuto

        boolean run = true;
        while (run) {
            printMenu(); // Stampa il menu delle opzioni
            int input = Keyboard.readInt(); // Legge l'input dell'utente

            switch (input) {
                case 1:
                    handleClustering(data); // Gestisce il processo di clustering
                    break;
                case 2:
                    run = false; // Termina il ciclo while, uscendo dal programma
                    System.out.println("Exiting..."); // Messaggio di uscita
                    break;
                default:
                    System.out.println("Invalid choice. Please try again."); // Gestione dell'input non valido
            }
        }
    }

    // Stampa il menu delle opzioni a schermo
    private static void printMenu() {
        System.out.println("\n========================");
        System.out.println("1. Perform clustering"); // Opzione per eseguire il clustering
        System.out.println("2. Exit"); // Opzione per uscire
        System.out.print("Enter your choice: "); // Richiesta di inserimento della scelta
    }

    // Gestisce la logica per eseguire il clustering
    private static void handleClustering(Data data) {
        KMeansMiner kmeans;
        while (true) {
            System.out.println("\nDo you want to load an existing ClusterSet? (yes/no)"); // Chiede se caricare un set esistente
            String response = Keyboard.readString().toLowerCase();

            if (YES.equalsIgnoreCase(response)) {
                kmeans = attemptToLoadClusterSet(data); // Tenta di caricare un set di cluster esistente
                if (kmeans != null) break; // Se il caricamento è riuscito, esce dal ciclo
            } else if (NO.equalsIgnoreCase(response)) {
                kmeans = createNewClusterSet(data); // Crea un nuovo set di cluster
                promptToSaveClusterSet(kmeans); // Chiede se salvare il nuovo set di cluster
                break; // Esce dal ciclo
            } else {
                System.out.println("Invalid input. Please try again."); // Gestione dell'input non valido
            }
        }
    }

    // Tenta di caricare un ClusterSet da disco
    private static KMeansMiner attemptToLoadClusterSet(Data data) {
        try {
            KMeansMiner kmeans = new KMeansMiner(FILE_NAME); // Crea un'istanza di KMeansMiner dal file
            System.out.println("ClusterSet successfully loaded.\n" + kmeans.getC().toString(data)); // Messaggio di successo
            return kmeans; // Ritorna l'oggetto kmeans caricato
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Make sure the file exists."); // Gestione del file non trovato
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading the file: " + e.getMessage()); // Gestione di altri errori di caricamento
        }
        return null; // Ritorna null se il caricamento fallisce
    }

    // Crea un nuovo ClusterSet eseguendo l'algoritmo k-means
    private static KMeansMiner createNewClusterSet(Data data) {
        while (true) {
            System.out.print("Enter the number of clusters (k): "); // Richiesta del numero di cluster
            try {
                int k = Keyboard.readInt(); // Legge il numero di cluster
                KMeansMiner kmeans = new KMeansMiner(k); // Crea un nuovo KMeansMiner con il numero di cluster specificato
                int numIter = kmeans.kmeans(data); // Esegue l'algoritmo k-means
                System.out.println("Clustering completed in " + numIter + " iterations."); // Messaggio di completamento
                System.out.println("Details of the ClusterSet:\n" + kmeans.getC().toString(data)); // Dettagli del set di cluster
                return kmeans; // Ritorna l'oggetto kmeans creato
            } catch (OutOfRangeSampleSize e) {
                System.out.println("Error: " + e.getMessage() + " Please try a different value."); // Gestione dell'errore di range
            }
        }
    }

    // Chiede all'utente se vuole salvare il ClusterSet su disco
    private static void promptToSaveClusterSet(KMeansMiner kmeans) {
        System.out.println("Do you want to save this ClusterSet? (yes/no)"); // Domanda se salvare il set
        String save = Keyboard.readString().toLowerCase();
        if (YES.equalsIgnoreCase(save)) {
            try {
                kmeans.salva(FILE_NAME); // Salva il set nel file
                System.out.println("ClusterSet successfully saved."); // Messaggio di salvataggio riuscito
            } catch (IOException e) {
                System.err.println("Error saving: " + e.getMessage()); // Gestione dell'errore di salvataggio
            }
        }
    }
}
