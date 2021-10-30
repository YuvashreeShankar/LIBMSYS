package allserv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import calldatabase.*;

/**
 * Servlet implementation class DeleteBook
 */
@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out=response.getWriter();
			String id=request.getParameter("id");
			Connection cn=GetData.getCn();
			PreparedStatement ps=cn.prepareStatement("select * from books where id=?");
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				PreparedStatement ps1=cn.prepareStatement("delete from books where id=?");
				ps1.setString(1, id);
				ps1.execute();
				out.print("Record Deleted...");
				RequestDispatcher rd=request.getRequestDispatcher("Admin_home.html");
				rd.include(request, response);
			}else {
				out.print("no record found");
				RequestDispatcher rd=request.getRequestDispatcher("delete.html");
				rd.include(request, response);
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
