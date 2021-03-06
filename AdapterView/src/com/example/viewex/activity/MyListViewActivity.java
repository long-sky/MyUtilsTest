package com.example.viewex.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.adapterview.R;
import com.example.viewex.adapter.MyAdapter;
import com.example.viewex.widget.adapterview.MyListView;
import com.example.viewex.widget.adapterview.MyListView.OnDeleteListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MyListViewActivity extends Activity {
	private MyListView mMyListView;
	private MyAdapter mAdapter;
	private List<String> mContentList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_list_view);
		initList();
		mMyListView = (MyListView) findViewById(R.id.my_list_view);
		mMyListView.setOnDeleteListener(new OnDeleteListener() {
			@Override
			public void onDelete(int index) {
				mContentList.remove(index);
				mAdapter.notifyDataSetChanged();
			}
		});
		mAdapter = new MyAdapter(this, 0, mContentList);
		mMyListView.setAdapter(mAdapter);
	}

	private void initList() {
		mContentList.add("Content Item 1");
		mContentList.add("Content Item 2");
		mContentList.add("Content Item 3");
		mContentList.add("Content Item 4");
		mContentList.add("Content Item 5");
		mContentList.add("Content Item 6");
		mContentList.add("Content Item 7");
		mContentList.add("Content Item 8");
		mContentList.add("Content Item 9");
		mContentList.add("Content Item 10");
		mContentList.add("Content Item 11");
		mContentList.add("Content Item 12");
		mContentList.add("Content Item 13");
		mContentList.add("Content Item 14");
		mContentList.add("Content Item 15");
		mContentList.add("Content Item 16");
		mContentList.add("Content Item 17");
		mContentList.add("Content Item 18");
		mContentList.add("Content Item 19");
		mContentList.add("Content Item 20");
	}
}
