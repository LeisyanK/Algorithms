package seminar2;

public class Homework_sort {
    public static void main(String[] args) {
        int[] array = new int[]{
            4, 2, 52, 8, 17, 9, 22, 3, 6, 8, 
            5, 28, 1, 84, 11, 4, 6, 35, 81, 15,
            41, 65, 20, 8, 3, 26, 52, 6, 72, 45
        };

        piramidSort(array);                             // O(n*log n)

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
    public static void piramidSort(int[] array){        // O(n*log n)
        // Построение кучи (перегруппируем массив) - выносим самый большой элемент наверх, чтоб поставить в конец массива
                System.out.println("начало первого этапа");
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, array.length, i);
        }
                System.out.println("первый этап (просеивания) завершен. начинается второй этап");
        // Один за другим извлекаем элементы из кучи
        // Найденный самый большой элемент с верхней позиции перемещаем в конец массива
        for (int i = array.length - 1; i >= 0; i--) {
            // Перемещаем текущий корень в конец
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Вызываем процедуру heapify на уменьшенной куче
            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int heapSize, int rootIndex) {
        int largest = rootIndex;    // принимаем корень как наибольший элемент
        int leftChild = 2 * rootIndex + 1;  // левый ребенок
        int rightChild = 2 * rootIndex + 2;     // правый ребенок
                System.out.println("следующий шаг:");
                // System.out.println("   " + rootIndex);
                System.out.println("[" + rootIndex + "]" + array[rootIndex]);
                System.out.print(leftChild + "   "); 
                System.out.println(rightChild);
                // System.out.println("        [" + rootIndex + "]" + array[rootIndex]);
                // System.out.print("[" + leftChild + "]" + array[leftChild] +"    "); 
                // System.out.println("[" + rightChild + "]" + array[rightChild]);

        // Если левый дочерний элемент больше корня, то он становится largest
        if (leftChild < heapSize && array[leftChild] > array[largest]) {
            largest = leftChild;
        }
        // Если правый дочерний элемент больше, чем самый большой элемент на данный момент (больше родителя 
        // и левого), то он становится largest
        if (rightChild < heapSize && array[rightChild] > array[largest]) {
            largest = rightChild;
        }
        // Если самый большой элемент не корень
        if (largest != rootIndex) {
            int temp = array[rootIndex];
            array[rootIndex] = array[largest];
            array[largest] = temp;

                System.out.println("переставили:");
                System.out.println("[" + rootIndex + "]" + array[rootIndex]);
                // System.out.print("[" + leftChild + "]" + array[leftChild] +"    "); 
                // System.out.println("[" + rightChild + "]" + array[rightChild]);
                // System.out.println("   " + rootIndex);
                System.out.print(leftChild + "   "); 
                System.out.println(rightChild);

            // Рекурсивно преобразкем в двоичную кучу затронутое поддерево, т.е. если менялись местами
            // родитель и какой-то ребенок, то запускаем проверку для нижележащих элементов
                System.out.println("нижнее поддерево:");
            heapify(array, heapSize, largest);
        }

    }
}