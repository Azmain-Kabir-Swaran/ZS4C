import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class xstream_class_28 {
    public class MapEntryConverter implements Converter {

        public boolean canConvert(Class clazz) {
            return Map.class.isAssignableFrom(clazz);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer, com.thoughtworks.xstream.converters.MarshallingContext context) {
            Map<String, Integer> map = (Map<String, Integer>) value;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                writer.startNode(entry.getKey().toString());
                writer.setValue(entry.getValue().toString());
                writer.endNode();
            }
        }

        public Object unmarshal(HierarchicalStreamReader reader, com.thoughtworks.xstream.converters.UnmarshallingContext context) {
            Map<String, Integer> map = new HashMap<String, Integer>();

            while (reader.hasMoreChildren()) {
                reader.moveDown();
                map.put(reader.getNodeName(), new Integer(reader.getValue()));
                reader.moveUp();
            }
            return map;
        }
    }
}