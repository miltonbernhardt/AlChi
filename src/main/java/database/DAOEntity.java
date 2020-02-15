package database;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import app.PanelAlerta;

public class DAOEntity {
	
	private static DAOEntity instanciaDAO = null;
	 
    private DAOEntity() {}

    public static DAOEntity get() {
        if (instanciaDAO == null){
        	instanciaDAO = new DAOEntity();
        }        
        return instanciaDAO;
    }
	
	public Boolean save(Object o) {
		Boolean valido = true;
		Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	valido = false;        	
        	PanelAlerta.getExcepcion(e, "No se pudo guardar en la base de datos.");        	
            session.getTransaction().rollback();	
		}
        session.close();
        return valido;
	}	
	
	public Boolean update(Object o) {
		Boolean valido = true;
		Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	session.beginTransaction();
            session.merge(o);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
        	valido = false;
        	PanelAlerta.getExcepcion(e, "No se pudo actualizar en la base de datos.");        	
            session.getTransaction().rollback();	
		}
        session.close();
        return valido;
	}
	
	public Object get(Integer id, Object o) {
		Object tipo = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	session.beginTransaction();
        	tipo = session.get(o.getClass(), id);
        }
        catch (HibernateException e) {
        	PanelAlerta.getExcepcion(e, "No se pudo obtener el objeto desde la base de datos.");        	
            session.getTransaction().rollback();	
		}
        session.close();
		return tipo;
	}
	
	public Object getSingleResult(String consulta, Object o) {
		Object objeto = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	session.beginTransaction();
        	objeto = session.createQuery(consulta, o.getClass()).getSingleResult(); 
        }
        catch (HibernateException e) {
        	PanelAlerta.getExcepcion(e, "No se pudo obtener el objeto desde la base de datos.");        	
		}
        session.close();
		return objeto;
	}
	
	public List<? extends Object> getResultList(String consulta, Object o) {
		List<? extends Object> lista = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	session.beginTransaction();
        	lista = session.createQuery(consulta, o.getClass()).getResultList();
        }
        catch (HibernateException e) {
        	PanelAlerta.getExcepcion(e, "No se pudo obtener la lista de objetos desde la base de datos.");        	
		}
        session.close();
		return lista;
	}	
}
