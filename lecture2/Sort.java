package lecture2;

public class Sort{

    public static void main(String[] args) {
        int[] array = new int[]{
            4, 2, 5, 8, 1, 9, 2, 3, 6, 8, 5
        };

        //bubbleSort(array);
        // directSort(array);
        //insertSort(array);
        //quickSort(array, 0, array.length-1);

        // бинарная куча
        piramidSort(array);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
    
    // пузырьковая сортировка (сравниваем по два элемента)
    public static void bubbleSort(int[] array) {        // O(n^2)
        boolean finish;
        do {
            finish = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i+1]) {
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    finish = false;
                }
            }
        } while (!finish);
    }

    // сортировка выбором (начиная с первого элемента, находим минимальный элемент правее текущего и меняем их местами)
    public static void directSort(int[] array) {        // O(n^2)
        for (int i = 0; i < array.length - 1; i++) {
            int minPosition = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minPosition]) {
                    minPosition = j;
                };
            }
            if (minPosition != i) {
                int temp = array[i];
                array[i] = array[minPosition];
                array[minPosition] = temp;
            }
        }

        // boolean finish;
        // int i = 0;
        // do {
        //     finish = true;
        //     for (int j = i+1; j < array.length; j++) {
                
        //     }

        // } while (!finish);
    }

    // сортировка вставками (сравниваем текущий элемент со всеми и меняем их местами, если какой-то меньше текущего)
    public static void insertSort(int[] array) {        // O(n^2)
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    // быстрая сортировка (делим массив пивотом и если справа от пивота есть элемент меньше пивота, то меняем его на элемент слева, который больше пивота)
    // сложность алгоритма O(n log n), максимальная сложность O(n^2) - когда элементы изначально оказались отсортированы и пивотом выбирается первый элемент
    // 
    public static void quickSort(int[] array, int startPosition, int endPosition) {     // O(n*log n)
        int leftPosition = startPosition;
        int rightPosition = endPosition;
        int pivot = array[(endPosition - startPosition) / 2 + startPosition];
        do{
            while (array[leftPosition] < pivot) {
                leftPosition++;
            }
            while (array[rightPosition] > pivot) {
                rightPosition--;
            }
            if (leftPosition <= rightPosition) {
                if (leftPosition < rightPosition) {
                    int temp = array[leftPosition];
                    array[leftPosition] = array[rightPosition];
                    array[rightPosition] = temp;
                }
                leftPosition++;
                rightPosition--;
            }
        }while(leftPosition <= rightPosition);

        if (leftPosition < endPosition) {
            quickSort(array, leftPosition, endPosition);
        }
        if (startPosition < rightPosition) {
            quickSort(array, startPosition, rightPosition);
        }
    }

    // пирамидальная сортировка (кучей)
    public static void piramidSort(int[] array){        // O(n*log n)
        // Построение кучи (перегруппируем массив) - выносим самый большой элемент наверх
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, array.length, i);
        }

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

            // Рекурсивно преобразкем в двоичную кучу затронутое поддерево, т.е. если менялись местами
            // родитель и какой-то ребенок, то запускаем проверку для нижележащих элементов
            heapify(array, heapSize, largest);
        }

    }
}