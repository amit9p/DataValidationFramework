package com.capitalone.data.validation.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DBConnection {

	private static DBConnection instance;

	private DBConnection() {

	}

	public static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}

	public Connection getDB1Connection() {

		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("db.properties");
		Properties props = new Properties();
		Connection con = null;
		try {
			props.load(in);

			Class.forName(props.getProperty("DB1_DRIVER_CLASS"));
			con = DriverManager.getConnection(props.getProperty("DB1_URL"),
					props.getProperty("DB1_USERNAME"),
					props.getProperty("DB1_PASSWORD"));
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}
