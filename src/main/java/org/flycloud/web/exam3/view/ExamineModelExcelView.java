package org.flycloud.web.exam3.view;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.flycloud.util.IndexGenerator;
import org.flycloud.web.exam3.model.QuestionBank;
import org.flycloud.web.exam3.model.QuestionFolder;
import org.flycloud.web.exam3.model.QuestionLevel;
import org.flycloud.web.exam3.model.QuestionType;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExamineModelExcelView extends AbstractExcelView {

	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		QuestionBank bank = (QuestionBank) model.get("root");

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ URLEncoder.encode(bank.getName() + "-抽题模板.xls", "UTF-8")
				+ "\"");

		HSSFSheet sheet = workbook.createSheet("抽题模板");

		HSSFCellStyle styleTitle = workbook.createCellStyle();
		styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont ft = workbook.createFont();
		ft.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		styleTitle.setFont(ft);

		//
		HSSFFont columnHeadFont = workbook.createFont();
		columnHeadFont.setFontName("宋体");
		columnHeadFont.setFontHeightInPoints((short) 10);
		columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 列头的样式
		HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
		columnHeadStyle.setFont(columnHeadFont);
		columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
//		columnHeadStyle.setLocked(true);
//		columnHeadStyle.setWrapText(true);
//		columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
//		columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
//		columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
//		columnHeadStyle.setBorderRight((short) 1);// 边框的大小
//		columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
//		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
		// 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
		columnHeadStyle.setFillForegroundColor(HSSFColor.BLUE.index);
		//

		sheet.createFreezePane(2, 2, 2, 2);

		HSSFRow header1 = sheet.createRow(0);
		int i = 0;

		HSSFCell c = header1.createCell(i);
		sheet.setColumnWidth(i++, 10 * 256);
		c.setCellStyle(columnHeadStyle);
		c.setCellValue("序号");

		CellRangeAddress range1 = new CellRangeAddress(0, 1, 0, 0);
		sheet.addMergedRegion(range1);

		c = header1.createCell(i);
		sheet.setColumnWidth(i++, 40 * 256);
		c.setCellStyle(columnHeadStyle);
		c.setCellValue("章节");
		CellRangeAddress range2 = new CellRangeAddress(0, 1, 1, 1);
		sheet.addMergedRegion(range2);

		HSSFRow header2 = sheet.createRow(1);
		
		for (int l = 0; l < bank.getTypes().size(); l++) {
			QuestionType type = bank.getTypes().get(l);
			c = header1.createCell(i);
			c.setCellValue(type.getName());
			CellRangeAddress range = new CellRangeAddress(0, 0, i, i + 9);
			sheet.addMergedRegion(range);
			header1.getCell(i).setCellStyle(columnHeadStyle);

			int sub = 0;
			for (QuestionLevel ql : QuestionLevel.values()) {
				HSSFCell c2 = header2.createCell(i + sub * 2);
				c2.setCellValue(new HSSFRichTextString(ql.getName()));
				c2.setCellType(HSSFCell.CELL_TYPE_STRING);
				c2.setCellStyle(columnHeadStyle);
				CellRangeAddress rg = new CellRangeAddress(1, 1, i + sub * 2, i
						+ sub * 2 + 1);
				sheet.setColumnWidth(i + sub * 2, 4 * 256);
				sheet.setColumnWidth(i + sub * 2 + 1, 4 * 256);
				sheet.addMergedRegion(rg);
				sub++;
			}

			i += 10;
		}

		HSSFCellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-mm-dd"));

		int rowNum = 2;

		IndexGenerator ig = new IndexGenerator();
		for (QuestionFolder f : bank.getFolders()) {
			HSSFRow row = sheet.createRow(rowNum++);
			i = 0;

			c = row.createCell(i++);
			c.setCellValue(ig.getIndex(f.getLevel()));

			c = row.createCell(i++);
			c.setCellValue(f.getName());
			
			for(QuestionType t : bank.getTypes()) {
				for (QuestionLevel l : QuestionLevel.values()) {
					c = row.createCell(i++);
					c.setCellValue(10);
					c = row.createCell(i++);
					c.setCellValue(0);
				}
			}
		}

		// HSSFRow row = sheet.createRow(rowNum);
		// row.createCell(0).setCellValue("TOTAL:");
		// String formual = "SUM(D2:D" + rowNum + ")"; //
		// D2到D[rowNum]单元格起(count数据)
		// row.createCell(3).setCellFormula(formual);
	}

}