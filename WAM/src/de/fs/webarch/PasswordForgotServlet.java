package de.fs.webarch;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fs.webarch.serialize.UserDAO;

/**
 * Servlet implementation class PasswordForgotServlet
 */
@WebServlet("/PasswordForgot")
public class PasswordForgotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordForgotServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String nutzer = request.getParameter("Nutzer");
		String pw = request.getParameter("Passwort");
		String pwwdh = request.getParameter("Passwortwdh");
		
		if(pw!="" && pwwdh!="" && pw.equals(pwwdh)) {
			UserDAO.instance.forgot(nutzer, pw);
			response.sendRedirect("Login.jsp");
			
		}else response.sendRedirect("LoginFehler.jsp");
	}

}
