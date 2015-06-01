package org.flycloud.web.exam3.util;

import java.util.ArrayList;
import java.util.List;

public class IndexGenerator {

	List<Integer> indexes = new ArrayList<Integer>();
	int currentLevel = 0;

	public IndexGenerator() {
		indexes.clear();
		currentLevel = 0;
		indexes.add(0);
	}

	public String getIndex(int level) {
		level--;
		if (level == currentLevel) {
			indexes.set(level, indexes.get(level) + 1);
		} else if (level < currentLevel) {
			int i = currentLevel - level;
			while (i-- > 0) {
				indexes.remove(indexes.size() - 1);
			}
			indexes.set(level, indexes.get(level) + 1);
			currentLevel = level;
		} else if (level == currentLevel + 1) {
			indexes.add(1);
			currentLevel++;
		} else {
			return "";
		}
		return getString();
	}

	public String getIndent(int level) {
		String ret = "";
		while (level-- > 1) {
			ret += "    ";
		}
		return ret;
	}

	private String getString() {
		String index = "";
		for (Integer i : indexes) {
			index += i + ".";
		}
		return index.replaceFirst("\\.$", "");
	}

	public static void main(String[] args) {
		IndexGenerator ig = new IndexGenerator();
		ig.reset();
		System.out.println(ig.getIndex(1));
		System.out.println(ig.getIndex(2));
		System.out.println(ig.getIndex(3));
		System.out.println(ig.getIndex(3));
		System.out.println(ig.getIndex(1));
		System.out.println(ig.getIndex(2));
		System.out.println(ig.getIndex(3));
		System.out.println(ig.getIndex(3));
		System.out.println(ig.getIndex(3));
		System.out.println(ig.getIndex(4));
		System.out.println(ig.getIndex(3));
	}

	private void reset() {
		indexes.clear();
		currentLevel = 0;
		indexes.add(0);
	}
}
