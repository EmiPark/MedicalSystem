package com.edu.writewords.sortlistview;

import java.util.Comparator;

import com.lf.entity.SearchEntity2;

/**
 * 字母比較器
 * @author WZG
 *
 */
public class PinyinComparator2 implements Comparator<SearchEntity2> {

	public int compare(SearchEntity2 o1, SearchEntity2 o2) {
		if (o1.getPre().equals("@")
				|| o2.getPre().equals("#")) {
			return -1;
		} else if (o1.getPre().equals("#")
				|| o2.getPre().equals("@")) {
			return 1;
		} else {
			return o1.getPre().compareTo(o2.getPre());
		}
	}

}
