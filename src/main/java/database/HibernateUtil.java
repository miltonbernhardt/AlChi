package database;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.metamodel.Type.PersistenceType;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                registry = new StandardServiceRegistryBuilder().configure().build();
                MetadataSources sources = new MetadataSources(registry);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
                sessionFactory.getCurrentSession().beginTransaction();
                
            } catch (Exception e) {
            	e.printStackTrace();
            }            
        }
        return sessionFactory;
    }
  
	public static Boolean conectarBaseDatos() {
		if(getSessionFactory() != null) {
			return true;
		}
		else {
			return false;
		}
	}	
	
	public static void apagarLog(Boolean apagamos) {	
		if(apagamos) {
	    	Logger log = Logger.getLogger("org.hibernate");
			log.setLevel(Level.OFF); 
		}
	}
	
	//TODO hibernate: cerrar la conexion una vez cerrado el programa
}
