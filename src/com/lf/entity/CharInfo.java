package com.lf.entity;

import java.util.List;

public class CharInfo {
	// 标题
	private String title;
	// y轴数量
	private int yScaleNum;
	// y左侧的的数据
	private String[] yScaleLeftLable;
	// 与右侧的数据
	private String[] yScaleRightLable;
	// x轴数量
	private int xScaleNum;
	// x上面的数据
	private String[] xScaleUpLable;
	// x下面的数据
	private String[] xScaleDownLable;
	private List<LineInfo> lineInfos;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getyScaleNum() {
		return yScaleNum;
	}

	public void setyScaleNum(int yScaleNum) {
		this.yScaleNum = yScaleNum;
	}

	public String[] getyScaleLeftLable() {
		return yScaleLeftLable;
	}

	public void setyScaleLeftLable(String[] yScaleLeftLable) {
		this.yScaleLeftLable = yScaleLeftLable;
	}

	public String[] getyScaleRightLable() {
		return yScaleRightLable;
	}

	public void setyScaleRightLable(String[] yScaleRightLable) {
		this.yScaleRightLable = yScaleRightLable;
	}

	public int getxScaleNum() {
		return xScaleNum;
	}

	public void setxScaleNum(int xScaleNum) {
		this.xScaleNum = xScaleNum;
	}

	public String[] getxScaleUpLable() {
		return xScaleUpLable;
	}

	public void setxScaleUpLable(String[] xScaleUpLable) {
		this.xScaleUpLable = xScaleUpLable;
	}

	public String[] getxScaleDownLable() {
		return xScaleDownLable;
	}

	public void setxScaleDownLable(String[] xScaleDownLable) {
		this.xScaleDownLable = xScaleDownLable;
	}

	public List<LineInfo> getLineInfos() {
		return lineInfos;
	}

	public void setLineInfos(List<LineInfo> lineInfos) {
		this.lineInfos = lineInfos;
	}

}