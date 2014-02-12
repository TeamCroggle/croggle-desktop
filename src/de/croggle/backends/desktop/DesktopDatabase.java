package de.croggle.backends.desktop;

import de.croggle.backends.sqlite.ContentValues;
import de.croggle.backends.sqlite.Cursor;
import de.croggle.backends.sqlite.Database;

public class DesktopDatabase implements Database {

	@Override
	public void execSQL(String sql) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isReadOnly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor rawQuery(String selection, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(String table, ContentValues values, String whereClause,
			String[] whereArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String table, String whereClause, String[] whereArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long insert(String table, String nullColumnHack, ContentValues values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long queryForLong(String selection, String[] args) {
		// TODO Auto-generated method stub
		return 0;
	}

}
