package refactoring_to_patterns.encapsulate_composite_with_builder.replace_implicit_tree_with_composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TagNode {
    private String name = "";
    private String value = "";
    private StringBuffer attributes;
    private List children;
    private TagNode parent;

    public TagNode(String name) {
        this.name = name;
        attributes = new StringBuffer("");
    }

    public void addAttribute(String attribute, String value) {
        attributes.append(" ");
        attributes.append(attribute);
        attributes.append("='");
        attributes.append(value);
        attributes.append("'");
    }

    public void addValue(String value) {
        this.value = value;
    }

    public String toString() {
        Iterator it = children().iterator();
        if (!it.hasNext()) {
            return "<" + name + attributes + "/>";
        }
        String result;
        result =
                "<" + name + attributes + ">";
        while (it.hasNext()) {
            TagNode node = (TagNode) it.next();
            result += node.toString();
        }
        result += value;
        result += "</" + name + ">";
        return result;
    }

    public void add(TagNode childNode) {
        childNode.setParent(this);
        children().add(childNode);
    }

    public TagNode getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    private List children() {
        if (children == null) {
            children = new ArrayList();
        }
        return children;
    }

    private void setParent(TagNode parent) {
        this.parent = parent;
    }
}
