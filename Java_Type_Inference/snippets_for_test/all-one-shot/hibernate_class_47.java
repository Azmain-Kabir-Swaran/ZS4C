package hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.example.hibernate.HibernateUtil;
//ID = 4323066


public class hibernate_class_47 {

    public static boolean registerEvent() {

         SessionFactory sf = HibernateUtil.getSessionFactory();
         Transaction t = null;
         try  {
         Session s  = sf.openSession();
         t = s.beginTransaction(); // start a new transaction
         s.persist("aa");
         t.commit();  // commit transaction 
         return true;
         }
         catch(Exception ex) {
             System.err.println("Error -->"  + ex.getMessage());
             if ( t!=null) t.rollback();  // rollback transaction on exception 
             return false;
         }
    }
}