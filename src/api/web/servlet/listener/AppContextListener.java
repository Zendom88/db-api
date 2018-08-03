package api.web.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import api.db.dao.ibatis.Configurator;
import api.util.sys.PrintInfo;

/**
 * 
 * This Listener to be called at application start-up.
 * This is to initiate needed environment settings and data connections for the app.
 * To be defined in web.xml 
 * 
 * @author Nguyen Tien Duong
 * @since Jul 25, 2018
 *
 */
public class AppContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent arg0) {	
		PrintInfo.out("Initialize servlet context. Start-up server.");
		try {
			Configurator.initialize("app.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		PrintInfo.out("Destroy servlet context. Shutdown is completed.");
	}
}
