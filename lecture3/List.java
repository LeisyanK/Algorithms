package lecture3;

public class List {             // односвязный список
    Node head;      // односвязный список может не иметь tail, может иметь. мы будем работать со списком без tail,
                    // т.к. односвязный список с конца обойти невозможно, т.к. мы всегда ссылаемся только на следующий элемент

    public void revert(){
        // если в списке только head, то разворачивать нам ничего не нужно
        // если в списке нет даже head (пустой список), то разворачивать нечего
        if (head != null) {
            Node temp = head;       // сохраняем информацию о head, чтобы потом его ссылку на следующий элемент удалить (он встанет в конце списка, и ссылки у него не будет)
            revert(head.next, head);
            temp.next = null;       // удаляем ссылку на следующий элемент, т.к. он оказался в конце списка и на след. элемент не ссылается
            // tail = head;         // если у списка был tail ?????
        }
    }
    // разворот решается рекурсией проще всего
    // т.к. эта функция не очень красивая с точки зрения пользовательского интерфейса, поэтому
    // мы ее делаем приватной и создаем другую публичную функцию, которая запустит текущую функцию
    private void revert(Node currentNode, Node previousNode){
        if (currentNode.next == null) { // если это последняя нода, то она должна стать head
            head = currentNode;
        } else {
            revert(currentNode.next, currentNode);
        }
        currentNode.next = previousNode;    // операция замены ссылок
        // previousNode.next = null;        // эту строку кода заменили строками 11 и 13
    }
    
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

    public class Node{
        int value;
        Node next;
    }
}
