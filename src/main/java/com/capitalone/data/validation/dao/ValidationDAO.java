package com.capitalone.data.validation.dao;

import java.sql.Connection;
import java.sql.ResultSet;

public interface ValidationDAO {

	public ResultSet executeSelect(Connection connection , String SQL);
}
