import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import hibernate.HibernateUtil;

public class hibernate_class_41 {
    @SuppressWarnings("null")
    public static List<?> list(Class<?> className, int start, int limit, String[] searchFilter) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Criteria criteria = session.createCriteria(className);
            criteria.setFirstResult(start);
            criteria.setMaxResults(limit);

            for (String sf : searchFilter) {
                String[] values = null;
                if (values == null) {
                    if (values.length == 1) {
                        criteria.add(Restrictions.eq(sf, values[0]));
                    } else {
                        criteria.add(Restrictions.in(sf, values));
                    }
                }
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return null;
    }
}