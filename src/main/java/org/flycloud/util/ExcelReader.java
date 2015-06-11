package org.flycloud.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;

public class ExcelReader {
	private Sheet sheet;
	private Map<String, Integer> map = new HashMap<String, Integer>();
	private List<String> list = new ArrayList<String>();

	public ExcelReader(Sheet sheet) {
		this.sheet = sheet;
		for (int c = 0; c < sheet.getColumns(); c++) {
			String name = sheet.getCell(c, 0).getContents().trim();
			map.put(name, c);
			list.add(name);
		}
	}

	public int getColumns() {
		return sheet.getColumns();
	}

	public int getRows() {
		return sheet.getRows();
	}

	public Map<String, String> getRow(int row) {
		Map<String, String> map = new HashMap<String, String>();
		for (String key : list) {
			map.put(key, getCellValue(row, key));
		}
		return map;
	}

	public String getCellValue(int row, String key) {
		return sheet.getCell(map.get(key), row).getContents();
	}

	public static void main(String[] args) {

	}

}
