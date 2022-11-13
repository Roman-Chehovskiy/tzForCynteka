package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWrite {

    private static List<String> result = new ArrayList<String>();

    public static List<String> readFromFile() {
        try {
            File file = new File("src/main/resources/input.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void writerToFile(List<String> finalSortList) {

        try {
            File file = new File("src/main/resources/output.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter writer = new PrintWriter(bufferedWriter);
            for(String a : finalSortList) {
                writer.println(a);
            }
            writer.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
