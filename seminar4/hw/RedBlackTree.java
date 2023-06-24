package seminar4.hw;
/* Дерево представляет собой односвязный список, где родитель имеет сcылки на своих детей */
/**
 * Красно-черное дерево имеет следующие критерии:
• Каждая нода имеет цвет (красный или черный)
• Корень дерева всегда черный
• Новая нода всегда красная
• Красные ноды могут быть только левым ребенком
• У краной ноды все дети черного цвета

Соответственно, чтобы данные условия выполнялись, после добавления элемента в дерево необходимо произвести 
балансировку, благодаря которой все критерии выше станут валидными. Для балансировки существует 3 операции – 
левый малый поворот, правый малый поворот и смена цвета.
 */

import java.util.ArrayList;
import java.util.List;

public class RedBlackTree <V extends Comparable<V>>{
    private Node root;

    /** 
     * Добавление значения value в дерево (простое добавление без ребалансировки)
     * @param value значение для добавления
    */
    public void addSimple(V value){ // с семинара
        Node node = root;
        Node newNode = new Node();
        newNode.value = value;
        if (root == null) {
            root = newNode;
        } else {
            if (node.value.compareTo(value) > 0) {
                node.leftChild = newNode;
            } else {
                if (node.value.compareTo(value) < 0) {
                    node.rightChild = newNode;
                } else {
                    System.out.println("Такой элемент уже есть");
                }
                
            }
        }
    }

    /**
     * Добавление значения в дерево
     * @param value значение для добавления
     * @return результат добавления (true или false)
     */
    public boolean add(V value){
        if (root != null) {
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;
        } else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }

    /**
     * Добавление ноды в дерево, если такой не существует
     * @param node родитель 
     * @param value значение ноды для добавления
     * @return результат добавления
     */
    private boolean addNode(Node node, V value){
        //if (node.value == value) {  // если такая нода уже есть,
        if (node.value.compareTo(value) == 0) {
            return false;           // то не сможем ее создать повторно
        } else {
            if (node.value.compareTo(value) > 0) {   // если значение нашей ноды больше, чем новой ноды
            // if (node.value > value) {
                if (node.leftChild != null) {   // и левый ребенок существует,
                    boolean result = addNode(node.leftChild, value);    // то запускаем рекурсию вглубь по левому ребенку 
                    node.leftChild = rebalance(node.leftChild);         // и проверяем, можем ли мы создать ноду там
                    return result;
                } else {    // если левого ребенка не существует, то мы считаем, что нашли подходящее место для 
                    node.leftChild = new Node();        // подстановки новой ноды: генерируем новую ноду, 
                    node.leftChild.color = Color.RED;   // присваиваем ей красный цвет
                    node.leftChild.value = value;       // присваиваем ей значение, которое хотели добавить
                    return true;    // для КЧД есть характерный признак: все новые ноды при создании получают красный цвет
                }
                
            } else {    // для правого ребенка все аналогично. Создается правый ребенок, если новая создаваемая
                if (node.rightChild != null) {  //  нода больше родителя
                    boolean result = addNode(node.rightChild, value);
                    node.rightChild = rebalance(node.rightChild);
                    return result;
                } else {
                    node.rightChild = new Node();
                    node.rightChild.color = Color.RED;
                    node.rightChild.value = value;
                    return true;
                }
            }
        }
    }

    /**
     * Ребалансировка дерева после добавления элемента
     * @param node родитель?
     * @return родителя после ребалансировки
     */
    private Node rebalance(Node node){  // после правостороннего поворота красная нода перемещается справа налево - это валидная ситуация
        Node result = node;             // а после левостороннего поворота красная нода перемещается слева направо и 
        boolean needRebalance;          // становится правым ребенком, что недопустимо для левостороннего красно-черного дерева
        do {                            // левосторонний поворот всегда будет сопровождаться сменой цвета
            needRebalance = false;
            if (result.rightChild != null && result.rightChild.color == Color.RED &&
                    (result.leftChild == null || result.leftChild.color == Color.BLACK)) {
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.RED &&
                    result.leftChild.leftChild != null && result.leftChild.leftChild.color == Color.RED) {
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.RED &&
                    // result.rightChild != null || result.rightChild.color == Color.RED) { // в лекции
                    result.rightChild != null && result.rightChild.color == Color.RED) {
                needRebalance = true;
                colorSwap(result);
            }
        } while (needRebalance);
        return result;
    }

    /**
     * Правосторонний малый поворот
     * @param node родитель до поворота
     * @return родитель после поворота
     */
    private Node rightSwap(Node node){
        Node rightChild = node.rightChild;
        Node betweenChild = rightChild.leftChild;
        rightChild.leftChild = node;
        node.rightChild = betweenChild;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }

    /**
     * Левосторонний малый поворот
     * @param node родитель до поворота
     * @return родителяь после поворота
     */
    private Node leftSwap(Node node){
        Node letfChild = node.leftChild;
        Node betweenChild = letfChild.rightChild;
        letfChild.rightChild = node;
        node.leftChild = betweenChild;
        letfChild.color = node.color;
        node.color = Color.RED;
        return letfChild;
    }

    /**
     * Смена цвета
     * @param node
     */
    private void colorSwap(Node node){  // смена цвета происходит только тогда, когда у ноды 2 красных ребенка
        node.rightChild.color = Color.BLACK;    // дети становятся черными
        node.leftChild.color = Color.BLACK;
        node.color = Color.RED;                 // родитель становится красным
    }

    /**
     * Поиск элемента в дереве
     * @param value
     * @return
     */
    public boolean contains(V value){
        Node node = root;
        while (node != null) {
            if (node.value.equals(value)) {
                return true;
            }
            if (node.value.compareTo(value) > 0) { // если значение текущей ноды больше, чем искомое значение V value
                node = node.leftChild;
            } else {
                node = node.rightChild;
            }
        }
        return false;
    }

    public int countNode(){
        List<Node> line = new ArrayList<>();
        line.add(root);
        int count = 0;
        while (line.size() > 0) {    // пока есть дети на текущем уровне
            count += line.size();
            List<Node> nextLine = new ArrayList<>();
            for (Node node : line) {     // будем перебирать текущую линию
                
                if (node.leftChild != null) {nextLine.add(node.leftChild);}
                if (node.rightChild != null) {nextLine.add(node.rightChild);}
            }
            line = nextLine;
        }
        return count;
    }

    private class Node{
        private V value;
        private Node leftChild;
        private Node rightChild;
        private Color color;    // в семинаре не было - там простое дерево, не красно-черное

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", color=" + color +
                    "}";
        }
    }

    enum Color {
        RED, BLACK
    }
    
}
