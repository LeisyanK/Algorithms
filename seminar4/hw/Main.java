package seminar4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        RedBlackTree<Integer> tree = new RedBlackTree<>();
        System.out.println(tree.add(6));
        System.out.println(tree.add(3));
        System.out.println(tree.add(6));
        System.out.println(tree.add(10));
        System.out.println(tree.add(1));
        System.out.println(tree.add(8));
        System.out.println(tree.add(9));
        System.out.println(tree.add(15));
        System.out.println(tree.add(2));
        System.out.println(tree.add(18));
        System.out.println(tree.countNode());

        
        
        // final RedBlackTree tree = new RedBlackTree();
        // код с лекции
        // try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
        //     while (true) {
        //         try {
        //             int value = Integer.parseInt(reader.readLine());
        //             tree.add(value);
        //             System.out.println("finish");
        //         } catch (Exception ignored) {
        //             // TODO: handle exception
        //         }
                
        //     }
        // } catch (IOException e) {
        //     // TODO: handle exception
        //     throw new RuntimeException(e);
        // }

            // мой код
        // Scanner scanner = new Scanner(System.in);
        // System.out.print("Введите число или слово 'stop' для остановки ввода:");
        // String input = scanner.nextLine();
        // while (input != "stop") {
        //     int value = Integer.parseInt(input);
        //     tree.add(value);
        //     System.out.print("Введите число или слово 'stop' для остановки ввода:");
        //     input = scanner.nextLine();
        // }
        // scanner.close();

    }
}
