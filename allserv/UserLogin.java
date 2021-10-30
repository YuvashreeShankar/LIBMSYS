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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out=response.getWriter();
			String id=request.getParameter("uid");
			String pass=request.getParameter("upass");
			Connection cn=GetData.getCn();
			PreparedStatement ps=cn.prepareStatement("select uname,uid from user where uid=? and upass=? ");
			ps.setString(1,id);
			ps.setString(2,pass);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				HttpSession ses=request.getSession();
				ses.setAttribute("name",rs.getString(1));
				HttpSession ses1=request.getSession();
				ses1.setAttribute("uid",rs.getString(2));
				RequestDispatcher rd=request.getRequestDispatcher("books.html");
				rd.forward(request, response);
			}else {
				out.print("wrong userid or password");
				RequestDispatcher rd=request.getRequestDispatcher("UserLogin.html");
				rd.include(request, response);
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}

