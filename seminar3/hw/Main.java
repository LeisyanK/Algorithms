package seminar3.hw;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List list = new List();
        
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int value = r.nextInt(100);
            System.out.print(value + " ");
            list.addFirst(value);
        }
        System.out.println();

        list.print(list);
        System.out.println("разворот");
        list.reverse(list);
        list.print(list);

    }
    
}
