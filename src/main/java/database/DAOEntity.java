package database;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class DAOEntity {
	
	private static DAOEntity instanciaDAO = null;
	 
    private DAOEntity() {}

    public static DAOEntity getDAO() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOEntity();
        }
        
        return instanciaDAO;
    }
	
	public void save(Object o) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
            session.getTransaction().rollback();	
		}
        session.close();
	}
	
	public void saveOrUpdate(Object o) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	session.beginTransaction();
            session.saveOrUpdate(o);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
            session.getTransaction().rollback();	
		}
        session.close();
	}
	
	public void persist(Object o) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	session.beginTransaction();
            session.persist(o);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
            session.getTransaction().rollback();	
		}
        session.close();
	}
	
}
