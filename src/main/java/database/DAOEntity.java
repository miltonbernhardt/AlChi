package database;

import java.math.BigInteger;
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
        	e.printStackTrace();
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object get(Class clase, Integer id) {
		Object tipo = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	session.beginTransaction();
        	tipo = session.get(clase, id);
        }
        catch (HibernateException e) {
        	PanelAlerta.getExcepcion(e, "No se pudo obtener el objeto desde la base de datos.");        	
            session.getTransaction().rollback();	
		}
        session.close();
		return tipo;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getSingleResult(String consulta, Class clase) {
		Object objeto = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	session.beginTransaction();
        	objeto = session.createQuery(consulta, clase).getSingleResult(); 
        }
        catch (HibernateException e) {
        	PanelAlerta.getExcepcion(e, "No se pudo obtener el objeto desde la base de datos.");        	
		}
        session.close();
		return objeto;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<? extends Object> getResultList(String consulta, Class clase) {
		List<? extends Object> lista = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	session.beginTransaction();
        	lista = session.createQuery(consulta, clase).getResultList();
        }
        catch (HibernateException e) {
        	PanelAlerta.getExcepcion(e, "No se pudo obtener la lista de objetos desde la base de datos.");        	
		}
        session.close();
		return lista;
	}

	public Integer getCantidad(String cadena) {
		Integer i = 0;
		Object o = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			o = session.createSQLQuery(cadena).getSingleResult();	
		}catch (javax.persistence.NoResultException e) { 
			
			o = null;    
		}
		
		if(o != null) {
			i = ((BigInteger) o).intValue();
		}
		
		session.close();
	    return i;
	}
}
