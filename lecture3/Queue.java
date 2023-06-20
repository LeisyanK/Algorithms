package lecture3;

public class Queue {    // для работы с очередью нужны head и tail, т.к. добавляем элемент в конец очереди, 
                        // вынимаем из начала, поэтому обычно используется ДВУСВЯЗНЫЙ список
    Node head;
    Node tail;

    // push будет добавлять новые элементы в начало очереди
    public void push(int value){
        Node node = new Node();  // создаем новую ноду, которую будем добавлять в связанный список
        node.value = value;      // задаем этой новой ноде значение, которое передали в функцию
        node.next = head;
        head.previous = node;
        head = node;
    }

    // pop извлекает элементы из начала очереди
    public Integer peek(){
        Integer result = null;
        if (tail != null) {
            result = tail.value;
            tail.previous.next = null;
            tail = tail.previous;
        }
        return result;
    }

    public class Node{
        int value;
        Node next;
        Node previous;
    }
    
}
