package com.android.csiapp.Crime.listscene;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.csiapp.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2016/10/18.
 */
public class ListSearchAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    private List<String> items;
    // 用来控制CheckBox的选中状况
    private HashMap<Integer, Boolean> isSelected;

    public ListSearchAdapter(Context context, List<String> items, HashMap<Integer,Boolean> isSelected){
        myInflater = LayoutInflater.from(context);
        this.items = items;
        this.isSelected = isSelected;
        for (int i = 0; i < items.size(); i++) {
            getIsSelected().put(i, false);
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView = myInflater.inflate(R.layout.listview_search, null);
            holder = new ViewHolder();
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            holder.txt = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        // 监听checkBox并根据原来的状态来设置新的状态
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isSelected.get(position)) {
                    isSelected.put(position, false);
                    setIsSelected(isSelected);
                } else {
                    isSelected.put(position, true);
                    setIsSelected(isSelected);
                }
                notifyDataSetChanged();
            }
        });

        // 根据isSelected来设置checkbox的选中状况
        holder.checkBox.setChecked(getIsSelected().get(position));

        //setText
        holder.txt.setText(((String) getItem(position)));

        return convertView;
    }

    private class ViewHolder {
        CheckBox checkBox;
        TextView txt;
    }
}
