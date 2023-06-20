package lecture2;

public class Find {
    public static void main(String[] args) {
        int[] array = new int[]{
            1, 2, 2, 3, 4, 5, 5, 6, 8, 8, 9
        };

        System.out.println(simpleSearch(array, 5));
        System.out.println(binarySearch(array, 4, 0, array.length));
    }

    // поиск путем перебора всех элементов  // O(n)
    public static int simpleSearch(int[] array, int search) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == search) {
                return i;
            }
        }
        return -1;
    }

    // бинарный поиск по отсортированному массиву  // O(log n)
    public static int binarySearch(int[] array, int search, int leftPosition, int rightPosition) {
        int center;
        if (leftPosition > rightPosition) {
            return -1; // не нашлось
        }
        else {
            center = (rightPosition - leftPosition) / 2 + leftPosition;
            //System.out.println("center = " + center);
            if (array[center] == search) {
                return center;
            }
            else if (array[center] > search) {
                return binarySearch(array, search, leftPosition, center - 1);
            }
            else {
                return binarySearch(array, search, center + 1, rightPosition);
            }
        }
    }
}
