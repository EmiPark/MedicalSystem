package com.lf.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * viewparger的adapter
 * 
 * @author WZG
 * 
 */
public class ViewPagerAdapter extends PagerAdapter {

	List<View> viewLists;

	public ViewPagerAdapter(List<View> lists) {
		viewLists = lists;
	}

	/**
	 * 设置数据
	 * 
	 * @param list
	 */
	public void setData(List<View> list) {
		this.viewLists = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return viewLists.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(View view, int position, Object object) {
		((ViewPager) view).removeView(viewLists.get(position));
	}

	@Override
	public Object instantiateItem(View view, int position) {
		((ViewPager) view).addView(viewLists.get(position), 0);
		return viewLists.get(position);
	}
}