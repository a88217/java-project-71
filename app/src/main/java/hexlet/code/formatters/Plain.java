package hexlet.code.formatters;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(Map<String, List> resultMap) {
        StringBuilder resultString = new StringBuilder("");
        Iterator it = resultMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            List value = (List) entry.getValue();
            for (int i = 1; i < value.size(); i++) {
                value.set(i, stringify(value.get(i)));
            }
            if (value.get(0).equals("Modified")) {
                resultString.append("Property '");
                resultString.append(key);
                resultString.append("' was updated. From ");
                resultString.append(value.get(1));
                resultString.append(" to ");
                resultString.append(value.get(2));
                resultString.append("\n");
            }
            if (value.get(0).equals("Deleted")) {
                resultString.append("Property '");
                resultString.append(key);
                resultString.append("' was removed");
                resultString.append("\n");
            }
            if (value.get(0).equals("Added")) {
                resultString.append("Property '");
                resultString.append(key);
                resultString.append("' was added with value: ");
                resultString.append(value.get(1));
                resultString.append("\n");
            }
        }
        return resultString.toString().substring(0, resultString.toString().length() - 1);
    }

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        return value.toString();
    }
}
