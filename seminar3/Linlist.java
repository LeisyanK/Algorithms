package seminar3;

public class Linlist {
    private Node head;

    public void addFirst(int value){    // добавляем значение value в начало списка
        Node node = new Node();
        node.value = value;
        if (head != null) {
            node.next = head;
        }
        head = node;    // если список пуст
    }

    public void removeFirst(){
        if (head == null) {
            System.out.println("Нельзя удалить элемент из пустого списка");
        } else {
            head = head.next;
        }
    }

    public void addLast(int value){
        Node node = new Node();
        node.value = value;
        if (head == null) {
            head = node;
        } else {
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = node;

        }
    }

    public void removeLast(){
        if (head == null) {
            System.out.println("Нельзя удалить элемент из пустого списка");
        } else {
            Node node = head;
            while (node.next != null) {
                if (node.next.next == null) {
                    node.next = null;
                    return;
                }
                node = node.next;
            }
            head = null;
        }
    }

    // метод проверки наличия значения в списке
    public boolean contains(int value){
        Node node = head;
        int index = 0;
        while (node != null) {
            if (node.value == value) {
                System.out.println("Индекс элемента " + value + " равен " + index);
                return true;
            }
            node = node.next;
            index++;
        }
        return false;
    }

    // функция вывода списка
    public void print(){
        Node node = head;
        while (node != null) {
            System.out.print(node.value + " -> ");
            node = node.next;
        }
    }

    private class Node{
        private int value;
        private Node next;
    }
    
}
