package org.flycloud.web.exam3.view;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.flycloud.web.exam3.model.Question;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class QuestionExcelView extends AbstractExcelView {

	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// CommonSelectCommand cmd = (CommonSelectCommand) model.get("command");
		@SuppressWarnings("unchecked")
		List<Question> list = (List<Question>) model.get("root");
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ URLEncoder.encode("网银激活用户信息.xls", "UTF-8") + "\"");

		HSSFSheet sheet = workbook.createSheet("网银激活用户");
		// + df.format(cmd.getStartDate()) + ">"
		// + df.format(cmd.getEndDate()));

		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("真实姓名");
		header.createCell(1).setCellValue("身份证号");
		// header.createCell(2).setCellValue("手机号码");
		// header.createCell(3).setCellValue("录入日期");
		// header.createCell(4).setCellValue("注册会员");
		// header.createCell(5).setCellValue("录入人员");
		// header.createCell(6).setCellValue("激活支局");

		HSSFCellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-mm-dd"));

		int rowNum = 1;
		for (Iterator<Question> iter = list.iterator(); iter.hasNext();) {
			Question ques = (Question) iter.next();
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(
					ques.getFolder().getName().toString());
			row.createCell(1).setCellValue(ques.getDifficult().toString());
			// row.createCell(2).setCellValue(ques.getMobile().toString());
			// row.createCell(3).setCellValue(df.format(ques.getDate()));
			// row.getCell(3).setCellStyle(dateStyle);
			// row.createCell(4).setCellValue(ques.getMember().getRealname());
			// row.createCell(5).setCellValue(ques.getUser().getRealname());
			// row.createCell(6).setCellValue(ques.getUser().getOrganization().getName());
		}

		// HSSFRow row = sheet.createRow(rowNum);
		// row.createCell(0).setCellValue("TOTAL:");
		// String formual = "SUM(D2:D" + rowNum + ")"; //
		// D2到D[rowNum]单元格起(count数据)
		// row.createCell(3).setCellFormula(formual);
	}

}