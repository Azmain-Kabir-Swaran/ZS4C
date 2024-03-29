package xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.apache.commons.lang3.text.WordUtils;

public class xstream_class_27 {
    private class ObjectContentConverter implements Converter {
        XStream xStream;

        private ObjectContentConverter(XStream xStream) {
            this.xStream = xStream;
        }

        @Override
        public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
            String className = WordUtils.uncapitalize(source.getClass().getSimpleName());
            xStream.aliasField(className, xstream.Content.class, "objectContent");
            xStream.marshal(source, writer);
        }

        @Override
        public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean canConvert(Class type) {
            return true;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}