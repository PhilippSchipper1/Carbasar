package de.fs.webarch;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fs.webarch.serialize.ProbefahrtDAO;

/**
 * Servlet implementation class ProbefahrtServlet
 */
@WebServlet("/Probefahrt")
public class ProbefahrtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProbefahrtServlet() {
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
		int id = (Integer) request.getSession().getAttribute("userId");
		
		if(request.getParameter("termin") == null) {
			int auto_id = Integer.parseInt((String) request.getParameter("auto_id"));
			String datum = request.getParameter("drive-start");
			
			ProbefahrtDAO.instance.getProbefahrt(id);
			String value =ProbefahrtDAO.instance.termin(auto_id, id, datum);
			
			
			request.setAttribute("termin", value);
			request.getRequestDispatcher("AutoDetail.jsp?auto_id=" + auto_id).forward(request, response);
		} else {
		    ProbefahrtDAO.instance.getTerminKunde(id);
		    ProbefahrtDAO.instance.getTerminBesitzer(id);
	    
		    String termin= request.getParameter("termin");
		    int probe_id= Integer.parseInt(request.getParameter("probe_id"));
		    ProbefahrtDAO.instance.validate(termin, probe_id);
		    response.sendRedirect("VerkauferMain.jsp"); 
		}
	}

}
