package refactoring_to_patterns.encapsulate_composite_with_builder.replace_implicit_tree_with_composite;

public class TagBuilder {
    private TagNode rootNode;
    private TagNode currentNode;

    public TagBuilder(String rootTagName) {
        rootNode = new TagNode(rootTagName);
        currentNode = rootNode;
    }

    public String toXml() {
        return rootNode.toString();
    }

    public void addChild(String childTagNode) {
        TagNode parentNode = currentNode;
        currentNode = new TagNode(childTagNode);
        parentNode.add(currentNode);
    }
}
