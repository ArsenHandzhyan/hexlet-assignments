package exercise;

public class InputTag implements TagInterface {
    private final String type;
    private final String value;

    public InputTag(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String render() {
        return "<input type=\"" + type + "\" value=\"" + value + "\">";
    }

    public static void main(String[] args) {
        TagInterface inputTag = new InputTag("submit", "Save");
        TagInterface labelTag = new LabelTag("Press Submit", inputTag);
        System.out.println(labelTag.render());
    }
}
