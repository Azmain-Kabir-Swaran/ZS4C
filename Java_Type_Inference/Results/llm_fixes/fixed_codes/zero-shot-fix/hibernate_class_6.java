import java.util.Iterator;
import java.net.MalformedURLException;
import org.hibernate.HibernateException;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import java.util.List;
import org.hibernate.SessionFactory;
import java.io.File;
import org.hibernate.cfg.Configuration;
import org.dom4j.DocumentException;
import org.dom4j.Document;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.dom4j.io.SAXReader;

public class hibernate_class_6 {

    public static void main(final String[] args) throws MalformedURLException {
        File baseDir = new File("C:\\workspaces\\hobby");
        hibernate_class_6 importer = new hibernate_class_6();
        Configuration config = importer.setupDb(baseDir);

        if (config != null) {
            importer.importContents(new File(baseDir, "people.xml"), config);
        }
    }

    private void importContents(final File file, final Configuration config) throws MalformedURLException {
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Session dom4jSession = session;

        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(file);

            List list = document.selectNodes("//Person");
            Iterator iter = list.iterator();

            while (iter.hasNext()) {
                Object personObj = iter.next();
                // dom4jSession.save(Person.class.getName(), personObj);
            }

            session.flush();
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private Configuration setupDb(final File baseDir) throws HibernateException {
        Configuration cfg = new Configuration();
        cfg.addFile(new File(baseDir, "name/seller/rich/hobby/Person.hbm.xml"));
        cfg.addFile(new File(baseDir, "name/seller/rich/hobby/Hobby.hbm.xml"));

        SchemaExport export = new SchemaExport(cfg);

        export.setOutputFile("hobbyDB.txt");
        export.execute(false, true, false, false);
        return cfg;
    }
}