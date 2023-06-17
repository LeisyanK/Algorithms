package seminar1;

import java.util.Date;
import java.util.Scanner;

import javax.xml.crypto.Data;

public class Main {
    
    public static void main(String[] args) {
        task4();
    }


    // Необходимо написать алгоритм, считающий сумму всех чисел от 1 до N 
    public static void task1() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число N:");
        int n = sc.nextInt();
        int sum = 0;
        for (int i = 1; i <= n; i++) {  // O(n)
            sum += i;
        }
        System.out.println(sum);
        sc.close();        
    }

    // Написать алгоритм поиска простых чисел в диапазоне от 1 до N
    public static void task2() {
        boolean flag;
        for (int i = 2; i < 1000; i++) {    // O(n^2)
            flag = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    flag = false;
                }
            }
            if (flag) {
                System.out.print(i + " ");
            }
        }
    }

    // Необходимо написать алгоритм поиска всех доступных комбинаций (посчитать количество)
    // для количества кубиков К с количеством граней N
    public static void task3() { // O(2^n) - экспоненциальная зависимость: чем больше кубиков, тем в разы больше 
                                    //операций подсчета, в данном случае
                                    // для двух кубиков 36 комбинаций
                                    // для трех - 216
                                    // для четырех - 1296
                                    // для пяти - 7776
                                    // т.е. при увеличении входного параметра (количества кубиков) на 1, количество операции увеличивается в разы
        int count = 5;  // количество кубиков
        int faces = 6;  // количество граней
        System.out.println(recursiveCount(1, count, faces));
    }

    public static int recursiveCount(int countNow, int maxCount, int faces) {
        int count = 0;
        //System.out.println(countNow + "-й кубик: ");
        for (int i = 0; i < faces; i++) {
            if (countNow == maxCount) {
                count++;
            }
            else {
                //System.out.print(countNow + "-й кубик: " + i + "-ая грань ");
                count += recursiveCount(countNow + 1, maxCount, faces);
            }
        }
        //System.out.println();
        return count;

        // Решение: сколько кубиков, столько циклов, вложенных в друг друга
        // for (int i = 0; i < faces; i++) {
        //     for (int j = 0; j < faces; j++) {
        //         for (int j2 = 0; j2 < faces; j2++) {
        //             for (int k = 0; k < faces; k++) {
        //                 count ++;
        //             }
        //         }
                
        //     }
        // }
        // return count;
    }

    // Написать алгоритм поиска нужного числа последовательности Фибоначчи.
    // Считать, что 1 и 2 значения последовательности равны 1.
    public static void task4() {
        int n = 40;
        Date startDate = new Date();
        System.out.println(fb(n));
        Date endDate = new Date();
        System.out.print("Время решения через рекурсию: ");
        System.out.println( + endDate.getTime() - startDate.getTime());

        Date startDate2 = new Date();
        System.out.println(fb2(n));
        Date endDate2 = new Date();
        System.out.print("Время решения линейного (1 цикл): ");
        System.out.println(endDate2.getTime() - startDate2.getTime());
    }

    public static int fb(int num) { // сложность алгоритма - экспоненциальная
        // On = On-1 + On-2
        //System.out.println("Новый запуск функции");
        if (num <= 2) {
            return 1;
        }
        else {
            return fb(num-1) + fb(num-2);
        }
    }

    public static int fb2(int num) {
        if (num <=2) {
            return 1;
        }
        int [] numbers = new int[num];
        numbers[0] = 1;
        numbers[1] = 1;
        for (int i = 2; i < numbers.length; i++) {
            numbers[i] = numbers[i-1] + numbers[i-2];
        }
        return numbers[num-1];
    }
}
