import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class xstream_class_5 {
    public static class Test {
        public static void main(String[] args) throws FileNotFoundException {
            XStream xs = new XStream(new DomDriver());
            Foo f = new Foo(1, "booo", new Bar(42));
            PrintWriter pw = new PrintWriter("a.xml");
            xs.toXML(f, pw);
        }
    }

    public static class Bar {
        public int id;

        public Bar(int id) {
            this.id = id;
        }
    }

    public static class Foo {
        public int a;
        public String b;
        public Bar boo;

        public Foo(int a, String b, Bar c) {
            this.a = a;
            this.b = b;
            this.boo = c;
        }
    }
}