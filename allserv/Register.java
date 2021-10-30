package allserv;
import java.sql.*;
import calldatabase.GetData;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out=response.getWriter();
			int uid=(int)(Math.random()*10000);
			String upass=request.getParameter("upass");
			String upass1=request.getParameter("upass1");
			String uname=request.getParameter("uname");
			String uaddr=request.getParameter("uaddr");
			String uemail=request.getParameter("uemail");
			String uphn=request.getParameter("uphn");
			String ugen=request.getParameter("ugen");
			
			Connection cn=GetData.getCn();
			PreparedStatement ps=cn.prepareStatement("insert into user values(?,?,?,?,?,?,?,?)");
			ps.setInt(1, uid);
			ps.setString(2, uname);
			ps.setString(3, uaddr);
			ps.setString(4, uphn);
			ps.setString(5, uemail);
			ps.setString(6,ugen);
			ps.setString(7, upass);
			ps.setString(8, upass1);
			
			ps.execute();
			out.print("Registration success for ID :"+uid);
			RequestDispatcher rd=request.getRequestDispatcher("UserLogin.html");
			rd.include(request, response);
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
