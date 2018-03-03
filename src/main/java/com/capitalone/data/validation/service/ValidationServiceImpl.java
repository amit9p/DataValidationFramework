package com.capitalone.data.validation.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.capitalone.data.validation.constants.GetObject;

public class ValidationServiceImpl implements ValidationService {

	public List<List<String>> getReportMetadata(String configFile) {
		List<List<String>> reportMeta = null;
		try {
			reportMeta = GetObject.getObject().getReportUtilObject()
					.ReadReportConfig(configFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return reportMeta;
	}

	public boolean generateCombinedDBreport(List<List<String>> configData,
			Connection DBcon1, Connection DBcon2, String outputFile)
			throws SQLException, FileNotFoundException, IOException {

		boolean resultType = false;
		HSSFWorkbook xlsWorkbook = new HSSFWorkbook();
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int x = 0;

		try {

			for (List<String> O : configData) {

				String report_id = O.get(x);
				x++;
				String db1_sql1 = O.get(x);
				x++;
				String db1_sql2 = O.get(x);
				x++;

				HSSFSheet xlsSheet = xlsWorkbook.createSheet(report_id);
				short rowIndex = 0;
				int j = 0;
				short colIndex = 0;

				rs1 = GetObject.getObject().getValidationDAOObject()
						.executeSelect(DBcon1, db1_sql1);

				rs2 = GetObject.getObject().getValidationDAOObject()
						.executeSelect(DBcon1, db1_sql2);

				ResultSetMetaData colInfo = rs1.getMetaData();
				ResultSetMetaData colInfo2 = rs2.getMetaData();

				List<String> colNames = new ArrayList<String>();
				List<String> colNames2 = new ArrayList<String>();

				HSSFRow titleRow = xlsSheet.createRow(rowIndex++);

				for (int i = 1; i <= colInfo.getColumnCount(); i++) {
					colNames.add(colInfo.getColumnName(i));
					titleRow.createCell((short) (i - 1)).setCellValue(
							new HSSFRichTextString(colInfo.getColumnName(i)));
					xlsSheet.setColumnWidth((short) (i - 1), (short) 4000);
					j = i;
				}

				for (int k = 1; k <= colInfo2.getColumnCount(); k++) {
					colNames2.add(colInfo2.getColumnName(k));
					titleRow.createCell((short) (j)).setCellValue(
							new HSSFRichTextString(colInfo2.getColumnName(k)));
					xlsSheet.setColumnWidth((short) (k - 1), (short) 4000);
					j++;
				}

				HSSFRow dataRow = null;

				while (rs1.next()) {
					dataRow = xlsSheet.createRow(rowIndex++);
					colIndex = 0;
					for (String colName : colNames) {
						dataRow.createCell(colIndex++).setCellValue(
								new HSSFRichTextString(rs1.getString(colName)));
					}

					if (rs2.next()) {
						for (String colName : colNames2) {

							dataRow.createCell(colIndex++).setCellValue(
									new HSSFRichTextString(rs2
											.getString(colName)));
						}
					}

				}

			}

		} catch (Exception e) {
			resultType = false;
		}
		// Write to disk
		xlsWorkbook.write(new FileOutputStream(outputFile));
		resultType = true;
		DBcon1.close();
		DBcon2.close();

		return resultType;

	}

}
