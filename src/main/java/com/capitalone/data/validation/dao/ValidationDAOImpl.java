package com.capitalone.data.validation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidationDAOImpl implements ValidationDAO {

	@Override
	public ResultSet executeSelect(Connection connection, String SQL) {

		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		try {
			stmt1 = connection.prepareStatement(SQL);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rs = stmt1.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}

}
