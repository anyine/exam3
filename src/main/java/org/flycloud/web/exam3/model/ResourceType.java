package org.flycloud.web.exam3.model;

/**
 * 试题显示格式(试题结构及显示及答案)
 * 
 * @author zhangbo
 *
 */
public enum ResourceType {
	Question {
		@Override
		public String getName() {
			return "题目";
		}
	},
	Answer {
		@Override
		public String getName() {
			return "答案";
		}
	};

	public abstract String getName();
}
