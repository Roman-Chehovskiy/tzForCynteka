import utils.Operation;
import utils.ReadAndWrite;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        List<String> readList = ReadAndWrite.readFromFile();
        List<String> resultList = Operation.sortedList(readList);
        ReadAndWrite.writerToFile(resultList);
    }
}
