package org.flycloud.web.exam3.model;

public enum QuestionLevel {
	Level0 {
		@Override
		public String getName() {
			return "容易";
		}
	},
	Level1 {
		@Override
		public String getName() {
			return "较易";
		}
	},
	Level2 {
		@Override
		public String getName() {
			return "一般";
		}
	},
	Level3 {
		@Override
		public String getName() {
			return "较难";
		}
	},
	Level4 {
		@Override
		public String getName() {
			return "困难";
		}
	};
	public abstract String getName();

	public static QuestionLevel getByName(String name) {
		for (QuestionLevel f : QuestionLevel.values()) {
			if (f.getName().equals(name)) {
				return f;
			}
		}
		return null;
	}
}
