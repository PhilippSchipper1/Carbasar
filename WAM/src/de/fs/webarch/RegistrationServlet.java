package de.fs.webarch;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fs.webarch.serialize.UserDAO;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/Registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
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
		String uname = request.getParameter("Benutzer");
		String email = request.getParameter("E-Mail");
		String pwort = request.getParameter("Passwort");
		String pwortwdh = request.getParameter("Passwort_wdh");
		
		if(uname!="" && email!="" && pwort !="" && pwortwdh !="" && pwort.equals(pwortwdh)) {
			
				UserDAO.instance.registrieren(uname, pwort, email);
				response.sendRedirect("BesucherTyp.jsp");	
		}
		else response.sendRedirect("LoginFehler.jsp");
		
		
	}

}
