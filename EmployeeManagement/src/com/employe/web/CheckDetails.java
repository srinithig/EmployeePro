package com.employe.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employe.util.DBUtill;

@WebServlet("/CheckDetails")
public class CheckDetails extends HttpServlet {
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String inputUserId=request.getParameter("Name");
		String inputPassword=request.getParameter("password");
		out.println(inputUserId);
		out.println(inputPassword);	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String data ="select * from Login";
			connection = DBUtill.getConnection();
			preparedStatement = connection.prepareStatement(data);
			resultSet = preparedStatement.executeQuery();
			int flag = 0;
			while(resultSet.next())
			{
				if(inputUserId.equals(resultSet.getObject(1))&&inputPassword.equals(resultSet.getObject(2))) {
					out.print("login Sucess");
					flag = 1;
					break;
				}
				
			}
			javax.servlet.RequestDispatcher rd = null;
			if(flag == 1) {
				out.println("<h1>login successfully</h1>");
				 rd = request.getRequestDispatcher("details.html");
				rd.forward(request, response);
			}
			else {
				out.println("<h1>user invalid</h1>");
				rd=request.getRequestDispatcher("userlogin.html");
				rd.include(request, response);
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

}
