package lecture4;
/* Дерево представляет собой связанный односвязный список, где родитель имеет сыылки на своих детей */

import java.util.ArrayList;
import java.util.List;

public class Tree {
    Node root;

// обход в глубину
    // проверка, существует ли в дереве наше значение
    public boolean exist(int value){
        if (root != null) {
            Node node = findInDepth(root, value);
            if (node != null) {
                return true;
            }    
        }        
        return false;
    }
    // поиск ноды
    private Node findInDepth(Node node, int value){
        if (node.value == value) {  // проверяем текущую ноду, если ее значение равно значению, которое мы ищем, 
            return node;            // то возвращаем ее
        } else {
            for (Node child : node.children) {  // иначе запускаем поиск у ее детей, потом у их детей рекурсивно вглубь и т.д
                Node result = findInDepth(child, value);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

// обход в ширину
    private Node findInBreadth(int value){
        List<Node> line = new ArrayList<>();
        line.add(root);
        while (line.size() > 0) {    // пока есть дети на текущем уровне
            List<Node> nextLine = new ArrayList<>();
            for (Node node : line) {     // будем перебирать текущую линию
                if (node.value == value) {
                    return node;
                }
                nextLine.addAll(node.children);
            }
            line = nextLine;
        }
        return null;
    }

    public int countNode(){
        List<Node> line = new ArrayList<>();
        line.add(root);
        int count = 0;
        while (line.size() > 0) {    // пока есть дети на текущем уровне
            count += line.size();
            List<Node> nextLine = new ArrayList<>();
            for (Node node : line) {     // будем перебирать текущую линию
                
                nextLine.addAll(node.children);
            }
            line = nextLine;
        }
        return count;
    }


    public class Node{
        int value;
        List<Node> children;
    }
}
