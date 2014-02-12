package de.croggle.data.persistence.manager;

public interface Database {
	void execSQL(String sql);

	boolean isReadOnly();

	Cursor rawQuery(String selection, String[] args);

	int update(String table, ContentValues values, String whereClause,
			String[] whereArgs);

	int delete(String table, String whereClause, String[] whereArgs);

	long insert(String table, String nullColumnHack, ContentValues values);
}
