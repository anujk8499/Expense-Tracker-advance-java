package com.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.User;

public class UserDao {
	private SessionFactory factory=null;
	private Session session=null;
	private Transaction tx=null;

	public UserDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}

	public boolean saveuser(User user) {
		boolean f=false;
		try {
			session =factory.openSession();
			tx=session.beginTransaction();
			session.save(user);
			tx.commit();
			f=true;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return f;
	}
	
	public User login(String email , String password) {
		User u=null;
		
		try {
			session=factory.openSession();
			System.out.println("reached to this line0");
			Query q= session.createQuery("from 	User where email=:em and password=:ps");
			System.out.println("reached to this line1");
			q.setParameter("em", email);
			q.setParameter("ps", password);
			System.out.println("reached to this line2");
			  u=(User)q.uniqueResult();
			  System.out.println("reached to this line3");
		} catch (Exception e) {
			System.out.println( "error occured : " +  e.getMessage());
		}
		return u;
	}
	
	
	
}
