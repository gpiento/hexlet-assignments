package exercise;

public class LabelTag implements TagInterface {

    String tagText;
    TagInterface tagInterface;

    public LabelTag(String tagText, TagInterface tagInterface) {

        this.tagText = tagText;
        this.tagInterface = tagInterface;
    }

    @Override
    public String render() {

        return "<label>Press Submit" + tagInterface.render() + "</label>";
    }
}
