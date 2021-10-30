package allserv;
import java.sql.*;
import calldatabase.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddBook
 */
@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out=response.getWriter();
			int id=(int)(Math.random()*1000);
			String bname=request.getParameter("bname");
			String author=request.getParameter("author");
			String pub=request.getParameter("pub");
			Connection cn=GetData.getCn();
			PreparedStatement ps=cn.prepareStatement("insert into books values(?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, bname);
			ps.setString(3, author);
			ps.setString(4, pub);
			ps.execute();
			out.print("Book Added...");
			RequestDispatcher rd=request.getRequestDispatcher("add.html");
			rd.include(request, response);
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
