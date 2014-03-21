package de.croggle.backends.desktop;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					sql = sql.replaceFirst("\\?", "'" + args[i].replace("'", "''") + "'");
				}
			}
			ResultSet set = s.executeQuery(sql);

			return new DesktopCursor(set);
		} catch (java.sql.SQLException e) {
			throw new SQLException();
		}
	}

	@Override
	public int update(String table, ContentValues values, String whereClause,
			String[] whereArgs) {
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
		Iterator<Map.Entry<String, Object>> entriesIter = entrySet.iterator();

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
		PreparedStatement statement = null;
		try {
			statement = c.prepareStatement(sql.toString());

			int size = entrySet.size();
			entriesIter = entrySet.iterator();
			int bindArg = 1;
			for (int i = 0; i < size; i++) {
				Map.Entry<String, Object> entry = entriesIter.next();
				bindObjectToProgram(statement, bindArg, entry.getValue());
				bindArg++;
			}

			if (whereArgs != null) {
				size = whereArgs.length;
				for (int i = 0; i < size; i++) {
					statement.setString(bindArg, whereArgs[i]);
					bindArg++;
				}
			}

			// Run the program and then cleanup
			statement.executeUpdate();
			int numChangedRows = statement.getUpdateCount();
			return numChangedRows;
		} catch (java.sql.SQLException ex) {
			ex.printStackTrace();
			throw new SQLException();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (java.sql.SQLException e) {
					e.printStackTrace();
					// TODO things are pretty serious here
					throw new SQLException();
				}
			}
		}
	}

	@Override
	public int delete(String table, String whereClause, String[] whereArgs) {
		String sql = "DELETE FROM " + table
				+ (!whereClause.isEmpty() ? " WHERE " + whereClause : "");
		PreparedStatement statement = null;
		try {

			statement = c.prepareStatement(sql);
			if (whereArgs != null) {
				int numArgs = whereArgs.length;
				for (int i = 0; i < numArgs; i++) {
					statement.setString(i + 1, whereArgs[i]);
				}
			}
			statement.execute();
			return statement.getUpdateCount();
		} catch (java.sql.SQLException ex) {
			ex.printStackTrace();
			throw new SQLException();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (java.sql.SQLException e) {
					e.printStackTrace();
					// TODO things are pretty serious here
					throw new SQLException();
				}
			}
		}
	}

	@Override
	public long insert(String table, String nullColumnHack,
			ContentValues initialValues) {
		// Most code result of a grand theft from AOSP
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
				values.append('?');
			}

			sql.append(')');
		} else {
			sql.append("(" + nullColumnHack + ") ");
			values.append("NULL");
		}

		sql.append(" VALUES(");
		sql.append(values);
		sql.append(");");

		try {
			PreparedStatement statement = c.prepareStatement(sql.toString());
			// Bind the values
			if (entrySet != null) {
				int size = entrySet.size();
				Iterator<Map.Entry<String, Object>> entriesIter = entrySet
						.iterator();
				for (int i = 0; i < size; i++) {
					Map.Entry<String, Object> entry = entriesIter.next();
					bindObjectToProgram(statement, i + 1, entry.getValue());
				}
			}

			// Run the program and then cleanup
			return statement.executeUpdate();
		} catch (java.sql.SQLException ex) {
			ex.printStackTrace();
			throw new SQLException();
		}
	}

	@Override
	public long queryForLong(String sql, String[] columnNames) {
		try {
			Statement s = c.createStatement();
			s.execute(sql);
			return s.getResultSet().getLong(1);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}

	private static void bindObjectToProgram(PreparedStatement prog, int index,
			Object value) {
		try {
			if (value == null) {
				prog.setNull(index, java.sql.Types.NULL);
			} else if (value instanceof Double || value instanceof Float) {
				prog.setDouble(index, ((Number) value).doubleValue());
			} else if (value instanceof Number) {
				prog.setLong(index, ((Number) value).longValue());
			} else if (value instanceof Boolean) {
				Boolean bool = (Boolean) value;
				if (bool) {
					prog.setLong(index, 1);
				} else {
					prog.setLong(index, 0);
				}
			}
			// else if (value instanceof byte[]) {
			// prog.setBlob(index, new (byte[]) value);
			// }
			else {
				prog.setString(index, value.toString());
			}
		} catch (java.sql.SQLException e) {
			throw new SQLException();
		}
	}
}
