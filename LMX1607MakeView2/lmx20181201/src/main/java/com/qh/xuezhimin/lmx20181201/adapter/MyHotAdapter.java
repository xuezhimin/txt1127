package com.qh.xuezhimin.lmx20181201.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qh.xuezhimin.lmx20181201.R;

import java.util.List;

public class MyHotAdapter extends BaseAdapter {


    private Context mContext;
    private List<String> list_hot;


    public MyHotAdapter(Context mContext, List<String> list_hot) {
        this.mContext = mContext;
        this.list_hot = list_hot;
    }

    @Override
    public int getCount() {
        return list_hot.size();
    }

    @Override
    public Object getItem(int position) {
        return list_hot.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_hot_list, null);
            viewHolder.title = convertView.findViewById(R.id.item_hot_gird);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(list_hot.get(position));

        return convertView;
    }

    class ViewHolder {
        private TextView title;
    }

}
