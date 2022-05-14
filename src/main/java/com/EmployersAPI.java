package com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class EmployersAPI
 */
@WebServlet("/EmployersAPI")   
public class EmployersAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Employer empObj = new Employer();    

 
    public EmployersAPI() {
        super();
    }

    // Convert request parameters to a Map
 	private static Map getParasMap(HttpServletRequest request)
 	{
 	Map<String, String> map = new HashMap<String, String>();
 	try
 	{
 	Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
 	String queryString = scanner.hasNext() ?
 	scanner.useDelimiter("\\A").next() : "";
 	scanner.close();
 	String[] params = queryString.split("&");
 	for (String param : params)
 	{String[] p = param.split("=");
 	map.put(p[0], p[1]);
 	}
 	}
 	catch (Exception e)
 	{
 	}
 	return map;
 	}
 	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = empObj.insertEmployer(
				request.getParameter("nic"),
				request.getParameter("name"),
				request.getParameter("address"),
				request.getParameter("dept"),
				request.getParameter("contact"));
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = empObj.updateEmployer(paras.get("hidEmployerIDSave").toString(),
		paras.get("nic").toString(),
		paras.get("name").toString(),
		paras.get("address").toString(),
		paras.get("dept").toString(),
		paras.get("contact").toString());
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = empObj.deleteEmployer(paras.get("empid").toString());
		response.getWriter().write(output);
	}

}
