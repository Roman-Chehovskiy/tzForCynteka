package utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Operation {

    private static List<String> listOne = new ArrayList<String>();
    private static List<String> listTwo = new ArrayList<String>();

    //делим входной список на два списка строк, которым необходимо найти соответствие
    private static void separation(List<String> initialList) {
        try {
            int n = Integer.parseInt(initialList.get(0));
            int j = Integer.parseInt(initialList.get(n + 1));
            for (int i = 1; i < (n + 1); i++) {
                listOne.add(initialList.get(i));
            }
            for (int i = (n + 2); i < initialList.size(); i++) {
                listTwo.add(initialList.get(i));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Входной файл не соответствует заданому формату");
        }
    }

    //формируем список который будем сортировать
    private static List<String> createSortList() {
        List<String> sortList = new ArrayList<>();
        int n = listOne.size() + listTwo.size();
        if (listOne.size() != listTwo.size()) {
            n = n + Math.abs(listOne.size() - listTwo.size());
            if (listOne.size() > listTwo.size()) {
                for (int i = 0; i < n - listTwo.size(); i++) {
                    listTwo.add("?");
                }
            } else {
                for (int i = 0; i < n - listOne.size(); i++) {
                    listOne.add("?");
                }
            }
        }
        for (int i = 0; i < n / 2; i++) {
            sortList.add(listOne.get(i));
            sortList.add(listTwo.get(i));
        }
        return sortList;
    }
//Сщртируем строки списка по признаку подобности
    private static List<String> sortList(List<String> sortList) {
        int i = 0;
        int j;
        double index;
        double indexMax;
        String str = "";
        while (i < sortList.size()) {
            j = 1;
            indexMax = 0;
            while (j < sortList.size()) {
                index = StringUtils.getJaroWinklerDistance(sortList.get(i), sortList.get(j));
                if (index > indexMax) {
                    str = sortList.get(i + 1);
                    sortList.set(i + 1, sortList.get(j));
                    sortList.set(j, str);
                    indexMax = index;
                }
                j = j + 2;
            }
            i = i + 2;
        }
        return sortList;
    }

    //создаем список строк, вид которых соответствует поставленной задаче
    private static List<String> createResultList(List<String> sortList) {
        List<String> resultList = new ArrayList<>();
        String str = "";
        int i = 0;
        while (i < sortList.size()) {
            if (sortList.get(i).equals("?")) {
                str = sortList.get(i + 1) + " : " + sortList.get(i);
            } else {
                str = sortList.get(i) + " : " + sortList.get(i + 1);
            }
            resultList.add(str);
            i = i + 2;
        }
        return resultList;
    }

    public static List<String> sortedList(List<String> stringList) {
        List<String> resultList = new ArrayList<>();
        separation(stringList);
        List<String> sortList = createSortList();
        sortList = sortList(sortList);
        resultList = createResultList(sortList);
        return resultList;
    }
}
