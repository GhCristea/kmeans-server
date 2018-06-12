package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.Attribute;

/**
 * Class rappresenting the schema of database's table.
 * 
 * @author Cristea Gheorghita
 *
 */
public class TableSchema {
	
	DbAccess database;

	/**
	 * Inner class rappresenting a collumn of database's table.
	 * 
	 * @author Cristea Gheorghita
	 *
	 */
	public class Column {

		private String name;
		private String type;

		/**
		 * Class constructor.
		 * @param setName name of the the {@link Column}
		 * @param setType type of {@link Column}
		 */
		Column(final String setName, final String setType) {
			this.name = setName;
			this.type = setType;
		}

		/**
		 * Getter for name of {@link Column}.
		 * @return name of {@link Column}
		 */
		public String getColumnName() {
			return name;
		}

		/**
		 * Decide if the type of {@link Column} is numeric type.
		 * @return true if the type is numeric.
		 */
		public boolean isNumber() {
			return type.equals("number");
		}

		/**
		 * @return name + type.
		 */
		public String toString() {
			return name + ":" + type;
		}
	}

	List<Column> tableSchema = new ArrayList<Column>();

	/**
	 * Class constructor.
	 * @param inputDatabase input databse. 
	 * @param inputTableName table name.
	 * @throws SQLException
	 */
	public TableSchema(DbAccess inputDatabase, String inputTableName) throws SQLException {
		this.database = inputDatabase;
		HashMap<String, String> mapSQL_JAVATypes = new HashMap<String, String>();
		// http://java.sun.com/j2se/1.3/docs/guide/jdbc/getstart/mapping.html
		mapSQL_JAVATypes.put("CHAR", "string");
		mapSQL_JAVATypes.put("VARCHAR", "string");
		mapSQL_JAVATypes.put("LONGVARCHAR", "string");
		mapSQL_JAVATypes.put("BIT", "string");
		mapSQL_JAVATypes.put("SHORT", "number");
		mapSQL_JAVATypes.put("INT", "number");
		mapSQL_JAVATypes.put("LONG", "number");
		mapSQL_JAVATypes.put("FLOAT", "number");
		mapSQL_JAVATypes.put("DOUBLE", "number");

		Connection con = inputDatabase.getConnection();
		DatabaseMetaData meta = con.getMetaData();
		ResultSet res = meta.getColumns(null, null, inputTableName, null);

		while (res.next()) {

			if (mapSQL_JAVATypes.containsKey(res.getString("TYPE_NAME")))
				tableSchema.add(
						new Column(res.getString("COLUMN_NAME"), mapSQL_JAVATypes.get(res.getString("TYPE_NAME"))));

		}
		res.close();

	}

	/**
	 * Gets the number of {@link Attribute}.
	 * @return size of {@link Attribute}'s schema.
	 */
	public int getNumberOfAttributes() {
		return tableSchema.size();
	}

	/**
	 * Returns a {@link Column} indexed at index.
	 * @param index index of {@link Column}
	 * @return
	 */
	public Column getColumn(int index) {
		return tableSchema.get(index);
	}

}
