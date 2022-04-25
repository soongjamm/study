package refactoring_to_patterns.encapsulate_composite_with_builder.replace_implicit_tree_with_composite;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TagTest {

    @Test
    void testParents() {
        TagNode root = new TagNode("root");
        assertNull(root.getParent());

        TagNode childNode = new TagNode("child");
        root.add(childNode);
        assertEquals(root, childNode.getParent());
        assertEquals("root", childNode.getParent().getName());
    }
}
