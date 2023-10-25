package com.servlet;

import java.io.IOException;

import org.hibernate.Hibernate;

import com.dao.UserDao;
import com.db.HibernateUtil;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/userRegister")
public class RegisterServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fullName=req.getParameter("fullName");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String about=req.getParameter("about");
		
		User u=new User(fullName, email, password, about);
		UserDao dao=new UserDao(HibernateUtil.getSessionFactory());
		boolean f=dao.saveuser(u);
		HttpSession session=req.getSession();
		
		if(f) {
			System.out.println("register successfully...");
			session.setAttribute("msg", "registration succcessfully...");
			resp.sendRedirect("register.jsp");
		}
		else {
			System.out.println("something went wrong...");
			session.setAttribute("msg", "something went wrong...");
			resp.sendRedirect("register.jsp");
		}
		
	}
	
}
