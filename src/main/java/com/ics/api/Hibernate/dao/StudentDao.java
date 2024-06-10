package com.ics.api.Hibernate.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ics.api.Hibernate.entity.Student;
import com.ics.api.Hibernate.util.HibernateUtil;



public class StudentDao {
	public void saveStudent(Student student) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(student);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	 public List<Student> getStudents() {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            return session.createQuery("from Student", Student.class).list();
	        }
	    }
	 
	 
	    public Student getStudent(String firstName) {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            return session.get(Student.class, firstName);
	        }
	    }
	 
	    public void updateStudent(Student student) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();
	            session.update(student);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }
	    
	    public void deleteStudentName(String firstName, String lastName) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();
	            String hql = "from Student where firstName = :firstName and lastName = :lastName";
	            List<Student> students = session.createQuery(hql, Student.class)
	                                            .setParameter("firstName", firstName)
	                                            .setParameter("lastName", lastName)
	                                            .list();
	            for (Student student : students) {
	                session.delete(student);
	            }
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }
	 
}
