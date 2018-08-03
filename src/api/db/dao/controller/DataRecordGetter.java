package api.db.dao.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import api.db.dao.ibatis.Configurator;
import api.db.dao.vo.TableMeta;
import api.util.sys.PrintInfo;

public class DataRecordGetter {

	public static List<Map<String, Object>> fetch(String dbObjName, String filters) {
		SqlSession session;
		List<Map<String, Object>> dbResults;
		TableMeta tableMetaObj;
		String defaultOwner = Configurator.getDbLoginId();
		
		if (dbObjName==null || dbObjName.isEmpty()) return null;
		PrintInfo.out("Session opened.");
		session = Configurator.getSqlSessionFactory().openSession();
		
		tableMetaObj = createTableMetaObj(session, defaultOwner, dbObjName, filters);
		dbResults = getDbObjectRecords(session, tableMetaObj);
		if (tableMetaObj==null) return null;
		
		session.close();
		PrintInfo.out("Session closed.");
		return dbResults;

	}
	
	public static List<Map<String, Object>> fetch(String dbObjName) {
		SqlSession session;
		List<Map<String, Object>> dbMetaData;
		TableMeta tableMetaObj;
		String defaultOwner = Configurator.getDbLoginId();
		
		if (dbObjName==null || dbObjName.isEmpty()) return null;
		
		session = Configurator.getSqlSessionFactory().openSession();
		PrintInfo.out("Session opened.");
		
		tableMetaObj = createTableMetaObj(session, defaultOwner, dbObjName,"");	
		dbMetaData = getDbObjectColumnsMeta(session, tableMetaObj);
		
		if (tableMetaObj==null || dbMetaData == null) return null;

		session.close();
		PrintInfo.out("Session closed.");
		return dbMetaData;

	}	
	
	private static TableMeta createTableMetaObj(SqlSession session, String defaultOwner, String dbObjName, String filters) {
		TableMeta tableMeta = new TableMeta();
		String[] dbObjectNameParts = dbObjName.split("\\."); //in case provides schema: SCHEMA.OBJ_NAME
		String dbObjectName = dbObjectNameParts[dbObjectNameParts.length -1 ];
		String owner = (dbObjectNameParts.length==1)? defaultOwner : dbObjectNameParts[dbObjectNameParts.length-2] ;		
		
		tableMeta.setOwner(owner);
		tableMeta.setTableName(dbObjectName);
		
		List<Map<String, Object>> columnMetas = getDbObjectColumnsMeta(session, tableMeta);
				
		if (columnMetas == null || columnMetas.isEmpty()) {
			return null;
		} else {
			tableMeta.setColumnMetas(columnMetas);
		}
		
		tableMeta.setFilters(filters);
		return tableMeta;
	}


	
	private static List<Map<String, Object>> getDbObjectColumnsMeta(SqlSession session, TableMeta tableMeta) {
		List<Map<String, Object>> dbColumnMetas =  session.selectList("api.dbAccess.selectTableStruture", tableMeta);
		if (dbColumnMetas == null || dbColumnMetas.isEmpty()) {
			return null;
		}
		return dbColumnMetas;
	}
	

	/**
	 * 
	 * @param session
	 * @param tableName
	 * @param colString
	 * @return DB Data in List of HashMaps
	 */
	private static List<Map<String, Object>> getDbObjectRecords(SqlSession session, TableMeta tableMeta) {
		// To Query Data Table using provided meta data.
		if(tableMeta==null || session == null) return null;
		
		List<Map<String, Object>> dbResults = session.selectList("api.dbAccess.executeQuery", tableMeta);
		return dbResults;
	}
}
