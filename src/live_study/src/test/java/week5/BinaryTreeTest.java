package week5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    Node root;

    @BeforeEach
    void initilize() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node5 = new Node(5, node1, node2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node6 = new Node(6, node3, node4);
        Node node7 = new Node(7, node5, node6);
        Node node8 = new Node(8);
        root = new Node(9, node7, node8);
    }

    @Test
    void bfs() {
        assertTrue(!Objects.isNull(root));
        assertEquals("978561234", BinaryTree.bfs(root));

    }

    @Test
    void dfs() {
        assertTrue(!Objects.isNull(root));
        assertEquals("152736498", BinaryTree.dfs(root));
    }

}