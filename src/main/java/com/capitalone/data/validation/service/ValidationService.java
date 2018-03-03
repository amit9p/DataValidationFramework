package com.capitalone.data.validation.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ValidationService {

	public boolean generateCombinedDBreport(List<List<String>> configData ,  Connection DBcon1 , Connection DBcon2 , String outputFile)throws SQLException ,  FileNotFoundException, IOException;
	public List<List<String>> getReportMetadata(String configFile);
	
}
