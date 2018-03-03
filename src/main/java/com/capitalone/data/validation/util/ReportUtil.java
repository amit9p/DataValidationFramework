package com.capitalone.data.validation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReportUtil {

	@SuppressWarnings("deprecation")
	public List<List<String>> ReadReportConfig(String FILE_NAME) throws IOException {

		List<List<String>> allConfigData = new ArrayList<List<String>>();
		List<String> reportConfigData = new ArrayList<String>();
		FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet datatypeSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = datatypeSheet.iterator();
		while (iterator.hasNext()) {

			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();

			while (cellIterator.hasNext()) {

				Cell currentCell = cellIterator.next();
				if (currentCell.getCellTypeEnum() == CellType.STRING) {
					reportConfigData.add(currentCell.getStringCellValue());
				} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
					System.out.print(currentCell.getNumericCellValue() + "--");
				}

			}
			
			allConfigData.add(reportConfigData);

		}
		return allConfigData;
	}
}
