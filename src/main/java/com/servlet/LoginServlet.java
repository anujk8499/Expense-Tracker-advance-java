package com.servlet;

import java.io.IOException;

import com.dao.UserDao;
import com.db.HibernateUtil;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		System.out.println(email + " " + password);
		UserDao dao=new UserDao(HibernateUtil.getSessionFactory());
		User u=dao.login(email, password);
		HttpSession session=req.getSession();
		if(u==null) {
			session.setAttribute("msg", "invalid username and password");
			resp.sendRedirect("login.jsp");
		}
		else {
			session.setAttribute("loginUser", u);
			User u1=(User)session.getAttribute("loginUser");
			System.out.println(u1);
			resp.sendRedirect("user/home.jsp");
		}
		
	}
}
