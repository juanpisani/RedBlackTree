import org.junit.Test;

import static org.junit.Assert.*;

public class RedBlackTreeTest {

    @Test
    public void test01_add_first_element_and_root_is_black(){
        RedBlackTree<Integer, String> redBlackTree = new RedBlackTree<>();
        redBlackTree.add(10, "10");
        assertEquals(RedBlackTree.BLACK, redBlackTree.head.color);
    }

    @Test
    public void test01_add_first_three_elements_and_rotation_is_performed(){
        RedBlackTree<Integer, String> redBlackTree = new RedBlackTree<>();
        redBlackTree.add(10, "10");
        redBlackTree.add(20, "10");
        redBlackTree.add(30, "10");
        assertEquals(RedBlackTree.BLACK, redBlackTree.head.color);
    }



}