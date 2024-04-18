package keyboardinput;

import java.io.*;
import java.util.*;

// La classe Keyboard semplifica l'input da tastiera, gestendo parsing, conversioni e eccezioni.
public class Keyboard {
    // ************* Sezione di gestione degli errori **************************

    private static boolean printErrors = true; // Se vero, stampa gli errori a schermo
    private static int errorCount = 0; // Contatore degli errori avvenuti durante l'input

    // Restituisce il conteggio corrente degli errori.
    public static int getErrorCount() {
        return errorCount;
    }

    // Reimposta il conteggio degli errori a zero.
    public static void resetErrorCount(int count) {
        errorCount = 0;
    }

    // Restituisce un booleano che indica se gli errori di input sono attualmente stampati sullo standard output.
    public static boolean getPrintErrors() {
        return printErrors;
    }

    // Imposta un booleano che indica se gli errori di input debbano essere stampati sullo standard output.
    public static void setPrintErrors(boolean flag) {
        printErrors = flag;
    }

    // Incrementa il conteggio degli errori e stampa il messaggio di errore se appropriato.
    private static void error(String str) {
        errorCount++;
        if (printErrors)
            System.out.println(str);
    }

    // ************* Sezione del flusso di input tokenizzato ******************

    private static String current_token = null;
    private static StringTokenizer reader;
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    // Ottiene il prossimo token di input assumendo che possa essere su linee di input successive.
    private static String getNextToken() {
        return getNextToken(true);
    }

    // Ottiene il prossimo token di input, che potrebbe già essere stato letto.
    private static String getNextToken(boolean skip) {
        String token;

        if (current_token == null)
            token = getNextInputToken(skip);
        else {
            token = current_token;
            current_token = null;
        }

        return token;
    }

    // Ottiene il prossimo token dall'input, che può provenire dalla linea di input corrente o da una successiva.
    // Il parametro determina se le linee successive vengono utilizzate.
    private static String getNextInputToken(boolean skip) {
        final String delimiters = " \t\n\r\f";
        String token = null;

        try {
            if (reader == null)
                reader = new StringTokenizer(in.readLine(), delimiters, true);

            while (token == null || ((delimiters.indexOf(token) >= 0) && skip)) {
                while (!reader.hasMoreTokens())
                    reader = new StringTokenizer(in.readLine(), delimiters, true);

                token = reader.nextToken();
            }
        } catch (Exception exception) {
            token = null;
        }

        return token;
    }

    // Restituisce true se non ci sono più token da leggere sulla linea di input corrente.
    public static boolean endOfLine() {
        return !reader.hasMoreTokens();
    }

    // ************* Sezione di lettura *********************************

    // Restituisce una stringa letta dallo standard input.
    public static String readString() {
        String str;

        try {
            str = getNextToken(false);
            while (!endOfLine()) {
                str = str + getNextToken(false);
            }
        } catch (Exception exception) {
            error("Errore nella lettura dei dati String, valore null restituito.");
            str = null;
        }
        return str;
    }

    // Restituisce una sottostringa delimitata da spazi (una parola) letta dallo standard input.
    public static String readWord() {
        String token;
        try {
            token = getNextToken();
        } catch (Exception exception) {
            error("Errore nella lettura dei dati String, valore null restituito.");
            token = null;
        }
        return token;
    }

    // Restituisce un booleano letto dallo standard input.
    public static boolean readBoolean() {
        String token = getNextToken();
        boolean bool;
        try {
            if (token.toLowerCase().equals("true"))
                bool = true;
            else if (token.toLowerCase().equals("false"))
                bool = false;
            else {
                error("Errore nella lettura dei dati boolean, valore false restituito.");
                bool = false;
            }
        } catch (Exception exception) {
            error("Errore nella lettura dei dati boolean, valore false restituito.");
            bool = false;
        }
        return bool;
    }

    // Restituisce un carattere letto dallo standard input.
    public static char readChar() {
        String token = getNextToken(false);
        char value;
        try {
            if (token.length() > 1) {
                current_token = token.substring(1, token.length());
            } else
                current_token = null;
            value = token.charAt(0);
        } catch (Exception exception) {
            error("Errore nella lettura dei dati char, valore MIN_VALUE restituito.");
            value = Character.MIN_VALUE;
        }

        return value;
    }

    // Restituisce un intero letto dallo standard input.
    public static int readInt() {
        String token = getNextToken();
        int value;
        try {
            value = Integer.parseInt(token);
        } catch (Exception exception) {
            error("Errore nella lettura dei dati int, valore MIN_VALUE restituito.");
            value = Integer.MIN_VALUE;
        }
        return value;
    }

    // Restituisce un intero lungo letto dallo standard input.
    public static long readLong() {
        String token = getNextToken();
        long value;
        try {
            value = Long.parseLong(token);
        } catch (Exception exception) {
            error("Errore nella lettura dei dati long, valore MIN_VALUE restituito.");
            value = Long.MIN_VALUE;
        }
        return value;
    }

    // Restituisce un float letto dallo standard input.
    public static float readFloat() {
        String token = getNextToken();
        float value;
        try {
            value = Float.parseFloat(token);
        } catch (Exception exception) {
            error("Errore nella lettura dei dati float, valore NaN restituito.");
            value = Float.NaN;
        }
        return value;
    }

    // Restituisce un doppio letto dallo standard input.
    public static double readDouble() {
        String token = getNextToken();
        double value;
        try {
            value = Double.parseDouble(token);
        } catch (Exception exception) {
            error("Errore nella lettura dei dati double, valore NaN restituito.");
            value = Double.NaN;
        }
        return value;
    }
}
