package seminar4;

/* Дерево представляет собой связанный односвязный список, где родитель имеет сыылки на своих детей */
public class Tree<V extends Comparable<V>>{
    private Node root;

    public void add(V value){
        Node node = root;
        Node newNode = new Node();
        newNode.value = value;
        if (root == null) {
            root = newNode;
        } else {
            if (node.value.compareTo(value) > 0) {
                node.left = newNode;
            } else {
                if (node.value.compareTo(value) < 0) {
                    node.right = newNode;
                } else {
                    System.out.println("Такой элемент уже есть");
                }
                
            }
        }
    }

    public boolean contains(V value){
        Node node = root;
        while (node != null) {
            if (node.value.equals(value)) {
                return true;
            }
            if (node.value.compareTo(value) > 0) { // если значение текущей ноды больше, чем искомое значение V value
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
    }

    private class Node{
        private V value;
        private Node left;
        private Node right;
    }

}