package general;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author insicontratado
 */
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

/**
 * 
 * @author Insicontratado
 */
@SuppressWarnings("serial")
public class ManejarEntidades implements java.io.Serializable {
	

	public Session currentSession() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return session;
	}	
	@SuppressWarnings("unchecked")
	public void incluir(Object objeto)
			throws Exception {
		Transaction tx=null;
		Session sess =null;		
		String entidad = objeto.getClass().toString();
		String resultado = "OK";
		try {
			sess = currentSession();
			tx=sess.beginTransaction();
			sess.persist(objeto);
			//sess.beginTransaction().commit();
			tx.commit();
		} catch (Exception e) {
			resultado = "ERROR";		
			if (tx!=null) tx.rollback();
			throw new Exception("Error al incluir el registro");
		}
		
	}

	@SuppressWarnings("unchecked")
	public void Modificar(Object objeto)
			throws Exception {
		Transaction tx=null;
		Session sess =null;	
		String entidad = objeto.getClass().toString();
		String resultado = "OK";
		try {
			sess = currentSession();
			//sess.beginTransaction();
			tx=sess.beginTransaction();
			sess.update(objeto);
			//sess.beginTransaction().commit();
			tx.commit();
		} catch (Exception e) {
			resultado = "ERROR";			
			if (tx!=null) tx.rollback();
			throw new Exception("Error al modificar el registro");
		} 
	}
	
	

	
	
	
	
	public void Eliminar(String sql)
			throws Exception {
		Transaction tx=null;
		Session sess = null;
		
		//String entidad = objeto.getClass().toString();
		String resultado = "OK";
		try {
			sess = currentSession();
			tx=sess.beginTransaction();
			Query query = sess.createQuery(sql);
			//sess.beginTransaction();
			query.executeUpdate();
			//sess.beginTransaction().commit();
			tx.commit();
		} catch (Exception e) {
			resultado = "ERROR";
			
			if (tx!=null) tx.rollback();
			throw new Exception("Error al eliminar el registro");
		} 
	}
	
	public void ActualizarCampos(String sql)
			throws Exception {
		Transaction tx=null;
		Session sess = null;
		
		//String entidad = objeto.getClass().toString();
		String resultado = "OK";
		try {
			sess = currentSession();
			tx=sess.beginTransaction();
			Query query = sess.createQuery(sql);
			//sess.beginTransaction();
			query.executeUpdate();
			//sess.beginTransaction().commit();
			tx.commit();
		} catch (Exception e) {
			resultado = "ERROR";
			
			if (tx!=null) tx.rollback();
			throw new Exception("Error al eliminar el registro");
		} 
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> Consultar(String Sql)
			throws Exception {
		Session sess = null;
		Transaction tx=null;
		List<Object[]> objeto = null;
		
		String resultado = "OK";
		try {
			sess = currentSession();
			tx=sess.beginTransaction();
			Query q = sess.createQuery(Sql);
			Type[] tipo = q.getReturnTypes();
			q.setMaxResults(1);
			objeto = (List<Object[]>) q.list();
			tx.commit();
			return objeto;
		} catch (Exception e) {
			resultado = "ERROR";
			//sess.beginTransaction().rollback();
			if (tx!=null) tx.rollback();
			throw new Exception("Error al consultar el registro");
		} 
	}	

	@SuppressWarnings("unchecked")	
	
	public List<Object[]> ConsultarLista(String Sql)
			throws Exception {
		Transaction tx=null;
		Session sess = null;
		List<Object[]> ObjetosLista = null;
		String entidad = "";
		
		String resultado = "OK";
		try {
			sess = currentSession();
			tx=sess.beginTransaction();
			//sess.beginTransaction();
			Query q = sess.createQuery(Sql);
			Type[] tipo = q.getReturnTypes();
			entidad = tipo[0].getReturnedClass().toString();
			ObjetosLista = (List<Object[]>) q.list();
			//sess.beginTransaction().commit();
			tx.commit();
			return ObjetosLista;
		} catch (Exception e) {
			resultado = "ERROR";
			
			//sess.beginTransaction().rollback();
			if (tx!=null) tx.rollback();
			throw new Exception("Error al consultar los registros");
		} 
	}
	
	

	
	
}