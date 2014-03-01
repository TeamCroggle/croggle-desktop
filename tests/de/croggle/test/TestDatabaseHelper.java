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

	@Override
	public Database getWritableDatabase() {
		if (db == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				FileHandle dbFile = Gdx.files.internal("test.db");
				boolean existing = dbFile.exists();
				Connection c = DriverManager.getConnection("jdbc:sqlite:"
						+ dbFile.path());
				db = new DesktopDatabase(c);
				if (!existing) {
					this.onCreate(db);
				}
				return db;
			} catch (java.sql.SQLException e) {
				e.printStackTrace();
				throw new SQLException();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new SQLException();
			}
		} else {
			return db;
		}
	}
}
