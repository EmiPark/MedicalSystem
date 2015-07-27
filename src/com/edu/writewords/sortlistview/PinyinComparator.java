package com.edu.writewords.sortlistview;

import java.util.Comparator;

import com.lf.entity.SearchEntity;

/**
 * 字母比較器
 * @author WZG
 *
 */
public class PinyinComparator implements Comparator<SearchEntity> {

	public int compare(SearchEntity o1, SearchEntity o2) {
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
