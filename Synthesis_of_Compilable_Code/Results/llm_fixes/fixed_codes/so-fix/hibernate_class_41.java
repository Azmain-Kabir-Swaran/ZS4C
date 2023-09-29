import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import java.util.List;

public class hibernate_class_41 {
    @SuppressWarnings("null")
    public static List list(Class<?> className,int start,int limit,String[] searchFilter){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null; 
        try {
            transaction = session.beginTransaction();

            Criteria criteria = session.createCriteria(className);
            criteria.setFirstResult(start);
            criteria.setMaxResults(limit);
            
            for(String sf : searchFilter){
                String[] values = null;
                if(values == null){
                    if(values.length == 1) {
                        criteria.add(Restrictions.eq(sf, values[0]));
                    }else{
                        criteria.add(Restrictions.in(sf, values));
                    }
                }
            }

            transaction.commit();

        }catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }

        return null;
    }
}

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}