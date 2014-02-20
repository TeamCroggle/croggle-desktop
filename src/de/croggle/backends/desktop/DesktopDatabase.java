package de.croggle.backends.desktop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import de.croggle.backends.sqlite.ContentValues;
import de.croggle.backends.sqlite.Cursor;
import de.croggle.backends.sqlite.Database;
import de.croggle.backends.sqlite.SQLException;

public class DesktopDatabase implements Database {

	final Connection c;

	public DesktopDatabase(Connection c) {
		this.c = c;
	}

	@Override
	public void execSQL(String sql) {
		try {
			Statement stmt = c.createStatement();
			stmt.execute(sql);
		} catch (java.sql.SQLException e) {
			throw new SQLException();
		}
	}

	@Override
	public boolean isReadOnly() {
		try {
			return c.isReadOnly();
		} catch (java.sql.SQLException e) {
			throw new SQLException();
		}
	}

	@Override
	public Cursor rawQuery(String sql, String[] args) {
		try {
			Statement s = c.createStatement();
			ResultSet set = s.executeQuery(sql);

			return new DesktopCursor(set);
		} catch (java.sql.SQLException e) {
			throw new SQLException();
		}
	}

	@Override
	public int update(String table, ContentValues values, String whereClause,
			String[] whereArgs) {
		try {
			if (values == null
					|| ((DesktopContentValues) values).values.size() == 0) {
				throw new IllegalArgumentException("Empty values");
			}

			StringBuilder sql = new StringBuilder(120);
			sql.append("UPDATE ");

			sql.append(table);
			sql.append(" SET ");

			Set<Map.Entry<String, Object>> entrySet = ((DesktopContentValues) values).values
					.entrySet();
			Iterator<Map.Entry<String, Object>> entriesIter = entrySet
					.iterator();

			while (entriesIter.hasNext()) {
				Map.Entry<String, Object> entry = entriesIter.next();
				sql.append(entry.getKey());
				sql.append("=?");
				if (entriesIter.hasNext()) {
					sql.append(", ");
				}
			}

			if (!whereClause.isEmpty()) {
				sql.append(" WHERE ");
				sql.append(whereClause);
			}

			Statement statement = null;
			statement = c.createStatement();

			// TODO
			// // Bind the values
			// int size = entrySet.size();
			// entriesIter = entrySet.iterator();
			// int bindArg = 1;
			// for (int i = 0; i < size; i++) {
			// Map.Entry<String, Object> entry = entriesIter.next();
			// DatabaseUtils.bindObjectToProgram(statement, bindArg,
			// entry.getValue());
			// bindArg++;
			// }

			// if (whereArgs != null) {
			// size = whereArgs.length;
			// for (int i = 0; i < size; i++) {
			// statement.bindString(bindArg, whereArgs[i]);
			// bindArg++;
			// }
			// }

			// Run the program and then cleanup
			statement.executeUpdate(sql.toString());
			statement.close();
			int numChangedRows = statement.getUpdateCount();
			return numChangedRows;
		} catch (java.sql.SQLException ex) {
			throw new SQLException();
		}
	}

	@Override
	public int delete(String table, String whereClause, String[] whereArgs) {
		try {
			Statement statement = c.createStatement();
			String sql = "DELETE FROM " + table
					+ (!whereClause.isEmpty() ? " WHERE " + whereClause : "");
			// TODO
			// if (whereArgs != null) {
			// int numArgs = whereArgs.length;
			// for (int i = 0; i < numArgs; i++) {
			// DatabaseUtils.bindObjectToProgram(statement, i + 1,
			// whereArgs[i]);
			// }
			// }
			statement.executeUpdate(sql);
			statement.close();
			return statement.getUpdateCount();
		} catch (java.sql.SQLException ex) {
			throw new SQLException();
		}
	}

	@Override
	public long insert(String table, String nullColumnHack,
			ContentValues initialValues) {
		// Most code result of a grand theft from
		/*
		 * http://grepcode.com/file/repository.grepcode.com/java/ext/com.google.
		 * android/android/
		 * 2.1_r2/android/database/sqlite/SQLiteDatabase.java#SQLiteDatabase.insertWithOnConflict%28java.lang.String%2Cjava.lang.String%2Candroid.content.ContentValues%2Candroid.database.sqlite.SQLiteDatabase.ConflictAlgorithm%29
		 */

		// Measurements show most sql lengths <= 152
		StringBuilder sql = new StringBuilder(152);
		sql.append("INSERT");
		sql.append(" INTO ");
		sql.append(table);
		// Measurements show most values lengths < 40
		StringBuilder values = new StringBuilder(40);

		Set<Map.Entry<String, Object>> entrySet = null;
		if (initialValues != null
				&& ((DesktopContentValues) initialValues).values.size() > 0) {
			entrySet = ((DesktopContentValues) initialValues).values.entrySet();
			Iterator<Map.Entry<String, Object>> entriesIter = entrySet
					.iterator();
			sql.append('(');

			boolean needSeparator = false;
			while (entriesIter.hasNext()) {
				if (needSeparator) {
					sql.append(", ");
					values.append(", ");
				}
				needSeparator = true;
				Map.Entry<String, Object> entry = entriesIter.next();
				sql.append(entry.getKey());
				// originally:
				// values.append('?');
				// but we don't bind values correctly. TODO
				values.append("'" + entry.getValue() + "'");
			}

			sql.append(')');
		} else {
			sql.append("(" + nullColumnHack + ") ");
			values.append("NULL");
		}

		sql.append(" VALUES(");
		sql.append(values);
		sql.append(");");

		Statement statement = null;
		try {
			statement = c.createStatement();
			// TODO no idea what this was for
			// Bind the values
			// if (entrySet != null) {
			// int size = entrySet.size();
			// Iterator<Map.Entry<String, Object>> entriesIter =
			// entrySet.iterator();
			// for (int i = 0; i < size; i++) {
			// Map.Entry<String, Object> entry = entriesIter.next();
			// DatabaseUtils.bindObjectToProgram(statement, i + 1,
			// entry.getValue());
			// }
			// }

			// Run the program and then cleanup
			return statement.executeUpdate(sql.toString());
		} catch (java.sql.SQLException ex) {
			ex.printStackTrace();
			throw new SQLException();
		}
	}

	@Override
	public long queryForLong(String sql, String[] columnNames) {
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			return s.getResultSet().getLong(1);
		} catch (java.sql.SQLException e) {
			throw new SQLException();
		}
	}

}
