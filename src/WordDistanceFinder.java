import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

//Elapsed time - 1 hour

public class WordDistanceFinder {

    public static final int NONE = -1;
    public static final String TEXT_FILE_NAME = "test_sample.txt";

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            //Request for source words
            System.out.println("Input first word: ");
            String oneWord = bufferedReader.readLine();
            System.out.println("Input second word: ");
            String anotherWord = bufferedReader.readLine();

            BufferedReader fileReader = new BufferedReader(new FileReader(TEXT_FILE_NAME));

            int minDistance = -1;
            int maxDistance = -1;

            ArrayList<Integer> oneWordIndexes = new ArrayList<>();
            ArrayList<Integer> anotherWordIndexes = new ArrayList<>();

            //Read file by line in memory usage reasons
            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] words = line.split(" ");
                for (int i = 0; i < words.length; i++) {
                    if (words[i].equals(oneWord)) {
                        oneWordIndexes.add(i);
                    } else if (words[i].equals(anotherWord)) {
                        anotherWordIndexes.add(i);
                    }
                }
            }

            for (Integer oneIndex : oneWordIndexes) {
                for (Integer anotherIndex : anotherWordIndexes) {
                    int distance = Math.abs(anotherIndex - oneIndex) - 1;
                    if (minDistance < 0 && maxDistance < 0) {
                        minDistance = distance;
                        maxDistance = distance;
                    }

                    if (distance < minDistance) {
                        minDistance = distance;
                    }
                    if (distance > maxDistance) {
                        maxDistance = distance;
                    }
                }
            }


            if (minDistance == NONE) {
                System.out.println("Min distance: NONE");
                System.out.println("Max distance: NONE");
            } else {
                System.out.println("Min distance: " + minDistance);
                System.out.println("Max distance: " + maxDistance);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
