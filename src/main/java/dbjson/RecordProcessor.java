package dbjson;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecordProcessor implements ResultSetHandler<JSONObject> {

	JSONArray jobject = new JSONArray();
	
	@Override
	public JSONObject handle(ResultSet rs) throws SQLException {
		
		ResultSetMetaData rsmd= rs.getMetaData();
		while(rs.next()) {
			JSONObject arr = new JSONObject();
			for(int i = 1; i < rsmd.getColumnCount(); i++) {
				if(rsmd.getColumnClassName(i).toLowerCase().contains("short")) {
					arr.put(rsmd.getColumnName(i), rs.getShort(i));
				} else if(rsmd.getColumnClassName(i).toLowerCase().contains("int")) {
					arr.put(rsmd.getColumnName(i), rs.getInt(i));
				}  else if(rsmd.getColumnClassName(i).toLowerCase().contains("string")) {
					arr.put(rsmd.getColumnName(i), rs.getString(i));
				} 
			}
			jobject.put(arr);
		}
		//return jobject;
		return new JSONObject().put("results", jobject);
	}

}
