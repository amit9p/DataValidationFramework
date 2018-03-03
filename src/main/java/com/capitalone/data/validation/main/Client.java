package com.capitalone.data.validation.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.capitalone.data.validation.constants.GetObject;
import com.capitalone.data.validation.util.DBConnection;

public class Client {

	public static void main(String[] args) throws IOException, SQLException {

		String inputReportConfigFile = args[0];
		String outputResultFile = args[1];

		List<List<String>> configData = GetObject.getObject()
				.getValidationService()
				.getReportMetadata(inputReportConfigFile);

		boolean combinedReportResult = GetObject
				.getObject()
				.getValidationService()
				.generateCombinedDBreport(configData,
						DBConnection.getInstance().getDB1Connection(),
						DBConnection.getInstance().getDB1Connection(),
						outputResultFile);
		if (combinedReportResult == true) {

			System.out.println("XLSX generated");
		} else {

			System.out.println("XLSX not generated");
		}

	}

}
