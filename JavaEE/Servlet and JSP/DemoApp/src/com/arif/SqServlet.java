package com.arif;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SqServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		
		//fetch value here
		//int k=(int)req.getAttribute("k");
		int k=Integer.parseInt(req.getParameter("k"));
		k=k*k;
		
		PrintWriter out = res.getWriter();
		out.println("Result is:" +k);
		
		System.out.print("Here We Are");
	}

}
