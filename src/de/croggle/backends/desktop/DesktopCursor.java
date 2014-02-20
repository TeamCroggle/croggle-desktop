package de.croggle.backends.desktop;

import java.sql.ResultSet;

import de.croggle.backends.sqlite.Cursor;
import de.croggle.backends.sqlite.SQLException;

public class DesktopCursor implements Cursor {

	final ResultSet cursor;

	public DesktopCursor(ResultSet r) {
		cursor = r;
	}

	@Override
	public boolean moveToFirst() {
		try {
			return cursor.isBeforeFirst();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
		/*
		 * Not implemeted due to obvious sqlite restrictions. See for example
		 * http
		 * ://stackoverflow.com/questions/20066635/using-rsnext-but-got-type-
		 * foward-only-in-sqlite
		 */
		// try {
		// return cursor.first();
		// } catch (java.sql.SQLException ex) {
		// ex.printStackTrace();
		// throw new SQLException();
		// }

	}

	@Override
	public boolean moveToNext() {
		try {
			return cursor.next();
		} catch (java.sql.SQLException e) {
			throw new SQLException();
		}
	}

	@Override
	public int getColumnIndex(String name) {
		try {
			return cursor.findColumn(name) - 1;
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}

	@Override
	public String getString(int columnIndex) {
		try {
			return cursor.getString(columnIndex + 1);
		} catch (java.sql.SQLException e) {
			throw new SQLException();
		}
	}

	@Override
	public int getInt(int columnIndex) {
		try {
			return cursor.getInt(columnIndex + 1);
		} catch (java.sql.SQLException e) {
			throw new SQLException();
		}
	}

	@Override
	public float getFloat(int columnIndex) {
		try {
			return cursor.getFloat(columnIndex + 1);
		} catch (java.sql.SQLException e) {
			throw new SQLException();
		}
	}
}
