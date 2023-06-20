package lecture3;

public class Stek {     // для работы со стеком нужен только head, поэтому обычно используется ОДНОСВЯЗНЫЙ список
    Node head;

    // push будет добавлять новые элменты в начало стека
    public void push(int value){
        Node node = new Node();  // создаем новую ноду, которую будем добавлять в связанный список
        node.value = value;      // задаем этой новой ноде значение, которое передали в функцию
        node.next = head;
        head = node;
    }

    // pop извлекает элементы из начала стека
    public Integer pop(){
        Integer result = null;
        if (head != null) {
            result = head.value;
            head = head.next;
        }
        return result;
    }

    public class Node{
        int value;
        Node next;
    }
    
}
