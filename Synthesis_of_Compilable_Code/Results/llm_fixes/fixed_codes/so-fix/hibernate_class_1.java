import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.example.User;
import com.example.UserDAO;

public class hibernate_class_1 {
    public class HibernateUserDAO implements UserDAO {

        private SessionFactory sessionFactory;

        public HibernateUserDAO() {
            AnnotationConfiguration annotConf = new AnnotationConfiguration();
            annotConf.addAnnotatedClass(User.class);
            annotConf.configure();
            sessionFactory = annotConf.buildSessionFactory();
        }

        public void store(User user) {
            Session session = sessionFactory.openSession();
            Transaction tx = session.getTransaction();
            try {
                tx.begin();
                session.saveOrUpdate(user);
                tx.commit();
            } catch (RuntimeException e) {
                tx.rollback();
                throw e;
            } finally {
                session.close();
            }
        }

        @Override
        public User getUser(int arg0) {
            return null;
        }

        @Override
        public User getUserByFirstLastName(String arg0, String arg1) {
            return null;
        }

        @Override
        public User getUserByUsername(String arg0) {
            return null;
        }

        @Override
        public int saveUser(User arg0) {
            return 0;
        }
    }
}