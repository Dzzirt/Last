import java.io.*;
import java.sql.SQLOutput;

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

            int firstWordPos = -1;
            int minDistance = -1;
            int maxDistance = -1;

            String firstWord = null;
            String secondWord = null;

            long start = System.currentTimeMillis();
            //Read file by line in memory usage reasons
            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] words = line.split(" ");
                for (int i = 0; i < words.length; i++) {
                    // Define the words order
                    if (firstWord == null && secondWord == null) {
                        if (words[i].equals(oneWord)) {
                            firstWord = oneWord;
                            secondWord = anotherWord;
                        } else if (words[i].equals(anotherWord)) {
                            firstWord = anotherWord;
                            secondWord = oneWord;
                        }
                        firstWordPos = i;
                    }
                    // Calculate min and max distance if second word has found
                    if (words[i].equals(secondWord)) {
                        if (minDistance == NONE) {
                            minDistance = i - firstWordPos - 1;
                        } else {
                            maxDistance = i - firstWordPos - 1;
                        }
                    }
                }
                // Max and min distance are equal if second word isn't repeat
                if (maxDistance == NONE) {
                    maxDistance = minDistance;
                }
            }


            if (minDistance == NONE) {
                System.out.println("Min distance: NONE");
                System.out.println("Max distance: NONE");
            } else {
                System.out.println("Min distance: " + minDistance);
                System.out.println("Max distance: " + maxDistance);
            }

            System.out.println(System.currentTimeMillis() - start);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
