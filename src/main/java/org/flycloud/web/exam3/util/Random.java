package org.flycloud.web.exam3.util;

import java.util.LinkedList;
import java.util.List;

public class Random {

	public static void add(LinkedList<Integer> list, Integer item) {
		boolean added = false;
		for (int i = 0; i < list.size(); i++) {
			if (item < list.get(i)) {
				list.add(i, item);
				added = true;
				break;
			}
		}
		if (!added) {
			list.add(item);
		}
	}

	public static List<Integer> random(int size, int count) {
		LinkedList<Integer> items = new LinkedList<Integer>();
		for (int i = 0; i < count; i++) {
			int rand = (int) (Math.random() * (double) (size - items.size()));
			Integer ii = find(items, rand);
			add(items, ii);
		}
		return items;
	}

	private static Integer find(LinkedList<Integer> items, int rand) {
		for (Integer i : items) {
			if (i <= rand) {
				rand++;
			}
		}
		return rand;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (Integer i : random(1000000, 20)) {
			System.out.println(i);
		}
	}
}
