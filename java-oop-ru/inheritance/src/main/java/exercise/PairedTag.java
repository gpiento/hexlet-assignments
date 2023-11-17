package exercise;

import java.util.List;
import java.util.Map;

public class PairedTag extends Tag {

    Map<String, String> param;
    String text;
    List<Tag> singleTags;

    public PairedTag(final String tag, final Map<String, String> param, final String text, final List<Tag> singleTags) {
        this.tag = tag;
        this.param = param;
        this.text = text;
        this.singleTags = singleTags;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder().append("<").append(tag);

        if (!param.isEmpty()) {
            for (Map.Entry<String, String> m : param.entrySet()) {
                builder.append(" ").append(m.getKey()).append("=")
                        .append("\"").append(m.getValue()).append("\"");
            }
        }

        builder.append(">");

        if (!singleTags.isEmpty()) {
            for (Tag t : singleTags) {
                builder.append(t.toString());
            }
        }

        builder.append(text).append("</").append(tag).append(">");

        return builder.toString();
    }
}