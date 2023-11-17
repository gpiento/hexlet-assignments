package exercise;

import java.util.Map;

public class SingleTag extends Tag {

    Map<String, String> param;

    public SingleTag(String tag, Map<String, String> param) {
        this.tag = tag;
        this.param = param;
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

        return builder.toString();
    }
}
