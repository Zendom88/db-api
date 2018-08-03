package api.db.dao.ibatis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

import javax.print.attribute.standard.PrinterInfo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import api.util.sys.PrintInfo;

public class Configurator {
	
	private static String jdbcDriver;
	private static String jdbcUrl;
	private static String jdbcUserName;
	private static String jdbcPassword;
	
	protected static SqlSessionFactory sqlSessionFactory;
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	public static void initialize(String filePath) throws Exception {
		InputStream inputStream = Resources.getResourceAsStream(filePath);
		Properties properties = new Properties();

		//Read from app.properties file
		try {
			properties.load(inputStream);
			jdbcDriver = (String) properties.get("driver");
			jdbcUrl = (String) properties.get("jdbcURL");
			jdbcUserName = (String) properties.get("username");
			jdbcPassword = (String) properties.get("password");
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Set values from app.properties to Config map 
		if (sqlSessionFactory == null) {
			try {
				String resource = "api/db/dao/ibatis/db-config.xml";
				InputStream ipStream = Resources.getResourceAsStream(resource);
				Properties pro = new Properties();
				pro.setProperty("driver", jdbcDriver);
				pro.setProperty("jdbcURL", jdbcUrl);
				pro.setProperty("username", jdbcUserName);
				pro.setProperty("password", jdbcPassword);
				sqlSessionFactory = new SqlSessionFactoryBuilder()
			            				.build(ipStream, pro);
			    PrintInfo.out("Connection Opened.");

				} catch (Exception ex) {
					throw ex;
				}
		}
		
		
	}

	public static String getDbLoginId() {
		return jdbcUserName;
	}
}
