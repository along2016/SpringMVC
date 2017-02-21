/**
 * Created by alion on 2017/2/21 0021.
 */
public class QicukSort {
    public static void main(String[] args) {
        String[] language = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
        sort(language, 0, language.length - 1);

        for (int i = 0; i < language.length; i++){
            System.out.println(language[i]);
        }
    }

    public static int getMiddle(String[] languages, int start, int end){
        String key = languages[start];
        while (start < end){
            while (start < end && languages[end].compareTo(key) >= 0){
                end--;
            }
            languages[start] = languages[end];
            while (start < end && languages[start].compareTo(key) <= 0){
                start++;
            }
            languages[end] = languages[start];
        }
        languages[start] = key;
        return start;
    }

    public static void sort(String[] languages, int start, int end){
        if(start >= end){
            return;
        }

        int middle = getMiddle(languages, start, end);
        sort(languages, start, middle - 1);
        sort(languages, middle + 1, end);
    }
}
