package de.fs.webarch;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.fs.webarch.serialize.FrageDAO;
import de.fs.webarch.serialize.User;
import de.fs.webarch.serialize.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("logout").equals("1")) {
			request.getSession().removeAttribute("user");
			request.getSession().invalidate();
		}
		response.sendRedirect("Startseite.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("Benutzer");
		String pwort = request.getParameter("Passwort");
		
		HttpSession session = request.getSession();
		
		if(UserDAO.LoginCheck(uname, pwort)) {
			User u = UserDAO.instance.getUserByName(uname);
			System.out.println(u.id);
			session.setAttribute("user", uname);
			session.setAttribute("userId", u.id);
			response.sendRedirect("BesucherTyp.jsp");
		}
		else {
			response.sendRedirect("LoginFehler.jsp");
		}
	}

}
