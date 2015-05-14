package org.flycloud.web.exam3.test;
//package org.flycloud.util;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//
//import com.jacob.activeX.ActiveXComponent;
//import com.jacob.com.ComThread;
//import com.jacob.com.Dispatch;
//import com.jacob.com.Variant;
//
//public class Java2word {
//
//	private boolean saveOnExit;
//	/**
//	 * word文档
//	 */
//	Dispatch doc = null;
//
//	/**
//	 * word运行程序对象s
//	 */
//	public static ActiveXComponent word;
//	/**
//	 * 所有word文档
//	 */
//
//	/** 字体 */
//	private Dispatch font = null;
//	private Dispatch documents;
//
//	public Questions questions;
//
//	public String inUrl;
//	public String outUrl;
//
//	/**
//	 * 构造函数
//	 */
//	public Java2word() {
//		if (word == null) {
//			word = new ActiveXComponent("Word.Application");
//			word.setProperty("Visible", new Variant(true));
//		}
//		if (documents == null)
//			documents = word.getProperty("Documents").toDispatch();
//		saveOnExit = false;
//		// if (word == null||word.m_pDispatch==0) {
//		// word = new ActiveXComponent("Word.Application");
//		// word.setProperty("Visible", new Variant(false));
//		// word.setProperty("DisplayAlerts", new Variant(false));
//		// }
//		// if (documents == null||documents.m_pDispatch==0) {
//		// documents = word.getProperty("Documents").toDispatch();
//		// }
//	}
//
//	public Java2word(boolean flag) {
//		if (word == null) {
//			word = new ActiveXComponent("Word.Application");
//			word.setProperty("Visible", new Variant(flag));
//		}
//		if (documents == null)
//			documents = word.getProperty("Documents").toDispatch();
//		saveOnExit = true;
//	}
//
//	public Java2word(Questions questions, String inUrl, String outUrl) {
//		this.questions = questions;
//		this.inUrl = inUrl;
//		this.outUrl = outUrl;
//	}
//
//	/**
//	 * 设置参数：退出时是否保存
//	 * 
//	 * @param saveOnExit
//	 *            boolean true-退出时保存文件，false-退出时不保存文件
//	 */
//	public void setSaveOnExit(boolean saveOnExit) {
//		this.saveOnExit = saveOnExit;
//	}
//
//	/**
//	 * 得到参数：退出时是否保存
//	 * 
//	 * @return boolean true-退出时保存文件，false-退出时不保存文件
//	 */
//	public boolean getSaveOnExit() {
//		return saveOnExit;
//	}
//
//	/**
//	 * 打开文件
//	 * 
//	 * @param inputDoc
//	 *            String 要打开的文件，全路径
//	 * @return Dispatch 打开的文件
//	 */
//	public Dispatch open(String inputDoc) {
//		return Dispatch.call(documents, "Open", inputDoc).toDispatch();
//		// return Dispatch.invoke(documents,"Open",Dispatch.Method,new
//		// Object[]{inputDoc},new int[1]).toDispatch();
//	}
//
//	/**
//	 * 选定内容
//	 * 
//	 * @return Dispatch 选定的范围或插入点
//	 */
//	public Dispatch select() {
//		return word.getProperty("Selection").toDispatch();
//	}
//
//	/**
//	 * 把选定内容或插入点向上移动
//	 * 
//	 * @param selection
//	 *            Dispatch 要移动的内容
//	 * @param count
//	 *            int 移动的距离
//	 */
//	public void moveUp(Dispatch selection, int count) {
//		for (int i = 0; i < count; i++)
//			Dispatch.call(selection, "MoveUp");
//	}
//
//	/**
//	 * 把选定内容或插入点向下移动
//	 * 
//	 * @param selection
//	 *            Dispatch 要移动的内容
//	 * @param count
//	 *            int 移动的距离
//	 */
//	public void moveDown(Dispatch selection, int count) {
//		for (int i = 0; i < count; i++)
//			Dispatch.call(selection, "MoveDown");
//	}
//
//	/**
//	 * 把选定内容或插入点向左移动
//	 * 
//	 * @param selection
//	 *            Dispatch 要移动的内容
//	 * @param count
//	 *            int 移动的距离
//	 */
//	public void moveLeft(Dispatch selection, int count) {
//		for (int i = 0; i < count; i++) {
//			Dispatch.call(selection, "MoveLeft");
//		}
//	}
//
//	/**
//	 * 把选定内容或插入点向右移动
//	 * 
//	 * @param selection
//	 *            Dispatch 要移动的内容
//	 * @param count
//	 *            int 移动的距离
//	 */
//	public void moveRight(Dispatch selection, int count) {
//		for (int i = 0; i < count; i++)
//			Dispatch.call(selection, "MoveRight");
//	}
//
//	/**
//	 * 把插入点移动到文件首位置
//	 * 
//	 * @param selection
//	 *            Dispatch 插入点
//	 */
//	public void moveStart(Dispatch selection) {
//		Dispatch.call(selection, "HomeKey", new Variant(6));
//	}
//
//	/**
//	 * 从选定内容或插入点开始查找文本
//	 * 
//	 * @param selection
//	 *            Dispatch 选定内容
//	 * @param toFindText
//	 *            String 要查找的文本
//	 * @return boolean true-查找到并选中该文本，false-未查找到文本
//	 */
//	public boolean find(Dispatch selection, String toFindText) {
//		// 从selection所在位置开始查询
//		Dispatch find = word.call(selection, "Find").toDispatch();
//		// 设置要查找的内容
//		Dispatch.put(find, "Text", toFindText);
//		// 向前查找
//		Dispatch.put(find, "Forward", "True");
//		// 设置格式
//		Dispatch.put(find, "Format", "True");
//		// 大小写匹配
//		Dispatch.put(find, "MatchCase", "True");
//		// 全字匹配
//		Dispatch.put(find, "MatchWholeWord", "True");
//		// 查找并选中
//		return Dispatch.call(find, "Execute").getBoolean();
//	}
//
//	/**
//	 * 把选定内容替换为设定文本
//	 * 
//	 * @param selection
//	 *            Dispatch 选定内容
//	 * @param newText
//	 *            String 替换为文本
//	 */
//	public void replace(Dispatch selection, String newText) {
//		// 设置替换文本
//		Dispatch.put(selection, "Text", newText);
//	}
//
//	/**
//	 * 全局替换
//	 * 
//	 * @param selection
//	 *            Dispatch 选定内容或起始插入点
//	 * @param oldText
//	 *            String 要替换的文本
//	 * @param newText
//	 *            String 替换为文本
//	 */
//	public void replaceAll(Dispatch selection, String oldText, Object replaceObj) {
//		// 移动到文件开头
//		moveStart(selection);
//
//		if (oldText.startsWith("table") || replaceObj instanceof ArrayList)
//			replaceTable(selection, oldText, (ArrayList) replaceObj);
//		else {
//			String newText = (String) replaceObj;
//			if (newText == null)
//				newText = "";
//			if (oldText.indexOf("image") != -1 & !newText.trim().equals("")
//					|| newText.lastIndexOf(".bmp") != -1
//					|| newText.lastIndexOf(".jpg") != -1
//					|| newText.lastIndexOf(".gif") != -1) {
//				while (find(selection, oldText)) {
//					replaceImage(selection, newText);
//					Dispatch.call(selection, "MoveRight");
//				}
//			} else {
//				while (find(selection, oldText)) {
//					replace(selection, newText);
//					Dispatch.call(selection, "MoveRight");
//				}
//			}
//		}
//	}
//
//	/**
//	 * 替换图片
//	 * 
//	 * @param selection
//	 *            Dispatch 图片的插入点
//	 * @param imagePath
//	 *            String 图片文件（全路径）
//	 */
//	public void replaceImage(Dispatch selection, String imagePath) {
//		Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(),
//				"AddPicture", imagePath);
//	}
//
//	/**
//	 * 替换表格
//	 * 
//	 * @param selection
//	 *            Dispatch 插入点
//	 * @param tableName
//	 *            String 表格名称，形如table$1@1、table$2@1...table$R@N，R代表从表格中的第N行开始填充，
//	 *            N代表word文件中的第N张表
//	 * @param fields
//	 *            HashMap 表格中要替换的字段与数据的对应表
//	 */
//	public void replaceTable(Dispatch selection, String tableName,
//			ArrayList dataList) {
//		if (dataList.size() <= 1) {
//			System.out.println("Empty table!");
//			return;
//		}
//
//		// 要填充的列
//		String[] cols = (String[]) dataList.get(0);
//
//		// 表格序号
//		String tbIndex = tableName.substring(tableName.lastIndexOf("@") + 1);
//		// 从第几行开始填充
//		int fromRow = Integer.parseInt(tableName.substring(
//				tableName.lastIndexOf("$") + 1, tableName.lastIndexOf("@")));
//		// 所有表格
//		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
//		// 要填充的表格
//		Dispatch table = Dispatch.call(tables, "Item", new Variant(tbIndex))
//				.toDispatch();
//		// 表格的所有行
//		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
//		// 填充表格
//		for (int i = 1; i < dataList.size(); i++) {
//			// 某一行数据
//			String[] datas = (String[]) dataList.get(i);
//
//			// 在表格中添加一行
//			if (Dispatch.get(rows, "Count").getInt() < fromRow + i - 1)
//				Dispatch.call(rows, "Add");
//			// 填充该行的相关列
//			for (int j = 0; j < datas.length; j++) {
//				// 得到单元格
//				Dispatch cell = Dispatch.call(table, "Cell",
//						Integer.toString(fromRow + i - 1), cols[j])
//						.toDispatch();
//				// 选中单元格
//				Dispatch.call(cell, "Select");
//				// 设置格式
//				Dispatch font = Dispatch.get(selection, "Font").toDispatch();
//				Dispatch.put(font, "Bold", "0");
//				Dispatch.put(font, "Italic", "0");
//				// 输入数据
//				Dispatch.put(selection, "Text", datas[j]);
//			}
//		}
//	}
//
//	/**
//	 * 保存文件
//	 * 
//	 * @param outputPath
//	 *            String 输出文件（包含路径）
//	 */
//	public void save(String outputPath) {
//		Dispatch.call(Dispatch.call(word, "WordBasic").getDispatch(),
//				"FileSaveAs", outputPath);
//	}
//
//	/**
//	 * 关闭文件
//	 * 
//	 * @param document
//	 *            Dispatch 要关闭的文件
//	 */
//	public void close(Dispatch doc) {
//		Dispatch.call(doc, "Close", new Variant(saveOnExit));
//		word.invoke("Quit", new Variant[] {});
//		word = null;
//	}
//
//	/**
//	 * 根据模板、数据生成word文件
//	 * 
//	 * @param inputPath
//	 *            String 模板文件（包含路径）
//	 * @param outPath
//	 *            String 输出文件（包含路径）
//	 * @param data
//	 *            HashMap 数据包（包含要填充的字段、对应的数据）
//	 */
//	public void toWord(String inputPath, String outPath, HashMap data) {
//		String oldText;
//		Object newValue;
//		try {
//			if (doc == null)
//				doc = open(inputPath);
//
//			Dispatch selection = select();
//
//			Iterator keys = data.keySet().iterator();
//			while (keys.hasNext()) {
//				oldText = (String) keys.next();
//				newValue = data.get(oldText);
//
//				replaceAll(selection, oldText, newValue);
//			}
//
//			save(outPath);
//		} catch (Exception e) {
//			System.out.println("操作word文件失败！");
//			e.printStackTrace();
//		} finally {
//			// if (doc != null)
//			// close(doc);
//		}
//	}
//
//	public synchronized static void word(String inputPath, String outPath,
//			HashMap data) {
//		Java2word j2w = new Java2word();
//		j2w.toWord(inputPath, outPath, data);
//	}
//
//	Dispatch selection;
//	Dispatch document;
//
//	public void work1() {
//
//		ComThread.InitSTA();
//		ActiveXComponent objWord = new ActiveXComponent("Word.Application");
//		Dispatch wordObject = (Dispatch) objWord.getObject();
//		Dispatch.put((Dispatch) wordObject, "Visible", new Variant(false));
//		Dispatch documents = objWord.getProperty("Documents").toDispatch();
//		document = Dispatch.call(documents, "Add").toDispatch();
//		selection = Dispatch.get(wordObject, "Selection").toDispatch();
//
//		try {
//			// j2w.moveStart(selection);
//
//			for (int i = 0; i < this.questions.getQgs().size(); i++) {
//				QuestionGroup qs = this.questions.getQgs().get(i);
//				if (qs.type.equals("单项选择题")) {
//					this.write(selection, "一、单项选择题");
//					this.enterDown(selection, 1);
//					this.writeAnswer(selection, qs, "x");
//				} else if (qs.type.equals("多项选择题")) {
//					this.write(selection, "二、多项选择题");
//					this.enterDown(selection, 1);
//					this.writeAnswer(selection, qs, "x");
//				} else if (qs.type.equals("判断题")) {
//					this.write(selection, "三、判断题");
//					this.enterDown(selection, 1);
//					this.writeAnswer(selection, qs, "x");
//				} else if (qs.type.equals("简答题")) {
//					this.write(selection, "四、简答题");
//					this.enterDown(selection, 1);
//					this.writeAnswer(selection, qs, null);
//				} else if (qs.type.equals("技能实务题")) {
//					this.write(selection, "五、技能实务题");
//					this.enterDown(selection, 1);
//					this.writeAnswer(selection, qs, null);
////				} else if (qs.type.equals("公文写作")) {
////					this.write(selection, "五、公文写作");
////					this.enterDown(selection, 1);
////					this.writeAnswer(selection, qs, null);
//				}
//				// else if (qs.type.equals("案例分析题")) {
//				// this.write(selection, "六、案例分析题");
//				// this.enterDown(selection, 1);
//				// }
////				else if (qs.type.equals("论述题")) {
////					this.write(selection, "六、论述题");
////					this.enterDown(selection, 1);
////					this.writeAnswer(selection, qs, null);
////				}
//				// this.writeAnswer(selection, qs);
//			}
//			String name = this.questions.getTitle().split("，")[0];
//			Dispatch.call(document, "SaveAs", new Variant("c:/" + name
//					+ "答案.doc"));
//			// Dispatch.call(document, "SaveAs", new Variant("c:/" + "答案.doc"));
//			Dispatch.call(document, "Close", new Variant(true));
//			objWord.invoke("Quit", new Variant[0]);
//			ComThread.Release();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	// private void writeAnswer(Dispatch selection, QuestionGroup qs) {
//	// List<QuestionItem> list = qs.getQuestions();
//	// for (int i = 0; i < list.size(); i++) {
//	// this.write(selection, i + 1 + "、");
//	// this.write(selection, list.get(i).getAnswer());
//	// this.enterDown(selection, 1);
//	// }
//	// }
//	private void writeAnswer(Dispatch selection, QuestionGroup qs, String type) {
//		List<QuestionItem> list = qs.getQuestions();
//		if (type == null) {
//			for (int i = 0; i < list.size(); i++) {
//				this.write(selection, i + 1 + "、");
//				this.write(selection, list.get(i).getAnswer());
//				this.enterDown(selection, 1);
//			}
//		} else {// 选择题、判断题
//			int start = 0;
//			int limit = 5;
//			int pageNum = list.size() / limit;
//			int m = list.size() % limit;
//			if (m == 0) {
//				writeQuestion(list, start, limit, pageNum);
//			} else {
//				if (pageNum != 0) {
//					writeQuestion(list, start, limit, pageNum);
//				}
//				if ((pageNum * 5 + 1) == list.size()) {
//					this.write(selection, (pageNum * 5 + 1) + "、");
//				} else {
//					this.write(selection, (pageNum * 5 + 1) + "-" + list.size()
//							+ "、");
//				}
//				for (int j = pageNum * 5; j < list.size(); j++) {
//					this.write(selection, list.get(j).getAnswer());
//					this.write(selection, "  ");
//				}
//				this.enterDown(selection, 1);
//			}
//		}
//	}
//
//	private void writeQuestion(List<QuestionItem> list, int start, int limit,
//			int pageNum) {
//		for (int i = 0; i < pageNum; i++) {
//			this.write(selection, start + 1 + "-" + limit + "、");
//			for (int j = start; j < limit; j++) {
//				this.write(selection, list.get(j).getAnswer());
//				this.write(selection, "  ");
//			}
//			start = start + 5;
//			limit = limit + 5;
//			this.enterDown(selection, 1);
//		}
//	}
//
//	public void work() {
//		ComThread.InitSTA();
//		work1();
//		try {
//			Java2word j2w = new Java2word(false);
//
//			j2w.toWord(this.inUrl, this.outUrl,
//					this.setTitle(this.questions.getTitle().split("，")));
//			Dispatch selection = Dispatch.get(word, "Selection").toDispatch();
//			j2w.moveStart(selection);
//			j2w.moveDown(selection, 10);
//
//			for (int i = 0; i < this.questions.getQgs().size(); i++) {
//				QuestionGroup qs = this.questions.getQgs().get(i);
//				if (qs.type.equals("单项选择题")) {
//					this.writeType1(qs, selection);
//				} else if (qs.type.equals("多项选择题")) {
//					this.writeType1(qs, selection);
//				} else if (qs.type.equals("判断题")) {
//					this.writeType2(qs, selection, 1, true);
//				} else if (qs.type.equals("简答题")) {
//					this.writeType2(qs, selection, 5, false);
//				}  else if (qs.type.equals("技能实务题")) {
//					this.writeType2(qs, selection, 5, false);
//				} 
////					else if (qs.type.equals("公文写作")) {
////					this.writeType3(qs, selection);
////				}
//				// else if (qs.type.equals("案例分析题")) {
//				// this.writeType4(qs, selection);
//				// }
////				else if (qs.type.equals("论述题")) {
////					this.writeType5(qs, selection);
////				}
//
//			}
//			Dispatch.call(j2w.doc, "Close", new Variant(true));
//			j2w.getWord().invoke("Quit", new Variant[0]);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			ComThread.Release();
//			word = null;
//		}
//	}
//
//	private void writeType5(QuestionGroup qs, Dispatch selection) {
//		int num;
//		num = 1;
//		this.writeType(qs, selection);
//		List<QuestionItem> list = qs.getQuestions();
//		for (int i = 0; i < list.size(); i++) {
//			this.write(selection, (num++) + "、");
//			this.write(selection, list.get(i).getContent());
//			this.enterDown(selection, 20);
//		}
//	}
//
//	private void writeType4(QuestionGroup qs, Dispatch selection) {
//		int num;
//		num = 1;
//		this.writeType(qs, selection);
//		List<QuestionItem> list = qs.getQuestions();
//		for (int i = 0; i < list.size(); i++) {
//			this.write(selection, (num++) + "、");
//			this.write(selection, list.get(i).getContent());
//			this.enterDown(selection, 1);
//			String str = list.get(i).getAnContent();
//			if (str.length() > 9) {
//				int snum = 1;
//				if (str.trim().endsWith(";"))
//					str = str.substring(0, str.length() - 1);
//				String[] ans = str.trim().split(";");
//				this.write(selection, "问：");
//				for (int j = 0; j < ans.length; j++) {
//					this.write(selection, "(" + snum++ + ")");
//					this.write(selection, ans[j].trim());
//					this.enterDown(selection, 1);
//				}
//			}
//			this.enterDown(selection, 10);
//		}
//	}
//
//	private void writeType3(QuestionGroup qs, Dispatch selection) { // 公文写作
//		int num;
//		num = 1;
//		this.writeType(qs, selection);
//		List<QuestionItem> list = qs.getQuestions();
//		for (int i = 0; i < list.size(); i++) {
//			this.write(selection, (num++) + "、");
//			this.write(selection, list.get(i).getContent());
//			this.enterDown(selection, 1);
//			this.moveRight(selection, 1318);
//			this.enterDown(selection, 1);
//		}
//	}
//
//	private void writeType2(QuestionGroup qs, Dispatch selection, int count,
//			boolean flag) { // 简答 判断
//		int num;
//		int snum;
//		num = 1;
//		this.writeType(qs, selection);
//		List<QuestionItem> list = qs.getQuestions();
//		for (int i = 0; i < list.size(); i++) {
//			if (flag)
//				this.write(selection, "【      】");
//			this.write(selection, (num++) + "、");
//			this.write(selection, list.get(i).getContent());
//			this.enterDown(selection, count);
//		}
//	}
//
//	private void writeType1(QuestionGroup qs, Dispatch selection) { // 选择题 单选 多选
//		int num;
//		num = 1;
//		this.writeType(qs, selection);
//		List<QuestionItem> list = qs.getQuestions();
//		for (int i = 0; i < list.size(); i++) {
//			this.write(selection, "【      】");
//			this.write(selection, (num++) + "、");
//			this.write(selection, list.get(i).getContent());
//			this.enterDown(selection, 1);
//
//			String ansStr = list.get(i).getAnContent().trim(); // 处理答案最后有一个分号的问题
//			if (ansStr.endsWith(";")) {
//				ansStr = ansStr.substring(0, ansStr.length() - 1);
//			}
//			String[] ans = ansStr.split(";");
//
//			String flag = "short";
//			for (int j = 0; j < ans.length; j++) {
//				if (ans[j].trim().length() > 12) {
//					flag = "long";
//					break;
//				}
//			}
//			if (flag.equals("long")) {
//				for (int j = 0; j < ans.length; j++) {
//					this.write(selection, (char) (j + 65) + "、");
//					this.write(selection, ans[j].trim());
//					this.enterDown(selection, 1);
//				}
//			} else if (flag.equals("short")) {
//				for (int j = 0; j < ans.length; j++) {
//					this.write(selection, (char) (j + 65) + "、");
//					this.write(selection, ans[j].trim());
//					if (j % 2 != 0) {
//						this.enterDown(selection, 1);
//					} else {
//						this.writeKo(selection,
//								28 - (ans[j].trim().length() * 2));
//					}
//					if (j == ans.length - 1 & ans.length % 2 != 0) {
//						this.enterDown(selection, 1);
//					}
//				}
//			}
//		}
//	}
//
//	public void setFont(Dispatch selection, String fontS, String size) {// 设置字体
//
//		Dispatch font = Dispatch.get(selection, "Font").toDispatch();
//		Dispatch.put(font, "Size", size);
//		Dispatch.put(font, "Name", new Variant(fontS));
//	}
//
//	public void setAlign(Dispatch selection, String alignS) {// 设置段落格式
//
//		Dispatch align = Dispatch.get(selection, "ParagraphFormat")
//				.toDispatch();
//		Dispatch.put(align, "Alignment", alignS); // 1:置中 2:靠右 3:靠左
//	}
//
//	public void enterDown(Dispatch selection, int count) { // 回车
//		for (int i = 0; i < count; i++) {
//			Dispatch.call(selection, "TypeParagraph");
//		}
//	}
//
//	public void write(Dispatch selection, String str) {// 插入文本字符串
//		Dispatch.put(selection, "Text", str);
//		moveRight(selection, 1);
//	}
//
//	public void writeKo(Dispatch selection, int count) {
//		String ko = "";
//		for (int i = 0; i < count; i++) {
//			ko += " ";
//		}
//		this.write(selection, ko);
//	}
//
//	/**
//	 * 题目描述（例如：一、单项选择题（。。。。。。））
//	 */
//	int bigNum = 1;
//
//	public void writeType(QuestionGroup qs, Dispatch selection) {
//		this.setFont(selection, "黑体", "12");
//		if (qs.getType().endsWith("单项选择题"))
//			this.write(selection, "一、");
//		else if (qs.getType().endsWith("多项选择题"))
//			this.write(selection, "二、");
//		else if (qs.getType().endsWith("判断题"))
//			this.write(selection, "三、");
//		else if (qs.getType().endsWith("简答题"))
//			this.write(selection, "四、");
//		else if (qs.getType().endsWith("技能实务题"))
//			this.write(selection, "五、");
////		else if (qs.getType().endsWith("公文写作"))
////			this.write(selection, "六、");
//		// else if (qs.getType().endsWith("案例分析题"))
//		// this.write(selection, "六、");
////		else if (qs.getType().endsWith("论述题"))
////			this.write(selection, "五、");
//		this.write(selection, qs.getType());
//		this.setFont(selection, "华文楷体", "12");
//		this.write(
//				selection,
//				"(共有" + qs.getCount() + "个小题，每小题" + qs.getPoint() + "分，共"
//						+ Double.parseDouble(qs.getCount())
//						* Double.parseDouble(qs.getPoint()) + "分。"
//						+ qs.getRemark() + ")");
//		enterDown(selection, 1);
//		this.setFont(selection, "宋体", "11");
//		this.setParaFormat(selection, 0, 2.25);
//	}
//
//	public HashMap<String, String> setTitle(String[] title) {
//		HashMap<String, String> data = new HashMap<String, String>();
//		if (title.length == 2) {
//			data.put("$年份$", title[0]);
//		}
//		return data;
//	}
//
//	public void setParaFormat(Dispatch selection, int align, double lineSpace) {
//		if (align < 0 || align > 4) {
//			align = 0;
//		}
//		if (lineSpace < 0 || lineSpace > 4) {
//			lineSpace = 0;
//		}
//
//		Dispatch alignment = Dispatch.get(selection, "ParagraphFormat")
//				.toDispatch();
//		Dispatch.put(alignment, "Alignment", align);
//
//		Dispatch.put(alignment, "LineSpacingRule", new Variant(lineSpace));
//	}
//
//	public static ActiveXComponent getWord() {
//		return word;
//	}
//
//	public static void setWord(ActiveXComponent word) {
//		Java2word.word = word;
//	}
//
//}
