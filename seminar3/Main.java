package seminar3;

public class Main {
    public static void main(String[] args) {
        Linlist list = new Linlist();
        // list.removeFirst();
        // list.addFirst(3);
        // list.addFirst(2);
        // list.addFirst(1);
        // list.print();
        // System.out.println();
        // list.removeFirst();
        // list.print();

        // list.addLast(1);
        // list.print();
        // System.out.println();
        // list.addLast(2);
        // list.addLast(3);
        // list.print();
        // System.out.println();
        // list.addFirst(0);
        // list.print();
        // System.out.println();
        // list.removeFirst();
        // list.print();

        // list.removeLast();
        // list.addFirst(1);
        // list.print();
        // System.out.println();
        // list.addFirst(0);
        // list.print();
        // System.out.println();
        // list.addLast(2);
        // list.print();
        // System.out.println();
        // list.removeLast();
        // list.print();
        // System.out.println();
        // list.addLast(3);
        // list.print();
        // System.out.println();

        list.addFirst(1);
        list.addFirst(0);
        list.addLast(2);
        list.addLast(3);
        list.print();
        System.out.println();
        if (!list.contains(2)) {
            System.out.println("Элемента с таким значением не существует");
        }
    }
    
}
