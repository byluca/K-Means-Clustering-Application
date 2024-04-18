package Default;

import data.Data;
import data.OutOfRangeSampleSize;
import keyboardinput.Keyboard;
import mining.KMeansMiner;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainTest {

    // Constants for common file names and responses
    private static final String FILE_NAME = "Save.dat";
    private static final String YES = "yes";
    private static final String NO = "no";


    // Main method of the program
    public static void main(String[] args) {
        Data data = new Data();
        System.out.println("Welcome to the clustering system");

        boolean run = true;
        while (run) {
            printMenu();
            int input = Keyboard.readInt();

            switch (input) {
                case 1:
                    handleClustering(data);
                    break;
                case 2:
                    run = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    // Prints the options menu to the screen
    private static void printMenu() {
        System.out.println("\n========================");
        System.out.println("1. Perform clustering");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
    }

    // Handles the logic to perform clustering
    private static void handleClustering(Data data) {
        KMeansMiner kmeans;
        while (true) {
            System.out.println("\nDo you want to load an existing ClusterSet? (yes/no)");
            String response = Keyboard.readString().toLowerCase();

            if (YES.equalsIgnoreCase(response)) {
                kmeans = attemptToLoadClusterSet(data);
                if (kmeans != null) break;
            } else if (NO.equalsIgnoreCase(response)) {
                kmeans = createNewClusterSet(data);
                promptToSaveClusterSet(kmeans);
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    // Attempts to load a ClusterSet from disk
    private static KMeansMiner attemptToLoadClusterSet(Data data) {
        try {
            KMeansMiner kmeans = new KMeansMiner(FILE_NAME);
            System.out.println("ClusterSet successfully loaded.\n" + kmeans.getC().toString(data));
            return kmeans;
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Make sure the file exists.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading the file: " + e.getMessage());
        }
        return null;
    }

    // Creates a new ClusterSet by running the K-means algorithm
    private static KMeansMiner createNewClusterSet(Data data) {
        while (true) {
            System.out.print("Enter the number of clusters (k): ");
            try {
                int k = Keyboard.readInt();
                KMeansMiner kmeans = new KMeansMiner(k);
                int numIter = kmeans.kmeans(data);
                System.out.println("Clustering completed in " + numIter + " iterations.");
                System.out.println("Details of the ClusterSet:\n" + kmeans.getC().toString(data));
                return kmeans;
            } catch (OutOfRangeSampleSize e) {
                System.out.println("Error: " + e.getMessage() + " Please try a different value.");
            }
        }
    }

    // Asks the user if they want to save the ClusterSet on disk
    private static void promptToSaveClusterSet(KMeansMiner kmeans) {
        System.out.println("Do you want to save this ClusterSet? (yes/no)");
        String save = Keyboard.readString().toLowerCase();
        if (YES.equalsIgnoreCase(save)) {
            try {
                kmeans.salva(FILE_NAME);
                System.out.println("ClusterSet successfully saved.");
            } catch (IOException e) {
                System.err.println("Error saving: " + e.getMessage());
            }
        }
    }
}
