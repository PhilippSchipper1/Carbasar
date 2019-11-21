package de.fs.webarch;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fs.webarch.serialize.FrageDAO;
import de.fs.webarch.serialize.UserDAO;

/**
 * Servlet implementation class FrageStellenServlet
 */
@WebServlet("/FrageStellenServlet")
public class FrageStellenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrageStellenServlet() {
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
		String frage = request.getParameter("Frage");
		int auto_id = Integer.parseInt(request.getParameter("autoid"));
		int id = (Integer) request.getSession().getAttribute("userId");

		UserDAO.instance.frage_stellen(id, auto_id, frage);
		FrageDAO.instance.getFrage(id);
	}

}
