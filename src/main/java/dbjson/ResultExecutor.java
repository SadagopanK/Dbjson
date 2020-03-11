package dbjson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.json.JSONObject;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class ResultExecutor {

	public static void main(String[] args) {

		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=AdventureWorks2014;user=sa;password=Root123";
		String SQL = "SELECT * FROM HumanResources.Department";
		Connection con;
		MapListHandler handler = new MapListHandler();	
		try {
			con = DriverManager.getConnection(connectionUrl);

			JSONObject list = new QueryRunner().query(con, SQL, new RecordProcessor());
			Object document = Configuration.defaultConfiguration().jsonProvider().parse(list.toString());
			System.out.println(list);
			
			//List<String> groupNames = JsonPath.read(document, "$.results[*].GroupName");
			//System.out.println(JsonPath.read(document, "$.results[*].DepartmentID").toString());
			//System.out.println(JsonPath.read(document, "$.results[?(@.DepartmentID)]").toString());
			//System.out.println(JsonPath.read(document, "$.results[?(@.DepartmentID)]").toString());
			//System.out.println(JsonPath.read(document, "$.results[?(@.DepartmentID <= 5)]").toString());
			System.out.println(JsonPath.read(document, "$.results.length()").toString());
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class DTO<T, U> {

	T name;
	U value;

	public DTO(T name, U value) {

	}

	public T getName() {
		return name;
	}

	public U getValue() {
		return value;
	}

}
