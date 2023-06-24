package seminar4;
/*
 *  Суть Хэш-таблицы: все элементы делим по корзинам (basket) по такому принципу: для ключа нового элемента вычисляем 
 * его хэш и распределяем в одну из корзин путем деления полученного хэша на количество корзин (получаем номер корзины).
 * Т.к. для разных ключей могут оказаться одинаковые хэши, то такие элементы с одинаковыми хэшами ключей попадают 
 * в одну корзину (в связанный список). Таким образом работа с хэш-таблицей ускоряет работу с большим количеством данных,
 * поскольку поиск корзин выполняется за O(1) шагов (поиск элемента в массиве, длина массива равна количеству корзин),
 * а в самой корзине (в связанном списке) количество элементов небольшое. Когда количество элементов в корзине становится 
 * слишком большим, то происходит перераспределение элементов в большее количество корзин (количество корзин увеличивается).
 * Перераспределение элементов происходит по тому же принципу: вычисляем хэш ключа элемента и делим его на количество корзин.
*/
//import javax.swing.text.html.parser.Entity;

public class HashTable<K, V> {

    private static final int INIT_BASKET_COUNT = 25;
    private Basket[] baskets;

    // констуктор с параметром для хэш-таблицы
    public HashTable(int initsize){
        baskets = (Basket[]) new Object[initsize];  // массив связанных списков из initsize(или здесь INIT_BASKET_COUNT) элементов
        // baskets = (Basket[]) new Object[initsize];
    }

    // констуктор без параметра для хэш-таблицы
    public HashTable(){
        this(INIT_BASKET_COUNT);
    }

    // вычисляем номер корзины (хэш-код ключа делим на количество корзин)
    private int calculateBasketIndex(K key){
        return key.hashCode() % baskets.length;
    }

    // добавление элемента в корзину
    public boolean put(K key, V value){
        int index = calculateBasketIndex(key);
        Basket basket = baskets[index]; // basket - связанный список, baskets[index] - это массив этих связанных списков
        if (basket == null) {           // если такой корзины пока нет
            basket = new Basket();
            baskets[index] = basket;    // то создаем новую пустую корзину
        }
        Entity entity = new Entity(key, value); // создали элемент с помощью конструктора
        return basket.add(entity);
    }

    // удаление элемента из корзины
    public boolean remove(K key){
        int index = calculateBasketIndex(key);
        Basket basket = baskets[index]; // basket - связанный список, baskets[index] - это массив этих связанных списков
        return basket.remove(key);
    }

    public V get(K key){
        int index = calculateBasketIndex(key);
        Basket basket = baskets[index]; // basket - связанный список, baskets[index] - это массив этих связанных списков
        if (basket != null) {
            return basket.get(key);
        }
        return null;
    }

    public class Entity {
        private K key;
        private V value;

        // конструктор для Entity
        public Entity(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    private class Basket{   // связанный список элементов типа "ключ:значение"
        private Node head;

        // поиск элемента по ключу
        public V get(K key){
            Node node = head;
            while (node != null) {
                if (node.pair.key.equals(key)) {
                    return node.pair.value;
                }
                node = node.next;
            }
            return null;
        }

        // добавление элемента в корзину
        public boolean add(Entity entity){
            Node node = new Node();
            node.pair = entity;
            if (head != null){
                Node current = head;
                while (true) {
                    if (current.pair.key.equals(entity.key)) {  // мы нашли элемент с таким же ключом, который мы хотим добавить
                        return false;
                    }
                    if (current.next == null) {     // дошли до конца связанного списка и не нашли такой же ключ,
                        current.next = node;        // то добавляем элемент в список
                        return true;
                    } else {
                        current = current.next;
                    }
                }
            } else {        // если корзина была пуста
                head = node;
                return true;
            }
        }

        // удаление элемента из корзины
        public boolean remove(K key){
            if (head != null){
                if (head.pair.key.equals(key)){ // если нужно удалить head,
                    head = head.next;           // head-ом становится следующий элемент, а head отбрасывается
                    // return true;             // нужно ли возвращать true??? (mine line)
                } else{
                    Node node = head;
                    while (node.next != null) {
                        if (node.next.pair.key.equals(key)) {
                            node.next = node.next.next;
                            return true;
                        }
                        node = node.next;
                    }
                }
            }
            return false;
        }

        private class Node{
            private Entity pair;
            private Node next;
        }
    }

}
