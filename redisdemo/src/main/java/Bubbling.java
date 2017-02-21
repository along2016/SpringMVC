/**
 * Created by alion on 2017/2/20.
 */
public class Bubbling {

    public static void main(String[] args) {
        String[] language = new String[]{"Java", "Scala", "C++", "Groovy", "C"};

        String temp = language[0];
        for(int i = 0; i < language.length - 1; i++){
            for(int j = 0; j < language.length - 1 - i; j++){
                if(language[j].compareTo(language[j+1]) > 0){
                    temp = language[j];
                    language[j] = language[j+1];
                    language[j+1] = temp;
                }
            }
        }

        for(int i = 0; i < language.length; i++){
            System.out.println(language[i]);
        }
    }
}
