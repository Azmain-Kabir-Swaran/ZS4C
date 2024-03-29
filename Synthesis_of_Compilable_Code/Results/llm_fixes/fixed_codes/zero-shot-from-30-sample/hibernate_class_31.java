package hibernate;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

public class hibernate_class_31
{
    public static List<?> getData ()
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        List<?> result = null;
        try
        {
            session.beginTransaction();
            Query query = session.createQuery("from Users");
            result = query.list();
            session.getTransaction().commit();
            query.setReadOnly(true);
            query.setMaxResults(50);
            session.flush();
            session.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;      
    }

}