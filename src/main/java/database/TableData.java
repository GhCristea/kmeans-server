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

/**
 * Class to parse data from the database.
 * 
 * @author Cristea Gheorghita
 *
 */
public class TableData {

	DbAccess database;

	/**
	 * Class constructor.
	 * 
	 * @param dbIn
	 *            input database.
	 */
	public TableData(final DbAccess dbIn) {
		this.database = dbIn;
	}

	/**
	 * Gets the list of distincts {@link Example}s.
	 * 
	 * @param tableName
	 *            name of the datase's table
	 * @return the list of distincts {@link Example}s.
	 * @throws SQLException
	 * @throws EmptySetException
	 */
	public List<Example> getDistinctTransactions(final String tableName) throws SQLException, EmptySetException {
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

	/**
	 * Extract the sorted distinct values of column and populate a set to be
	 * returned.
	 * 
	 * @param tableName
	 *            name of database table.
	 * @param column
	 *            name of collumn.
	 * @return the the set of (distinct) values.
	 * @throws SQLException
	 */
	public Set<Object> getDistinctColumnValues(final String tableName, final Column column) throws SQLException {
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

	/**
	 * Extract the aggregate value (minimum or maximum value) searched in the column
	 * named column of the table named table.
	 * 
	 * @param tableName
	 *            name of table.
	 * @param column
	 *            name of collum.
	 * @param aggregate
	 *            aggregation operator SQL (min,max)
	 * @return searched aggregation
	 * @throws SQLException
	 * @throws NoValueException
	 */
	public Object getAggregateColumnValue(final String tableName, final Column column, final QUERY_TYPE aggregate)
			throws SQLException, NoValueException {
		Statement statement = database.getConnection().createStatement();
		ResultSet resultSet = statement
				.executeQuery("SELECT " + aggregate + "(" + column.getColumnName() + ") from MapDb." + tableName);
		resultSet.next();
		Object resultedObject = resultSet.getObject(1);
		statement.close();
		return resultedObject;
	}

}
