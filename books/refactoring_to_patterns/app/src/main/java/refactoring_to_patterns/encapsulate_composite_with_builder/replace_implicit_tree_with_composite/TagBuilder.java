package refactoring_to_patterns.encapsulate_composite_with_builder.replace_implicit_tree_with_composite;

public class TagBuilder {
    private static int TAG_CHARS_SIZE = 5;
    private static int ATTRIBUTE_CHARS_SIZE = 4;

    private int outputBufferedSize;
    private TagNode rootNode;
    private TagNode currentNode;

    public TagBuilder(String rootTagName) {
        rootNode = new TagNode(rootTagName);
        currentNode = rootNode;
        incrementBufferSizeByTagLength(rootTagName);
    }

    public String toXml() {
        StringBuffer xmlResult = new StringBuffer(outputBufferedSize);
        rootNode.appendContentsTo(xmlResult);
        return xmlResult.toString();
    }

    public void addChild(String childTagNode) {
        TagNode parentNode = currentNode;
        currentNode = new TagNode(childTagNode);
        addTo(parentNode, currentNode.getName());
    }

    public void addSibling(String siblingTagNode) {
        addTo(currentNode.getParent(), siblingTagNode);
    }

    private void addTo(TagNode parentNode, String tagName) {
        currentNode = new TagNode(tagName);
        parentNode.add(currentNode);
        incrementBufferSizeByTagLength(tagName);
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
        incrementBufferSizeByAttributeLength(name, value);
    }

    public void addValue(String value){
    	currentNode.addValue(value);
    	incrementBufferSizeByValueLength(value);
    }

    private void incrementBufferSizeByTagLength(String tag) {
        int sizeOfOpenAndCloseTags = tag.length() * 2;
        outputBufferedSize += sizeOfOpenAndCloseTags + TAG_CHARS_SIZE;
    }

    private void incrementBufferSizeByAttributeLength(String name, String value) {
        outputBufferedSize += name.length() + value.length() + ATTRIBUTE_CHARS_SIZE;
    }

    private void incrementBufferSizeByValueLength(String value) {
        outputBufferedSize += value.length();
    }

    public int bufferSize() {
        return outputBufferedSize;
    }
}
