package com.lf.entity;
/**
 * 每条线的数据
 * @author WZG
 *
 */
public class LineInfo {  
	//代表名
    private String name;  
//    点的颜色
    private int pointColor;  
//    线的颜色
    private int lineColor;  
//    曲线点
    private int[] points;  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public int getPointColor() {  
        return pointColor;  
    }  
  
    public void setPointColor(int pointColor) {  
        this.pointColor = pointColor;  
    }  
  
    public int getLineColor() {  
        return lineColor;  
    }  
  
    public void setLineColor(int lineColor) {  
        this.lineColor = lineColor;  
    }  
  
    public int[] getPoints() {  
        return points;  
    }  
  
    public void setPoints(int[] points) {  
        this.points = points;  
    }  
  
}  