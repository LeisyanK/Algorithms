package lecture3;

public class List2 {            // двусвязный список
    Node head;
    Node tail;

    public Node find(int value){        // O(n)  поиск элемента
        Node currentNode = head;
        while(currentNode != null){
            if (currentNode.value == value) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    public void add(int value){     // добавление нового элемента в конец списка  O(1)
        Node node = new Node(); // создаем новую ноду, которую будем добавлять в связанный список
        node.value = value;     // задаем этой новой ноде значение, которое передали в функцию
        if (head == null){      // если список был пустым, то новая нода станет head-ом и tail-ом. 
            head = node;        // Мы не будем рассматривать ситуацию, когда head-а нет, а tail есть (в таком случае используют доп проверку).
            tail = node;
        }else{           // если список был не пуст, то добавляем новую ноду в конец списка
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
    }

    // добавление элемента в середину списка. сначала с помощью функции find находим элемент (Node node), 
    // после которого нужно вставить новый элемент          O(1)
    public void add(int value, Node node){      // здесь node - это элемент списка, после которого нужно вставить новый элемент
                                    // node - это текущая нода, после которой добавляем новую ноду
        Node next = node.next;      // будем оперировать текущей нодой и следующей (между ними вставим новую ноду)
        Node newNode = new Node();  // создаем новую ноду, которую будем добавлять в связанный список
        newNode.value = value;      // задаем этой новой ноде значение, которое передали в функцию
        node.next = newNode;        // берем текущую ноду и говорим, что ее след значение - это новая нода
        newNode.previous = node;    // а предыдущей для новой ноды будет текущая
        if (next == null){          // если приходится вставить новую ноду после последнего элемента списка 
            tail = newNode;         // то tail-ом теперь будет новая добавленная нода
        }else{
            next.previous = newNode;    // предыдущим элементом для следующего теперь будет новая нода
            newNode.next = next;        // а новая нода будет ссылаться на следующую ноду
        }
    }

    // удаление элемента
    public void delete(Node node) {
        Node previous = node.previous;
        Node next = node.next;
        if (previous == null){      // если удаляем первый элемент(у него нет previous),
            next.previous = null;   // то после удаления текущего(head) у следующего элемента не будет предыдущего
            head = next;            // и этот следующий станет head
        } else {
            if (next == null) {         // удаляем последний элемент (у него нет next)
                previous.next = null;   // предыдущий элемент становится последним, после него нет элементов(т.е.null)
                tail = previous;        // и этот предыдущий становится tail
            } else {
                previous.next = next;
                next.previous = previous;
            }
        } 
    }

    // разворот двусвязного списка
    public void revert(){
        Node currentNode = head;
        while (currentNode != null){
            Node next = currentNode.next;
            Node previous = currentNode.previous;
            currentNode.next = previous;
            currentNode.previous = next;
            if (previous == null) {
                tail = currentNode;
            }
            if (next == null) {
                head = currentNode;
            }
            currentNode = next;
        }
    }

    public class Node{
        int value;
        Node next;

        Node previous;
    }    
}
