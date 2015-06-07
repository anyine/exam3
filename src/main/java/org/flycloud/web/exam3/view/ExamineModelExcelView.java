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
import org.flycloud.web.exam3.model.QuestionBank;
import org.flycloud.web.exam3.model.QuestionFolder;
import org.flycloud.web.exam3.model.QuestionLevel;
import org.flycloud.web.exam3.model.QuestionType;
import org.flycloud.web.exam3.util.IndexGenerator;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExamineModelExcelView extends AbstractExcelView {

	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		QuestionBank bank = (QuestionBank) model.get("root");

		HSSFSheet sheet = workbook.createSheet("抽题模板");
		
		createCells(sheet, bank);
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ URLEncoder.encode(bank.getName() + "-抽题模板.xls", "UTF-8")
				+ "\"");
	}
	
	private void createCells(HSSFSheet sheet, QuestionBank bank) {
		int levelNum = QuestionLevel.values().length;//试题难度数量
		int typeNum = bank.getTypes().size();//试题类型数量
		int folderNum = bank.getFolders().size();//试题分类
		
		sheet.createFreezePane(5, 4, 5, 4);

		HSSFRow header1 = sheet.createRow(0);
		HSSFRow header2 = sheet.createRow(1);
		HSSFRow header3 = sheet.createRow(2);
		HSSFRow header4 = sheet.createRow(3);
		
		HSSFCell c = header1.createCell(0);
		c.setCellValue("序号");
		
		c = header1.createCell(1);
		c.setCellValue("级别");
		
		c = header1.createCell(2);
		c.setCellValue("章节");
		
		c = header1.createCell(3);
		c.setCellValue("分数");
		
		c = header1.createCell(4);
		c.setCellValue("占比");
		
		c = header4.createCell(0);
		c.setCellValue("");
		
		c = header4.createCell(1);
		c.setCellValue("");
		
		c = header4.createCell(2);
		c.setCellValue("合计：");
		
		c = header4.createCell(3);
		c.setCellValue("100");
		
		c = header4.createCell(4);
		c.setCellValue("100%");
		
		sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(0, 2, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(0, 2, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(0, 2, 3, 3));
		sheet.addMergedRegion(new CellRangeAddress(0, 2, 4, 4));

		int rowNum = 4;
		IndexGenerator ig = new IndexGenerator();
		for (QuestionFolder f : bank.getFolders()) {
			HSSFRow row = sheet.createRow(rowNum++);
			int i = 0;

			//序号
			c = row.createCell(i++);
			c.setCellValue(""+(rowNum-4));

			//级别
			c = row.createCell(i++);
			c.setCellValue(f.getLevel());
			
			//章节
			c = row.createCell(i++);
			c.setCellValue(ig.getIndent(f.getLevel())
					+ ig.getIndex(f.getLevel()) + "  " + f.getName());

			//分数
			c = row.createCell(i++);
			c.setCellValue(f.getLevel());
			
			//占比
			c = row.createCell(i++);
			c.setCellValue(f.getLevel());
			
//			for (QuestionType t : bank.getTypes()) {
//				for (QuestionLevel l : QuestionLevel.values()) {
//					c = row.createCell(i++);
//					c.setCellValue(10);
//					c = row.createCell(i++);
//					c.setCellValue(0);
//				}
//			}
		}
	}


	protected void buildExcelDocument1(Map<String, Object> model,
				HSSFWorkbook workbook, HttpServletRequest request,
				HttpServletResponse response) throws Exception {

		QuestionBank bank = (QuestionBank) model.get("root");

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
		// columnHeadStyle.setLocked(true);
		// columnHeadStyle.setWrapText(true);
		// columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
		// columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
		// columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
		// columnHeadStyle.setBorderRight((short) 1);// 边框的大小
		// columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //
		// 设置单元格的边框为粗体
		// columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); //
		// 设置单元格的边框颜色
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
			c.setCellValue("");

			c = row.createCell(i++);
			c.setCellValue(ig.getIndent(f.getLevel())
					+ ig.getIndex(f.getLevel()) + "  " + f.getName());

			for (QuestionType t : bank.getTypes()) {
				for (QuestionLevel l : QuestionLevel.values()) {
					c = row.createCell(i++);
					c.setCellValue(10);
					c = row.createCell(i++);
					c.setCellValue(0);
				}
			}
		}

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ URLEncoder.encode(bank.getName() + "-抽题模板.xls", "UTF-8")
				+ "\"");
	}

}