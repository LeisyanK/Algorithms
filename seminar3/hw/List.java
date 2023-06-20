package seminar3.hw;

public class List {
    //Node node = new Node();
    private Node head;

    // разворот односвязного списка
    public void reverse(List list){
        Node node = head;
        System.out.println(node.value);
        Node nextNode = node.next;
        Node nextNode2 = nextNode.next;
        head.next = null;
        // while (node.next.next != null) {
        while (nextNode2 != null) {
            // node.next = node;
            // node.next.next = node.next;
            // node = node.next;
            // System.out.print(node.value + " ");
            nextNode.next = node;

            node = nextNode;
            nextNode = nextNode2;
            nextNode2 = nextNode.next;
        }
        // head.next = null;
        head = nextNode;
        // head.next = null;
        // node = head;
    }

    public void addFirst(int value){      
        Node node = new Node();
        node.value = value;
        if (head != null){
            node.next = head;
        }
        head = node;
    }

    // функция вывода списка
    public void print(List list){
        if (head != null) {
            Node node = head;
            while (node != null){
                System.out.print(node.value + " -> ");
                node = node.next;
            }
        } else {
            System.out.println("Список пуст");
        }
    }

    private class Node{
        private int value;
        private Node next;
    }
    
}
