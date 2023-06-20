package seminar3.hw;

import java.util.ArrayDeque;
import java.util.Deque;

public class List {
    //Node node = new Node();
    private Node head;

    // разворот односвязного списка в цикле
    public void reverse(){
        Node current = head;
        // System.out.println(curNode.value);
        Node previous = null;
        while (current != null) {
            Node tmp = current.next;
            current.next = previous;
            previous = current;
            current = tmp;
        }
        head = previous;
    }

    // разворот односвязного списка рекурсивно
    // public void reverseRecursive(List list, Node node){
    public void reverseRecursive(){
        // reverseRecursive(head, head.next);
        if (head != null) {
            reverseRecursive(head.next, head);
        }
    }
    private void reverseRecursive(Node current, Node previuos){
        if (current.next != null){
            reverseRecursive(current.next, current);
        }
         else {
            head = current;
        }
        current.next = previuos;
        previuos.next = null;
    }

    public void reverseUsingStack(){
        Deque<Node> stack = new ArrayDeque<>();
        Node current = head;
        // System.out.println("head: " + head.value);
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        
        Node result = stack.peek();
        // System.out.print(result.value + " ");

        current = result;
        while (!stack.isEmpty()) {
            current.next = stack.pop();
            // System.out.print(current.next.value + " ");
            current = current.next;
            current.next = null;
        }
        head = result;
        // return result;
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
    public void print(){
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
