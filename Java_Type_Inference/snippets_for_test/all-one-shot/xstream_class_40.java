package xstream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.MarshallingContext;
import com.thoughtworks.xstream.io.UnmarshallingContext;
//ID = 39005622


public class xstream_class_40 implements Converter {

   @SuppressWarnings("rawtypes")
   @Override
   public boolean canConvert(Class clazz) {
       return clazz.equals(Integer.class);
   }

   @Override
   public void marshal(Object object, HierarchicalStreamWriter writer,
        MarshallingContext context) {
   }

   @Override
   public Object unmarshal(HierarchicalStreamReader reader,
        UnmarshallingContext context) {
      String text = (String)reader.getValue();
      Integer number = Integer.parseInt(text.trim());
      return number;
   }    
}