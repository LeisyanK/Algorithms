package seminar4;

public class Main {

    public static void main(String[] args) {
        // работа с деревом
        // Tree<Integer> tree = new Tree<>();
        // tree.add(5);
        // tree.add(4);
        // tree.add(6);
        // // tree.add(2);
        // // tree.add(9);
        // System.out.println(tree);
        // tree.add(5);

        // System.out.println(tree.contains(7));
        // System.out.println(tree.contains(4));

        
        // работа с хэш-таблицей
        HashTable<Integer, Integer> hashTable = new HashTable<>();
        System.out.println(hashTable.put(1, 10));
        System.out.println(hashTable.put(2, 20));
        System.out.println(hashTable.put(3, 30));

    }
}
