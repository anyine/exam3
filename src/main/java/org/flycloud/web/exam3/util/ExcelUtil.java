package org.flycloud.web.exam3.util;

public class ExcelUtil {

	/**
	 * 将单元格转换为公式使用的单元格的名称。
	 * 
	 * @param rowNum
	 * @param columnNum
	 * @return
	 */
	public static String getCellName(int rowNum, int columnNum) {
		return indexToColumn(columnNum) + rowNum;
	}

	/**
	 * 用于将Excel表格中列号字母转成列索引，从1对应A开始
	 * 
	 * @param column
	 *            列号
	 * @return 列索引
	 */
	public static int columnToIndex(String column) {
		if (!column.matches("[A-Z]+")) {
			try {
				throw new Exception("Invalid parameter");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int index = 0;
		char[] chars = column.toUpperCase().toCharArray();
		for (int i = 0; i < chars.length; i++) {
			index += ((int) chars[i] - (int) 'A' + 1)
					* (int) Math.pow(26, chars.length - i - 1);
		}
		return index;
	}

	/**
	 * 用于将excel表格中列索引转成列号字母，从A对应1开始
	 * 
	 * @param index
	 *            列索引
	 * @return 列号
	 */
	public static String indexToColumn(int index) {
		if (index <= 0) {
			try {
				throw new Exception("Invalid parameter");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		index--;
		String column = "";
		do {
			if (column.length() > 0) {
				index--;
			}
			column = ((char) (index % 26 + (int) 'A')) + column;
			index = (int) ((index - index % 26) / 26);
		} while (index > 0);
		return column;
	}

	public static void main(String[] args) {
		System.out.println(ExcelUtil.indexToColumn(1));
		System.out.println(ExcelUtil.indexToColumn(26));
		System.out.println(ExcelUtil.indexToColumn(28));
		System.out.println(ExcelUtil.getCellName(1, 23));
	}
}
