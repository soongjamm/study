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
}