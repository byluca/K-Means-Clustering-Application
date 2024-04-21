import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mining.KMeansMiner;
import data.Data;
import data.OutOfRangeSampleSize;

public class MainTest extends Application {
    private TextArea textArea;
    private Data data;
    private KMeansMiner kmeans;

    @Override
    public void start(Stage primaryStage) throws Exception {
        data = new Data(); // Supponiamo che Data abbia un costruttore adatto
        VBox root = new VBox(10);
        textArea = new TextArea();
        textArea.setEditable(false);

        TextField inputK = new TextField();
        inputK.setPromptText("Enter number of clusters");

        Button btnInitialize = new Button("Initialize Clustering");
        Button btnPerformClustering = new Button("Perform Clustering");
        Button btnSave = new Button("Save ClusterSet");

        btnInitialize.setOnAction(e -> initializeClustering(inputK.getText()));
        btnPerformClustering.setOnAction(e -> performClustering());
        btnSave.setOnAction(e -> saveClusterSet());

        root.getChildren().addAll(inputK, btnInitialize, btnPerformClustering, btnSave, textArea);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle("K-Means Clustering");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeClustering(String k) {
        try {
            int numClusters = Integer.parseInt(k);
            kmeans = new KMeansMiner(numClusters);
            textArea.setText("ClusterSet initialized with " + numClusters + " clusters.");
        } catch (NumberFormatException | OutOfRangeSampleSize e) {
            textArea.setText("Error: " + e.getMessage());
        }
    }

    private void performClustering() {
        if (kmeans != null && data != null) {
            try {
                int iterations = kmeans.kmeans(data);
                textArea.appendText("\nClustering completed in " + iterations + " iterations.\n");
                textArea.appendText(kmeans.getC().toString(data));
            } catch (OutOfRangeSampleSize e) {
                textArea.setText("Error during clustering: " + e.getMessage());
            }
        } else {
            textArea.setText("Please initialize the cluster set first.");
        }
    }

    private void saveClusterSet() {
        if (kmeans != null) {
            try {
                kmeans.salva("Save.dat");
                textArea.appendText("\nClusterSet saved successfully.");
            } catch (Exception e) {
                textArea.setText("Failed to save ClusterSet: " + e.getMessage());
            }
        } else {
            textArea.setText("No ClusterSet to save.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
