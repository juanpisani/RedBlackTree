import java.util.LinkedList;
import java.util.List;

public class RedBlackTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node head;
    private int size;
    private Node lastAdded;

    class Node{

        boolean color;
        K key;
        V value;
        Node left;
        Node right;
        Node father;

        public Node(K key, V value, Node father) {
            this.key = key;
            this.value = value;
            this.father = father;
            this.color = RED;
        }

        Node getUncle() {
            if (father != null && father.father != null && father.father.left != null && father.father.right != null) {
                return father.father.left == father ? father.father.right : father.father.left;
            }

            return null;
        }

        boolean getUncleColour() {
            if (father != null && father.father != null && father.father.left != null && father.father.right != null) {
                return father.father.left == father ? father.father.right.color : father.father.left.color;
            }

            return BLACK;
        }

        @Override
        public String toString() {
            if (color == RED) return "\033[0;31m"+ value;
            return "\033[0;30m" + value;
        }
    }

    public RedBlackTree() {}

    public boolean add(K key, V value){
        int currentSize = size;
        head = add(head, null, key, value);
        balanceAdd(lastAdded);
        return currentSize < size;
    }

    private Node add(Node node, Node father, K key, V value) {
        if (node == null){
            node = new Node(key, value, father);
            lastAdded = node;
            size++;
        }
        else if (node.key.compareTo(key) > 0) {
            node.left = add(node.left, node, key, value);
        }
        else if (node.key.compareTo(key) < 0){
            node.right =  add(node.right, node, key, value);
        }
        return node;
    }
    private void balanceAdd (Node node){
        balanceAddr(node);
    }
    private Node balanceAddr(Node node) {


        if (node == head) {
            node.color = BLACK;
        }

        if (node.getUncleColour() == RED) {
            node.father.color = BLACK;
            node.getUncle().color = BLACK;
            node.father.father.color = RED;
            node.father.father = balanceAddr(node.father.father);

        } else{
            if (node.father != null && node.father.father != null) {
                //left left case
                if (node == node.father.left && node.father == node.father.father.left) {
                    node.father.father = rotateRight(node.father.father);

                    //swap colors
                    boolean gColor = node.father.father.color;
                    node.father.father.color = node.father.color;
                    node.father.color = gColor;
                }
                //left right case
                else if (node == node.father.right && node.father == node.father.father.left) {
                    node.father = rotateLeft(node.father);

                    node.father.father = rotateRight(node.father.father);
                    //swap colors
                    boolean gColor = node.father.father.color;
                    node.father.father.color = node.father.color;
                    node.father.color = gColor;
                }
                //right right case
                else if (node == node.father.right && node.father == node.father.father.right) {
                    node.father.father = rotateLeft(node.father.father);

                    //swap colors
                    boolean gColor = node.father.father.color;
                    node.father.father.color = node.father.color;
                    node.father.color = gColor;
                }
                //right left case
                else if (node == node.father.left && node.father == node.father.father.right) {
                    node.father = rotateRight(node.father);

                    //swap colors
                    boolean gColor = node.father.father.color;
                    node.father.father.color = node.father.color;
                    node.father.color = gColor;
                }
            }

        }

        return node;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = node;
        node.left = T2;

        // Return new root
        return x;
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

    public void printByLevels() {

        if(head == null) return;
        LinkedList<Node> q = new LinkedList<>();
        q.addLast(head);
        int nodesOnLevel = q.size();
        while(nodesOnLevel != 0)
        {
            nodesOnLevel = q.size();
            while(nodesOnLevel > 0)
            {
                Node node = q.removeFirst();
                System.out.print(node + " ");
                if(node.left != null)
                    q.addLast(node.left);
                if(node.right != null)
                    q.addLast(node.right);
                nodesOnLevel--;
            }
            System.out.println();
            nodesOnLevel = q.size();
        }


    }


    public static void main(String[] args) {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        tree.add(6, "6");
        tree.add(7, "7");
        tree.add(5, "5");
        tree.add(4, "4");
        tree.add(3, "3");

        tree.printByLevels();
        System.out.println();
    }

}
