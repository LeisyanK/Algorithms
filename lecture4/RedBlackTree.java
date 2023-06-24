package lecture4;
/*  - Каждый узел может быть либо черным, либо красным и иметь двух потомков
    - Корень всегда черный
    - Дети красного узла обязательно черные
    - Красный узел может быть только левым потомком (частный случай - левостороннее красно-черное дерево)
*/

public class RedBlackTree {

    private Node root;

    public boolean add(int value){
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

    private boolean addNode(Node node, int value){
        if (node.value == value) {  // если такая нода уже есть,
            return false;           // то не сможем ее создать повторно
        } else {
            if (node.value > value) {   // если значение нашей ноды больше, чем новой ноды
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

    private Node rightSwap(Node node){
        Node rightChild = node.rightChild;
        Node betweenChild = rightChild.leftChild;
        rightChild.leftChild = node;
        node.rightChild = betweenChild;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }

    private Node leftSwap(Node node){
        Node letfChild = node.leftChild;
        Node betweenChild = letfChild.rightChild;
        letfChild.rightChild = node;
        node.leftChild = betweenChild;
        letfChild.color = node.color;
        node.color = Color.RED;
        return letfChild;
    }

    private void colorSwap(Node node){  // смена цвета происходит только тогда, когда у ноды 2 красных ребенка
        node.rightChild.color = Color.BLACK;
        node.leftChild.color = Color.BLACK;
        node.color = Color.RED;
    }

    private class Node {
        private int value;
        private Color color;
        private Node leftChild;
        private Node rightChild;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", color=" + color +
                    "}";
        }
    }

    private enum Color {
        RED, BLACK
    }
    
}
