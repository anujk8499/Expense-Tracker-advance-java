package com.servlet;

import java.io.IOException;

import com.dao.ExpenseDao;
import com.db.HibernateUtil;
import com.entity.Expense;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/saveExpense")
public class SaveExpenseServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title=req.getParameter("title");
		String date=req.getParameter("date");
		String time=req.getParameter("time");
		String description=req.getParameter("description");
		String price=req.getParameter("price");
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("loginUser");
		System.out.println( " save expense : " + user);
		Expense ex=new Expense(title , date , time , description , price , user);
		ExpenseDao dao=new ExpenseDao(HibernateUtil.getSessionFactory());
		boolean f=dao.saveExpense(ex);
		if(f) {
			session.setAttribute("msg", "expense added succcessfully...");
			resp.sendRedirect("user/add_expense.jsp");
		}
		else {
			session.setAttribute("msg", "something went wrong...");
			resp.sendRedirect("user/add_expense.jsp");
		}
		
	}
}
