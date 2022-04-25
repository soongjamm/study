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

    public void addSibling(String siblingTagNode) {
        addTo(currentNode.getParent(), siblingTagNode);
    }

    private void addTo(TagNode parentNode, String tagName) {
        currentNode = new TagNode(tagName);
        parentNode.add(currentNode);
    }

    public void addToParent(String parentTagName, String childTagName) {
        TagNode parentNode = findParentBy(parentTagName);
        if (parentNode == null) {
            throw new RuntimeException("missing parent tag: " + parentTagName);
        }
        addTo(parentNode, childTagName);
    }

    private TagNode findParentBy(String parentTagName) {
        TagNode parentNode = currentNode;
        while (parentNode != null) {
            if (parentTagName.equals(parentNode.getName())) {
                return parentNode;
            }
            parentNode = parentNode.getParent();
        }
        return null;
    }

    public void addAttribute(String name, String value) {
        currentNode.addAttribute(name, value);
    }

    public void addValue(String value){
    	currentNode.addValue(value);
    }
}
