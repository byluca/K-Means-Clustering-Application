package data;

import java.io.Serializable;
import java.util.Set;

// La classe 'Tuple' implementa Serializable e rappresenta una tupla di elementi (Item).
public class Tuple implements Serializable {
   // Array di 'Item' che compone la tupla.
   Item[] tuple;

   // Costruttore che inizializza la tupla con una dimensione specificata.
   Tuple(int size) {
      tuple = new Item[size]; // Inizializza l'array di Item con la dimensione fornita.
   }

   // Restituisce la lunghezza della tupla.
   public int getLength() {
      return tuple.length;
   }

   // Restituisce l'elemento alla posizione 'i'.
   public Item get(int i) {
      return tuple[i];
   }

   // Aggiunge un Item 'c' alla posizione 'i' nell'array.
   void add(Item c, int i) {
      tuple[i] = c;
   }

   // Calcola e restituisce la distanza tra questa tupla e un'altra tupla 'obj'.
   public double getDistance(Tuple obj) {
      double distanza = 0;
      for (int i = 0; i < tuple.length; i++) {
         if (obj.get(i) != null) { // Controlla se l'elemento in 'obj' è non nullo.
            distanza += tuple[i].distance(obj.get(i).getValue()); // Aggiunge la distanza dall'elemento corrispondente in 'obj'.
         }
      }
      return distanza; // Restituisce la distanza totale.
   }

   // Calcola e restituisce la distanza media di questa tupla da un insieme di tuple indicizzate in 'clusteredData'.
   public double avgDistance(Data data, Set<Integer> clusteredData) {
      double sumD = 0.0; // Somma delle distanze.
      for (int i : clusteredData) { // Itera attraverso gli indici nell'insieme 'clusteredData'.
         double d = getDistance(data.getItemSet(i)); // Calcola la distanza dalla tupla corrispondente in 'data'.
         sumD += d; // Aggiunge alla somma delle distanze.
      }
      double p = sumD / clusteredData.size(); // Calcola la distanza media.
      return p; // Restituisce la distanza media.
   }
}
