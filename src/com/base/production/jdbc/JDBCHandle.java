package com.base.production.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.base.production.domain.Table;

public class JDBCHandle {
	private static final String BASE_SQL = "SELECT * FROM %s";

	public static Table getColumns(Table table, Connection conn) {
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		List<Map<Object, Object>> reList = new ArrayList<Map<Object, Object>>();
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(String.format(BASE_SQL, table.getTableName()));
			ResultSetMetaData rsmd = rs.getMetaData();
			Map<Object, Object> fields = null;
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				fields = new HashMap<Object, Object>();
				String name = rsmd.getColumnName(i);
				String type = change(rsmd.getColumnType(i));
				fields.put("name", name);
				fields.put("type", type);

				fields.put("isPK", false);
				fields.put("isFK", false);
				list.add(fields);
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			JDBCTemplate.close(rs);
		}
		DatabaseMetaData dbmd;
		try {
			dbmd = conn.getMetaData();
			ResultSet rPrimaryKey;
			String databaseName = dbmd.getDatabaseProductName().toUpperCase();
			if (databaseName.indexOf("ORACLE") != -1) {
				rPrimaryKey = dbmd.getPrimaryKeys(null, JDBCTemplate.USERNAME.toUpperCase(), table.getTableName());
			} else {
				rPrimaryKey = dbmd.getPrimaryKeys(null, null, table.getTableName());
			}
			while (rPrimaryKey.next()) {

				String pk = rPrimaryKey.getString(4);
				for (int i = 0; i < list.size(); i++) {
					Map<Object, Object> map = (Map<Object, Object>) list.get(i);

					if (pk.equals(map.get("name"))) {

						Map<Object, Object> mapPK = new HashMap<Object, Object>();
						mapPK.put("name", map.get("name"));
						mapPK.put("type", map.get("type"));
						table.setPkName( (String)map.get("name"));
						mapPK.put("isPK", true);
						mapPK.put("isFK", false);
						reList.add(mapPK);
						list.remove(map);
						i = list.size() + 1;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(stmt, conn);
		}
		reList.addAll(list);
		table.setList(reList);
		return table;
	}

	private static String change(int type) {

		switch (type) {
		case Types.ARRAY:
			return "java.sql.Array";
		case Types.BIGINT:
			return "Long";
		case Types.BINARY:
			return "byte[]";
		case Types.BIT:
			return "boolean";
		case Types.BLOB:
			return "java.sql.Blob";
		case Types.BOOLEAN:
			return "boolean";
		case Types.CHAR:
			return "String";
		case Types.CLOB:
			return "java.sql.Clob";
		case Types.DATALINK:
			return "Float";
		case Types.DATE:
			return "java.sql.Date";
		case Types.DECIMAL:
			return "BigDecimal";
			// case Types.DISTINCT:
			// return "";
		case Types.DOUBLE:
			return "Float";
		case Types.FLOAT:
			return "Float";
		case Types.INTEGER:
			return "Integer";
			// case Types.JAVA_OBJECT:
			// return "";
		case Types.LONGNVARCHAR:
			return "String";
		case Types.LONGVARBINARY:
			return "byte[]";
		case Types.LONGVARCHAR:
			return "String";
		case Types.NCHAR:
			return "String";
		case Types.NCLOB:
			return "java.sql.NClob";
		case Types.NULL:
			return "";
		case Types.NUMERIC:
			return "java.math.BigDecimal";
		case Types.NVARCHAR:
			return "String";
			// case Types.OTHER:
			// return "";
			// case Types.REAL:
			// return "";
			// case Types.REF:
			// return "";
			// case Types.ROWID:
			// return "";
		case Types.SMALLINT:
			return "short";
			// case Types.SQLXML:
			// return "";
			// case Types.STRUCT:
			// return "";
		case Types.TIME:
			return "java.sql.Time";
		case Types.TIMESTAMP:
			return "Date";
		case Types.TINYINT:
			return "byte";
		case Types.VARBINARY:
			return "byte[]";
		case Types.VARCHAR:
			return "String";
		default:
			return "String";
		}
	}
}
