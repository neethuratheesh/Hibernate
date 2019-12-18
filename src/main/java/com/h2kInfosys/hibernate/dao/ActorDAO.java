package com.h2kInfosys.hibernate.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.h2kInfosys.hibernate.dto.ActorDTO;

public class ActorDAO {
	private SessionFactory sessionFactory = null;
	protected void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}
	
	
	public ActorDAO() throws Exception {
		setUp();
	}
	
	public void fetchAllActors() {
		try {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TypedQuery<ActorDTO> query = session.createQuery("from ActorDTO");
		
		List<ActorDTO> actors = query.getResultList();
		for (ActorDTO eachactor:actors) {
			System.out.println("Actor ID : " +eachactor.getActor_ID() + " " +"First Name : " +eachactor.getFirstName() + " " +"Last Name : " +eachactor.getLastName() + " " +"Last Update : " +eachactor.getLastUpdate());
		}
		tx.commit();
		session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//Aggregate methods - "count"
	public void countactors() {
		try {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TypedQuery<ActorDTO> query = (session.createQuery("SELECT count(*) from ActorDTO"));
		Object actorcount = query.getSingleResult();
		
		//List<ActorDTO> actors = query.getResultList();
		//for (ActorDTO eachactor:actors) {
			System.out.println("Total number of actors : " +actorcount);
		//}
		tx.commit();
		session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public ActorDTO fetchAllActorswithActorID(int actorID) {
		ActorDTO eachactor = null;
		try {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TypedQuery<ActorDTO> query = session.createQuery("from ActorDTO AS A where A.actor_ID = :actor_key");
		query.setParameter("actor_key",actorID);
		eachactor = query.getSingleResult();
		
			System.out.println("Actor ID : " +eachactor.getActor_ID() + " " +"First Name : " +eachactor.getFirstName() + " " +"Last Name : " +eachactor.getLastName() + " " +"Last Update : " +eachactor.getLastUpdate());
		
		tx.commit();
		session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return eachactor;
	}

	public void updateActorwithActorID(ActorDTO actor) {
		try {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TypedQuery<ActorDTO> query = session.createQuery("Update ActorDTO AS A  set A.firstName = :firstname where A.actor_ID = :actor_key");
		query.setParameter("actor_key",actor.getActor_ID());
		query.setParameter("firstname",actor.getFirstName());
		int rows = query.executeUpdate();
		System.out.println("Rows Affected: " +rows);
		
			//System.out.println("Actor ID : " +eachactor.getActor_ID() + " " +"First Name : " +eachactor.getFirstName() + " " +"Last Name : " +eachactor.getLastName() + " " +"Last Update : " +eachactor.getLastUpdate());
		
		tx.commit();
		session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateActorwithsession(ActorDTO actor) {
		try {
		Session session = sessionFactory.openSession();
		//Transaction tx = session.beginTransaction();
		session.update(actor);
		//TypedQuery<ActorDTO> query = session.createQuery("Update ActorDTO AS A  set A.firstName = :firstname where A.actor_ID = :actor_key");
		//query.setParameter("actor_key",actor.getActor_ID());
		//query.setParameter("firstname",actor.getFirstName());
		//int rows = query.executeUpdate();
		System.out.println("Object updated successfully");
		
			//System.out.println("Actor ID : " +eachactor.getActor_ID() + " " +"First Name : " +eachactor.getFirstName() + " " +"Last Name : " +eachactor.getLastName() + " " +"Last Update : " +eachactor.getLastUpdate());
		
		//tx.commit();
		session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveorupdatewithsession(ActorDTO actor) {
		try {
		Session session = sessionFactory.openSession();
		//Transaction tx = session.beginTransaction();
		session.saveOrUpdate(actor);
		
		//TypedQuery<ActorDTO> query = session.createQuery("Update ActorDTO AS A  set A.firstName = :firstname where A.actor_ID = :actor_key");
		//query.setParameter("actor_key",actor.getActor_ID());
		//query.setParameter("firstname",actor.getFirstName());
		//int rows = query.executeUpdate();
		System.out.println("Object save or update successfully");
		
			//System.out.println("Actor ID : " +eachactor.getActor_ID() + " " +"First Name : " +eachactor.getFirstName() + " " +"Last Name : " +eachactor.getLastName() + " " +"Last Update : " +eachactor.getLastUpdate());
		
		//tx.commit();
		session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteActorwithActorID(int actorid) {
		try {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TypedQuery<ActorDTO> query = session.createQuery("delete ActorDTO AS A where A.actor_ID = :actor_key");
		query.setParameter("actor_key",actorid);
		//query.setParameter("firstname",actor.getFirstName());
		int rows = query.executeUpdate();
		System.out.println("Rows Affected: " +rows);
		
			//System.out.println("Actor ID : " +eachactor.getActor_ID() + " " +"First Name : " +eachactor.getFirstName() + " " +"Last Name : " +eachactor.getLastName() + " " +"Last Update : " +eachactor.getLastUpdate());
		
		tx.commit();
		session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

public static void main(String[] args) {
	try {
		ActorDAO actordao = new ActorDAO();
		//actordao.fetchAllActors();
		ActorDTO actor = actordao.fetchAllActorswithActorID(20);
		//ActorDTO actor = new ActorDTO();
		actor.setFirstName("Lucifer");
		actor.setLastName("Mathew");
		actordao.updateActorwithsession(actor);
		
		ActorDTO newactor = new ActorDTO();
		newactor.setFirstName("Neil");
		newactor.setLastName("Ratheesh");
		newactor.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		actordao.saveorupdatewithsession(newactor);
		actordao.countactors();
		//actordao.updateActorwithActorID(actor);
		//actordao.deleteActorwithActorID(201);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
