import java.io.*;
import java.util.*;

public class Twist {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Menue:");
            System.out.println("[1] - Wort twisten\n" + "[2] - Wort enttwisten");
            while (true) {
                System.out.print("\nEingabe: ");
                String text = input.next();
                if (text.equals("1")) {
                    System.out.print("Bitte geben Sie das zu twistende Wort ein: ");
                    text = input.next();
                    twisten(text);
                    break;
                } else if (text.equals("2")) {
                    System.out.print("Bitte geben Sie das zu enttwistende Wort ein: ");
                    text = input.next();
                    enttwisten(text);
                    break;
                } else {
                    System.out.println("Das war eine falsche Eingabe. Bitte wiederhohlen Sie die Eingabe.");
                }
            }
        } while (true);
    }

    public static void twisten(String text) {
        Random rand = new Random();
        char[] buchstabenListe = text.toCharArray();
        int[] zahlen = new int[buchstabenListe.length];
        System.out.print(buchstabenListe[0]);
        int laenge = buchstabenListe.length;
        for (int i = 0; i < laenge - 2; i++) {
            int zahl = rand.nextInt(buchstabenListe.length);
            while (zahl == 0 || zahl == laenge - 1 || zahl == zahlen[zahl]) {
                zahl = rand.nextInt(buchstabenListe.length);
            }
            zahlen[zahl] += zahl;
            System.out.print(buchstabenListe[zahl]);
        }
        System.out.println(buchstabenListe[buchstabenListe.length - 1]+"\n");
    }
    public static void enttwisten(String text) throws IOException {
        Random rand = new Random();
        String file = "C:\\Users\\tinot\\IdeaProjects\\lf05-twist-TinoTripkeFIC3\\src\\main\\resources\\woerterliste.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String currentLine = reader.readLine();
        char[] buchstabenListe = text.toCharArray();
        ArrayList<String> woerter = new ArrayList<>();
        while (currentLine != null) {
            builder.append(currentLine);
            String wort = String.valueOf(builder);
            String anfang = String.valueOf(buchstabenListe[0]);
            if (wort.startsWith(anfang) && wort.length() == text.length() && wort.charAt(wort.length() - 1) == text.charAt(text.length() - 1)) {
                woerter.add(wort);
            }
            builder.replace(0, currentLine.length(), "");
            currentLine = reader.readLine();
        }
        int match = 0, count = 0,fehler = 0;
        //Listenwörter durchlaufen
        for (int z = 0; z < woerter.size(); z++) {
            //Buchstaben einzeln durchlaufen
            for (int j = 0; j < buchstabenListe.length; j++) {
                char[] verlgeich = woerter.get(z)
                        .toCharArray();
                //Jeden Buchstaben eines Wortes aus der Liste auf vorkommen überprüfen
                for (int i = 0; i < text.length(); i++) {
                    if (verlgeich[i] == buchstabenListe[j]) {
                        count += 1;
                        break;
                    }
                }
            }
            if (count==0){fehler+=1;}
            if (count == buchstabenListe.length&&fehler == 0) {
                System.out.println(text + " entwistet: " + woerter.get(z)+"\n");match = +1;
            }
            count=0;
        }if (match == 0) {
            System.out.print("Das Wort kann nicht enttwisted werden, weil es nicht in der Liste vorhanden ist.\n");
        }
        reader.close();
    }
}
