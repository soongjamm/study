package refactoring_to_patterns.encapsulate_composite_with_builder.replace_implicit_tree_with_composite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SchemaBasedTagBuilderTest {

    @Test
    void testTwoSetsOfGreatGrandchildren() {
        TreeSchema schema = new TreeSchema(
                "orders" +
                        "    order" +
                        "        item" +
                        "            apple" +
                        "            orange"
        );

        String expected =
                "<orders>" +
                    "<order>" +
                        "<item>" +
                            "<apple/>" +
                            "<orange/>" +
                        "</item>" +
                        "<item>" +
                            "<apple/>" +
                            "<orange/>" +
                        "</item>" +
                    "</order>" +
                "</orders>";

        SchemaBasedTagBuilder builder = new SchemaBasedTagBuilder(schema);
        builder.add("orders");
        builder.add("order");
        for (int i = 0; i < 2; i++) {
            builder.add("item");
            builder.add("apple");
            builder.add("orange");
        }

        assertEquals(expected, builder.toString());
    }
}
