package test;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import api.db.dao.vo.TableMeta;

public class TableStructureTest {
	private SqlSession session;
	@Before
	public void setUp() throws Exception {
	    String resource = "api/dao/ibatis/mybatis-config.xml";
	    InputStream inputStream = Resources.getResourceAsStream(resource);
	    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
	            .build(inputStream);

	    session = sqlSessionFactory.openSession();	
	}

	@After
	public void tearDown() throws Exception {
		session.close();
	}

	@Test
	public void test() {
		// To get Data Table Meta: Column Headers and Data Type
		String tableName = "TRANX_TBL";
		tableName = "CMS_AUTH_USER";
		List<Map<String, Object>>  colMetaRecords;
		try {
			colMetaRecords = session.selectList("api.dbAccess.selectTableStruture", tableName);
		} catch (Exception e) {
			fail(e.getMessage());
			return;
		}
		if (colMetaRecords.isEmpty()) {
			fail("Table Not Found: "+ tableName);
			return;
		}
		
		// To form header string for SELECT statement	
		String colString = new String();
		// To Query Data Table using provided meta data.
		TableMeta tableMeta = new TableMeta();
//		tableMeta.setColumnHeaders(colString);
		tableMeta.setTableName(tableName);
		List<Map<String, Object>>  tableObjRecords = session.selectList("api.dbAccess.executeQuery", tableMeta);
		
		for(Map<String, Object>  rec:tableObjRecords) {
			System.out.println(rec.entrySet().toString());
	    }
		
	}

}
