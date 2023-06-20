package seminar3.hw;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List list = new List();
        
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int value = r.nextInt(100);
            // System.out.print(value + " ");
            list.addFirst(value);
        }
        // System.out.println();

        list.print();
        System.out.println();

        System.out.println("разворот:");
        list.reverse();
        list.print();
        System.out.println();

        // стэк
        System.out.println("разворот стэком:");
        list.reverseUsingStack();
        list.print();
        System.out.println();

        // рекурсия
        // Node head
        System.out.println("разворот рекурсией:");
        list.reverseRecursive();
        list.print();

    }
    
}
