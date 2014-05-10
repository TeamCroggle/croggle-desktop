package de.croggle.backends.desktop;

import java.sql.Connection;
import java.sql.DriverManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import de.croggle.backends.sqlite.Database;
import de.croggle.backends.sqlite.DatabaseHelper;
import de.croggle.backends.sqlite.SQLException;

public class DesktopDatabaseHelper extends DatabaseHelper {

	protected static int open = 0;
	protected static DesktopDatabase database = null;

	@Override
	public Database getWritableDatabase() {
		if (database == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				FileHandle dbFile = Gdx.files.internal(DATABASE_NAME + ".db");
				boolean existing = dbFile.exists();
				Connection c = DriverManager.getConnection("jdbc:sqlite:"
						+ dbFile.path());
				database = new DesktopDatabase(c);
				if (!existing) {
					this.onCreate(database);
				}
			} catch (java.sql.SQLException e) {
				e.printStackTrace();
				throw new SQLException();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new SQLException();
			}
		}
		open++;
		return database;
	}

	@Override
	public void close() {
		try {
			if (database == null || open <= 0) {
				Gdx.app.log("DesktopDatabaseHelper",
						"Closing the database more often than opened");
			} else {
				open--;
				if (open == 0) {
					if (!database.c.isClosed()) {
						database.c.close();
					}
					database = null;
				}
			}
		} catch (java.sql.SQLException e) {
			throw new SQLException();
		}
	}

}
