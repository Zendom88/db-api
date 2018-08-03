package api.db.dao.vo;

import java.util.List;
import java.util.Map;

public class TableMeta {
	String owner;
	String tableName;
	String filters;
	List<Map<String, Object>> columnMetas;
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner.toUpperCase();
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName.toUpperCase();
	}
	public List<Map<String, Object>> getColumnMetas() {
		return columnMetas;
	}
	public void setColumnMetas(List<Map<String, Object>> columnMetas) {
		this.columnMetas = columnMetas;
	}
	public String getFilters() {
		return filters;
	}
	public void setFilters(String filters) {
		this.filters = filters;
	}
	@Override
	public String toString() {
		return "TableMeta [owner=" + owner + ", tableName=" + tableName + ", filters=" + filters + ", columnMetas="
				+ columnMetas + "]";
	}
	

}
