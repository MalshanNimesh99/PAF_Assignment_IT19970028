package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employer {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	//*************************read employers***********************************************
	public String readEmployers() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table><tr><th style=width:100px; >NIC</th>"
					+ "<th style=width:100px; >Name</th>"
					+ "<th style=width:100px; >Address</th>"
					+ "<th style=width:100px; >Department</th>"
					+ "<th style=width:100px; >Contact</th>"
					+ "<th style=width:50px; >Update</th>"
					+ "<th style=width:50px; >Remove</th></tr>";

			String query = "select * from employers";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String empid = Integer.toString(rs.getInt("empid"));
				String nic = rs.getString("nic");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String dept = rs.getString("dept");
				String contact = rs.getString("contact");
// Add into the html table
				output += "<tr><td><input id='hidEmployerIDUpdate'name='hidEmployerIDUpdate'type='hidden' value='" + empid
						+ "'>" + nic + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + dept + "</td>";
				output += "<td>" + contact + "</td>";
// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'>"
						+ "</td><td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-empid='"+empid+"'></td></tr>";
			}
			con.close();
// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading employers.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//***********************************insert employers****************************************
public String insertEmployer(String nic, String name, String address, String dept, String contact)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for inserting.";
}
// create a prepared statement
String query = " insert into employers(`empid`,`nic`,`name`,`address`,`dept`,`contact`) values (?, ?, ?, ?, ?, ?)";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, 0);
preparedStmt.setString(2, nic);
preparedStmt.setString(3, name);
preparedStmt.setString(4, address);
preparedStmt.setString(5, dept);
preparedStmt.setString(6, contact);
// execute the statement
preparedStmt.execute();
con.close();
String newEmployers = readEmployers();
output = "{\"status\":\"success\", \"data\": \"" +
newEmployers + "\"}";
}
catch (Exception e)
{
output = "status:error ,data:Error while inserting employer.";
System.err.println(e.getMessage());
}
return output;
}

//***********************************update employers****************************************
public String updateEmployer(String empid,String nic, String name, String address, String dept, String contact)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for updating.";
}
// create a prepared statement
String query = "UPDATE employers SET nic=?,name=?,address=?,dept=?,contact=? WHERE empid=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setString(1, nic);
preparedStmt.setString(2, name);
preparedStmt.setString(3, address);
preparedStmt.setString(4, dept);
preparedStmt.setString(5, contact);
preparedStmt.setInt(6, Integer.parseInt(empid));
// execute the statement
preparedStmt.execute();
con.close();
String newEmployers = readEmployers();
output = "{\"status\":\"success\", \"data\": \"" +
newEmployers + "\"}";
}
catch (Exception e)
{
output = "status:error data Error while updating the employer.";
System.err.println(e.getMessage());
}
return output;
}



//***********************************delete employers****************************************

public String deleteEmployer(String empid)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for deleting.";
}
// create a prepared statement
String query = "delete from employers where empid=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, Integer.parseInt(empid));
// execute the statement
preparedStmt.execute();
con.close();
String newEmployers = readEmployers();
output = "{\"status\":\"success\", \"data\": \"" +
newEmployers + "\"}";
}
catch (Exception e)
{
output = "status:error data :Error while deleting employer.";
System.err.println(e.getMessage());
}
return output;
}
}