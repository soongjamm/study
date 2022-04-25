package refactoring_to_patterns.encapsulate_composite_with_builder.replace_implicit_tree_with_composite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TagBuilderTest {

    @Test
    public void testBuildOneNode() {
        String expectedXml =
                "<flavors/>";
        String actualXml = new TagBuilder("flavors").toXml();
        Assertions.assertEquals(expectedXml, actualXml);
    }

    @Test
    public void testBuildOneChild() {
        String expectedXml =
                "<flavors>" +
                        "<flavor/>" +
                        "</flavors>";
        TagBuilder tagBuilder = new TagBuilder("flavors");
        tagBuilder.addChild("flavor");
        String actualXml = tagBuilder.toXml();

        Assertions.assertEquals(expectedXml, actualXml);
    }


    @Test
    void testBuildChildrenOfChildren() {
        String expected =
                "<flavors>" +
                    "<flavor>" +
                        "<requirements>" +
                            "<requirement/>" +
                        "</requirements>" +
                    "</flavor>" +
                "</flavors>";

        TagBuilder builder = new TagBuilder("flavors");
        builder.addChild("flavor");
        builder.addChild("requirements");
        builder.addChild("requirement");
        String actualXml = builder.toXml();

        assertEquals(expected, actualXml);
    }

    @Test
    void testBuildSibling() {
        String expected =
                "<flavors>" +
                    "<flavor1/>" +
                    "<flavor2/>" +
                "</flavors>";

        TagBuilder builder = new TagBuilder("flavors");
        builder.addChild("flavor1");
        builder.addSibling("flavor2");
        String actualXml = builder.toXml();

        assertEquals(expected, actualXml);
    }

    @Test
    void testRepeatingChildrenAndGrandchildren() {
        String expected =
                "<flavors>" +
                    "<flavor>" +
                        "<requirements>" +
                            "<requirement/>" +
                        "</requirements>" +
                    "</flavor>" +
                    "<flavor>" +
                        "<requirements>" +
                            "<requirement/>" +
                        "</requirements>" +
                    "</flavor>" +
                "</flavors>";

        TagBuilder builder = new TagBuilder("flavors");
        for (int i = 0; i < 2; i++) {
            builder.addToParent("flavors", "flavor");
            builder.addChild("requirements");
            builder.addChild("requirement");
        }

        assertEquals(expected, builder.toXml());
    }

    @Test
    void testParentNameNotFound() {
        TagBuilder builder = new TagBuilder("flavors");
        try {
            for (int i = 0; i < 2; i++) {
                builder.addToParent("favors", "flavor"); // flavors 를 flavor 로 잘못 입력
                builder.addChild("requirements");
                builder.addChild("requirement");
            }
            fail("should not allow adding to parent that does not exist");
        } catch (RuntimeException e) {
            String exceptionErrorMessage = "missing parent tag: favors";
            assertEquals(exceptionErrorMessage, e.getMessage());
        }
    }
}