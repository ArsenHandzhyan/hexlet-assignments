package exercise;

import java.util.Map;
import java.util.List;

public class PairedTag extends Tag {
    private final String body;
    private final List<Tag> children;

    public PairedTag(String tagName, Map<String, String> attributes, String body, List<Tag> children) {
        super(tagName, attributes);
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(body);
        for (Tag child : children) {
            sb.append(child.toString());
        }
        sb.append("</").append(tagName).append(">");
        return sb.toString();
    }
}
