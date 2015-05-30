package org.flycloud.web.exam3.view;

import java.net.URLEncoder;
import java.util.HashMap;
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
import org.flycloud.web.exam3.model.QuestionFolder;
import org.flycloud.web.exam3.model.Resource;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * 导出EXCEL题库
 * @author zhangbo
 *
 */
public class QuestionBankExcelView extends AbstractExcelView {

	private String getFullFolderName(QuestionFolder folder) {
		if (folder.getParent() != null)
			return getFullFolderName(folder.getParent()) + "/"
					+ folder.getName();
		return folder.getName();
	}

	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<Question> list = (List<Question>) model.get("root");

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ URLEncoder.encode("所有试题.xls", "UTF-8") + "\"");

		HSSFSheet sheet = workbook.createSheet("试题");

		HSSFRow header = sheet.createRow(0);
		int i = 0;
		header.createCell(i++).setCellValue("序号");
		header.createCell(i++).setCellValue("题库");
		header.createCell(i++).setCellValue("章节");
		header.createCell(i++).setCellValue("题型");
		header.createCell(i++).setCellValue("格式");
		header.createCell(i++).setCellValue("难易");
		header.createCell(i++).setCellValue("题干");
		header.createCell(i++).setCellValue("选项");
		header.createCell(i++).setCellValue("答案");

		HSSFCellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-mm-dd"));

		int rowNum = 1;
		for (Iterator<Question> iter = list.iterator(); iter.hasNext();) {
			Question ques = (Question) iter.next();
			HSSFRow row = sheet.createRow(rowNum++);
			i = 0;
			row.createCell(i++).setCellValue(rowNum - 1);
			row.createCell(i++).setCellValue(
					ques.getFolder().getBank().getName());
			row.createCell(i++).setCellValue(getFullFolderName(ques.getFolder()));
			row.createCell(i++).setCellValue(ques.getType().getName());
			row.createCell(i++).setCellValue(
					ques.getType().getFormat().getName());
			row.createCell(i++).setCellValue(ques.getLevel().getName());

			List<Resource> r = ques.getResources();
			Map<String, String> m = new HashMap<String, String>();
			for (Resource rr : r) {
				String content = new String(rr.getContent(), rr.getCharset());
				m.put(rr.getName(), content);
			}

			row.createCell(i++).setCellValue(m.get("题干"));
			row.createCell(i++).setCellValue(m.get("选项"));
			row.createCell(i++).setCellValue(m.get("答案"));
		}

		// HSSFRow row = sheet.createRow(rowNum);
		// row.createCell(0).setCellValue("TOTAL:");
		// String formual = "SUM(D2:D" + rowNum + ")"; //
		// D2到D[rowNum]单元格起(count数据)
		// row.createCell(3).setCellFormula(formual);
	}

}