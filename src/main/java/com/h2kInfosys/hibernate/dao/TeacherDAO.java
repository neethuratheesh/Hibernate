package com.h2kInfosys.hibernate.dao;
import com.h2kInfosys.hibernate.dto.TeacherDTO;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class TeacherDAO {
	
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
	
	public TeacherDAO() throws Exception {
		setUp();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			TeacherDAO teacherdao = new TeacherDAO();
			teacherdao.fetchAllTeachers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void fetchAllTeachers() {
		Session session = sessionFactory.openSession();
		TypedQuery<TeacherDTO> query = session.createQuery("from TeacherDTO");
		List<TeacherDTO> teachers = query.getResultList();
		for (TeacherDTO eachTeacher:teachers) {
			System.out.println("TEACHER ID : " +eachTeacher.getTeacherID() + " " +"First Name : " +eachTeacher.getFirstName() + " " +"Last Name : " +eachTeacher.getLastName() + " " +"Skill : " +eachTeacher.getSkill());
		}
	}

}
