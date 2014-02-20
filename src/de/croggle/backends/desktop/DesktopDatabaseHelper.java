package de.croggle.backends.desktop;

import java.sql.Connection;
import java.sql.DriverManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import de.croggle.backends.sqlite.Database;
import de.croggle.backends.sqlite.DatabaseHelper;
import de.croggle.backends.sqlite.SQLException;

public class DesktopDatabaseHelper extends DatabaseHelper {

	DesktopDatabase db;

	@Override
	public Database getWritableDatabase() {
		if (db == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				FileHandle dbFile = Gdx.files.internal(DATABASE_NAME + ".db");
				boolean existing = dbFile.exists();
				Connection c = DriverManager.getConnection("jdbc:sqlite:"
						+ dbFile.path());
				db = new DesktopDatabase(c);
				if (!existing) {
					this.onCreate(db);
				}
				return db;
			} catch (java.sql.SQLException e) {
				throw new SQLException();
			} catch (ClassNotFoundException e) {
				throw new SQLException();
			}
		} else {
			return db;
		}
	}

	@Override
	public void close() {
		try {
			if (db != null) {
				if (!db.c.isClosed()) {
					db.c.close();
				}
				db = null;
			}
		} catch (java.sql.SQLException e) {
			throw new SQLException();
		}
	}

}
