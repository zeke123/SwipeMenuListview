package com.example.test;

import java.util.ArrayList;
import java.util.List;

import com.example.test.SlideView.OnSlideListener;
import com.example.test.base.BaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class DeleteListViewMainActivity extends BaseActivity implements OnItemClickListener, OnClickListener,
        OnSlideListener {

    private static final String TAG = "MainActivity";

    private ListViewCompat mListView;

    private List<MessageItem> mMessageItems = new ArrayList<DeleteListViewMainActivity.MessageItem>();

    private SlideView mLastSlideViewWithStatusOn;
    
    private SlideAdapter adapter;
    
	@Override
	public void setView() {
		 setContentView(R.layout.activity_main);
		
	}

	@Override
	public void initView() {
		  mListView = (ListViewCompat) findViewById(R.id.listview);

	        for (int i = 0; i < 20; i++) {
	            MessageItem item = new MessageItem();
	            if (i % 3 == 0) {
	                item.name = "周建"+i;
	                item.phonenumber = "187777777";
	                item.address = "阿发达时看风景阿卡丽的说法就爱看的设计费的书法家李开";
	            } else {
	                item.name = "周瑜"+i;
	                item.phonenumber = "187777777";
	                item.address = "阿发达时看风景阿卡丽的说法就爱看的设计费的书法家李开";
	            }
	            mMessageItems.add(item);
	        }
	        
			adapter = new SlideAdapter();
	        mListView.setAdapter(adapter);
	        mListView.setOnItemClickListener(this);
		
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		
	}

	
	
    private class SlideAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        SlideAdapter() {
            super();
            mInflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return mMessageItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mMessageItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            SlideView slideView = (SlideView) convertView;
            if (slideView == null) {
                View itemView = mInflater.inflate(R.layout.item_list_receiving, null);

                slideView = new SlideView(DeleteListViewMainActivity.this);
                slideView.setContentView(itemView);

                holder = new ViewHolder(slideView);
                slideView.setOnSlideListener(DeleteListViewMainActivity.this);
                slideView.setTag(holder);
            } else {
                holder = (ViewHolder) slideView.getTag();
            }
            MessageItem item = mMessageItems.get(position);
            item.slideView = slideView;
            item.slideView.shrink();

            holder.name.setText(item.name);
            holder.phonenumber.setText(item.phonenumber);
            holder.address.setText(item.address);
            holder.deleteHolder.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					mMessageItems.remove(position);
			    	adapter.notifyDataSetChanged();
			    	Toast.makeText(DeleteListViewMainActivity.this, "删除第" + position+"个条目", 0).show();
				}
			});
            
            return slideView;
        }

    }

    public class MessageItem {
        public String name;
        public String phonenumber;
        public String address;
        public SlideView slideView;
    }

    private static class ViewHolder {
    	public ImageButton ivButton;
        public TextView name;
        public TextView phonenumber;
        public TextView address;
        public ViewGroup deleteHolder;

        ViewHolder(View view) {
        	name = (TextView) view.findViewById(R.id.tv_name);
        	phonenumber = (TextView) view.findViewById(R.id.tv_phonenumber);
        	address = (TextView) view.findViewById(R.id.tv_address);
            deleteHolder = (ViewGroup)view.findViewById(R.id.holder);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        Toast.makeText(this, "onItemClick position=" + position, 0).show();
    	
    }

    @Override
    public void onSlide(View view, int status) {
        if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
            mLastSlideViewWithStatusOn.shrink();
        }

        if (status == SLIDE_STATUS_ON) {
            mLastSlideViewWithStatusOn = (SlideView) view;
        }
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()) {
	case R.id.holder:
		
		break;

	default:
		break;
	}
    }

}
