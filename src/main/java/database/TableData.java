package database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import database.TableSchema.Column;

public class TableData {

	DbAccess database;

	public TableData(DbAccess dbIn) {
		this.database = dbIn;
	}

	public List<Example> getDistinctTransazioni(String tableName) throws SQLException, EmptySetException {
		List<Example> distinctTransaction = new LinkedList<Example>();
		TableSchema schema = new TableSchema(database, tableName);
		Statement statment = database.getConnection().createStatement();
		ResultSet resultSet = statment.executeQuery("SELECT DISTINCT * FROM MapDB." + tableName);
		while (resultSet.next()) {
			Example row = new Example();
			for (int i = 1; i <= schema.getNumberOfAttributes(); i++) {
				if (schema.getColumn(i - 1).isNumber()) {
					row.add(resultSet.getDouble(i));
				} else {
					row.add(resultSet.getString(i));
				}
			}
			distinctTransaction.add(row);
		}
		statment.close();
		return distinctTransaction;
	}

	public Set<Object> getDistinctColumnValues(String tableName, Column column) throws SQLException {
		TreeSet<Object> distinctColumn = new TreeSet<Object>();
		Statement statement = database.getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT " + column.getColumnName() + " from MapDb." + tableName);
		if (column.isNumber()) {
			while (resultSet.next()) {
				distinctColumn.add(resultSet.getDouble(1));
			}
		} else {
			while (resultSet.next()) {
				distinctColumn.add(resultSet.getString(1));
			}
		}
		statement.close();
		return distinctColumn;
	}

	public Object getAggregateColumnValue(String tableName, Column column, QUERY_TYPE aggregate)
			throws SQLException, NoValueException {
		Statement statement = database.getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT " + aggregate + "(" + column.getColumnName() + ") from MapDb." + tableName);
		resultSet.next();
		Object resultedObject = resultSet.getObject(1);
		statement.close();
		return resultedObject;
	}

}
