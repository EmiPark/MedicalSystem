package com.edu.writewords.sortlistview;

public class SortModel {
	/**
	 * 单词名
	 */
	private String name; 
	/**
	 * 单词首字母
	 */
	private String sortLetters;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
}
