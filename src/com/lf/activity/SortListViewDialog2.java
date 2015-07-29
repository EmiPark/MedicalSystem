package com.lf.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.bysj.zzx.R;
import com.edu.writewords.sortlistview.CharacterParser;
import com.edu.writewords.sortlistview.ClearEditText;
import com.edu.writewords.sortlistview.PinyinComparator2;
import com.edu.writewords.sortlistview.SideBar;
import com.edu.writewords.sortlistview.SideBar.OnTouchingLetterChangedListener;
import com.lf.adapter.SortAdapter2;
import com.lf.dialog.SerachDialog2;
import com.lf.entity.SearchEntity2;
import com.lf.web.Global.Connect;
import com.lf.web.Global.ConnectListener;
import com.lf.web.WebCommonTask;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import opensource.jpinyin.PinyinHelper;

/**
 * 搜索界面药品
 * 
 * @author wzg
 * 
 */
public class SortListViewDialog2 extends Dialog implements ConnectListener,
		OnItemClickListener ,OnClickListener{
	private Context context;
	private ListView ltWords;
	private SortAdapter2 adapter;
	private SideBar sideBar;
	private TextView dialog;
	private ClearEditText mClearEditText;
	private CharacterParser characterParser;
	private List<SearchEntity2> list;
	private List<SearchEntity2> listData;

	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator2 PinyinComparator2;

	public SortListViewDialog2(Context context) {
		super(context, R.style.dialog_ransparent);
		this.context = context;
		new WebCommonTask(context, this, "加载。。。。").execute(Connect.SEARCH,
				new SearchEntity2());
	}

	private void init() {
		setContentView(R.layout.pop_sortwords);
		ltWords = (ListView) findViewById(R.id.lt_sort_words);
		ltWords.setOnItemClickListener(this);
		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
		PinyinComparator2 = new PinyinComparator2();
		characterParser = CharacterParser.getInstance();

		adapter = new SortAdapter2(context, list);
		ltWords.setAdapter(adapter);
		sideBar.setTextView(dialog);

		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			public void onTouchingLetterChanged(String s) {
				// 该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					ltWords.setSelection(position);
				}

			}
		});

		// 根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		
		((Button)findViewById(R.id.btn_back)).setOnClickListener(this);
	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<SearchEntity2> filterDateList = new ArrayList<SearchEntity2>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = listData;
		} else {
			filterDateList.clear();
			for (SearchEntity2 sortModel : list) {
				String name = sortModel.getName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// 根据a-z进行排序
		Collections.sort(filterDateList, PinyinComparator2);
		adapter.updateListView(filterDateList);
	}

	@Override
	public void Succes(Connect connect, Object object) {
		listData = JSONObject.parseArray(object.toString(), SearchEntity2.class);
		for (SearchEntity2 entity : listData) {
			entity.setPre(PinyinHelper.getShortPinyin(
					entity.getName().substring(0, 1)).toUpperCase());
		}
		list = listData;
		init();
	}

	@Override
	public void Failed(String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		new SerachDialog2(context, listData.get(position)).show();
	}

	@Override
	public void onClick(View v) {
		dismiss();
	}
}
