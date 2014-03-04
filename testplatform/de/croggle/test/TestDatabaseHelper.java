package de.croggle.test;

import java.sql.Connection;
import java.sql.DriverManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import de.croggle.backends.desktop.DesktopDatabase;
import de.croggle.backends.desktop.DesktopDatabaseHelper;
import de.croggle.backends.sqlite.Database;
import de.croggle.backends.sqlite.SQLException;

public class TestDatabaseHelper extends DesktopDatabaseHelper {

	public static final String TEST_DB_NAME = "test.db";

	@Override
	public Database getWritableDatabase() {
		if (database == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				FileHandle dbFile = Gdx.files.internal(TEST_DB_NAME);
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
}
