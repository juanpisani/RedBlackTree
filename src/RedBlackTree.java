import java.util.List;

public class RedBlackTree<K extends Comparable<K>, V> {
    static final boolean RED = true;
    static final boolean BLACK = false;

    Node head;
    private int size;
    Node lastAdded;

    class Node{
        boolean color;
        K key;
        V value;
        Node left;
        Node right;
        Node father;

        Node(K key, V value) {
            this.color = RED;
            this.key = key;
            this.value = value;
        }

        Node getUncle() {
            if (father != null && father.father != null && father.left != null && father.right != null) {
                return father.father.left == father ? father.father.right : father.father.left;
            }
            return null;
        }
    }

    public RedBlackTree() {}

    public boolean add(K key, V value){
        int currentSize = size;
        head = add(head, key, value);
        balanceAdd(lastAdded);
        return currentSize < size;
    }

    private void balanceAdd(Node node) {
        Node uncle = node.getUncle();

        if (uncle != null && uncle.color == RED){
            node.father.color = BLACK;
            uncle.color = BLACK;
            node.father.father.color = RED;
        }

        if (uncle != null && uncle.color == BLACK && node.father.right == node){
            node.father = rotateLeft(node.father);
        }

        if (uncle != null && uncle.color == BLACK && node.father.left == node){
            rotateRight(node.father.father);

            //switch color
            boolean fatherColor = node.father.color;
            node.father.color = node.father.right.color;
            node.father.right.color = fatherColor;
        }

    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        Node right = left.right;

        // Perform rotation
        left.right = node;
        node.left = right;

        return left;
    }

    private Node rotateLeft(Node node) {
        Node y = node.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = node;
        node.right = T2;

        // Return new root
        return y;
    }

    private Node add(Node node, K key, V value) {
        if (node == null){
            size++;
            Node newNode = new Node(key, value);
            lastAdded = newNode;
            return newNode;
        }
        if (key.compareTo(node.key) > 0){
            return add(node.right, key, value);
        }
        if (key.compareTo(node.key) < 0){
            return add(node.left, key, value);
        }
        return node;
    }


    public boolean remove(K key){
        return true;
    }

    public boolean edit(K key, V newValue){
        return true;
    }

    public int count(Condition<V> condition){
        return 0;
    }

    public V show(K key){
        return null;
    }

    public int weigth(){
        return size;
    }

    public List<V> getElems(){
        return null;
    }

    public List<V> getElems(Condition<V> condition){
        return null;
    }

   public boolean save(){
        return true;
   }
}
