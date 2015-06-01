package org.flycloud.web.exam3.model.format;

public interface IQuestionFormat {
	
	/**
	 * 格式的名称
	 * @return
	 */
	public String getName();
	
	/**
	 * 能否自动评分
	 * @return
	 */
	public boolean autoRating();
	
}
