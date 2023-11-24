package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private final String type;
    private final TagInterface inputTag;

    public LabelTag(String type, TagInterface inputTag) {
        this.type = type;
        this.inputTag = inputTag;
    }

    @Override
    public String render() {
        return "<label>" + type + inputTag.render() + "</label>";
    }
}
// END
