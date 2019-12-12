package de.fs.webarch;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;



/**
 * Application Lifecycle Listener implementation class DbPmPlusContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

	
	private static Properties passwords=new Properties();
	private static DataSource dataSource = null;

	/**
	 * Default constructor. 
	 */
	public ContextListener() {
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0)  { 
		System.out.println("und tschuess...");
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event)  { 
		System.getProperties().setProperty("Dorg.apache.cxf.stax.allowInsecureParser", "1");
		System.out.println("sys prop set");

		ServletContext servletContext = event.getServletContext();
		 dataSource = getDBConnection(servletContext);
		
	}

	


	public static boolean isUserAuthentic(String username, String passwordFromForm) {
		String realPwd=passwords.getProperty(username,null);

		if(passwordFromForm!=null && passwordFromForm.equals(realPwd)) {
			return true;
		}

		return false;

	}

	public static boolean isUserAuthentic(ServletContext servletContext, String username, String passwordFromForm) {
		//passwords=readPasswordFile(servletContext);
		return isUserAuthentic(username, passwordFromForm);
	}
	public static DataSource getDBConnection(ServletContext servletContext) {
		DataSource ds=null;
		try {
			javax.naming.Context initCont = new InitialContext();
			javax.naming.Context envCont = (javax.naming.Context) initCont.lookup("java:comp/env");
			ds= (DataSource) envCont.lookup("jdbc/Carbasar");
			System.out.println("Connected");
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		return ds;
		
	}
	public static DataSource getDataSource() {
		return dataSource;
	}
}