package org.flycloud.web.exam3.model;

/**
 * 试题显示格式(试题结构及显示及答案)
 * 
 * @author zhangbo
 *
 */
public enum QuestionFormat {
	IndividualChoice {
		@Override
		public String getName() {
			return "单项选择题";
		}

		@Override
		public Boolean autoRating() {
			return true;
		}
	},
	MultipleChoice {
		@Override
		public String getName() {
			return "多项选择题";
		}

		@Override
		public Boolean autoRating() {
			return true;
		}
	},
	BlankFilling {
		@Override
		public String getName() {
			return "填空题";
		}

		@Override
		public Boolean autoRating() {
			return true;
		}
	},
	Judgment {
		@Override
		public String getName() {
			return "判断题";
		}

		@Override
		public Boolean autoRating() {
			return true;
		}
	},
	ShortAnswer {
		@Override
		public String getName() {
			return "简答题";
		}

		@Override
		public Boolean autoRating() {
			return false;
		}
	},
	Description {
		@Override
		public String getName() {
			return "描述题";
		}

		@Override
		public Boolean autoRating() {
			return true;
		}
	},
	WordEdit {
		@Override
		public String getName() {
			return "文字编辑题";
		}

		@Override
		public Boolean autoRating() {
			return false;
		}
	};
	public abstract String getName();

	public abstract Boolean autoRating();
}
