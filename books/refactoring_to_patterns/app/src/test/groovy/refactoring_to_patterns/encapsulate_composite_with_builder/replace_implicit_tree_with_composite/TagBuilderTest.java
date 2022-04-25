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

    @Test
    void testAttributesAndValues() {
        String expected =
                "<flavor name='Test-Driven Development'>" + // 속성을 가진 태그
                    "<requirements>" +
                        "<requirement type='hardware'>" +
                            "1 computer for every 2 participants" + // 값을 가진 태그
                        "</requirement>" +
                        "<requirement type='software'>" +
                            "IDE" +
                        "</requirement>" +
                    "</requirements>" +
                "</flavor>";

        TagBuilder builder = new TagBuilder("flavor");
        builder.addAttribute("name", "Test-Driven Development");
        builder.addChild("requirements");
            builder.addToParent("requirements", "requirement");
            builder.addAttribute("type", "hardware");
            builder.addValue("1 computer for every 2 participants");
            builder.addToParent("requirements", "requirement");
            builder.addAttribute("type", "software");
            builder.addValue("IDE");

        assertEquals(expected, builder.toXml());
    }

    @Test
    void testToStringBufferSize() {
        TagBuilder builder = new TagBuilder("requirements");
        builder.addChild("requirement");
        builder.addAttribute("type", "software");
        builder.addValue("IDE");

        int stringSize = builder.toXml().length();
        System.out.println(builder.toXml());
        int computedSize = builder.bufferSize();
        assertEquals(stringSize, computedSize);
    }
}