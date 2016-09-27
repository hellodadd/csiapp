package com.android.csiapp.Crime.createscene;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.R;

import java.util.List;

/**
 * Created by user on 2016/9/27.
 */
public class Adapter extends BaseAdapter {

    private LayoutInflater myInflater;
    private List<CrimeItem> items;
    private int mListId;

    public Adapter(Context context, List<CrimeItem> items, int listId){
        myInflater = LayoutInflater.from(context);
        this.items = items;
        this.mListId = listId;
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
        return items.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView = myInflater.inflate(R.layout.adapterview, null);
            holder = new ViewHolder(
                    (TextView) convertView.findViewById(R.id.ItemName),
                    (TextView) convertView.findViewById(R.id.ItemContent)
            );
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        CrimeItem item = (CrimeItem)getItem(position);
        if(mListId == 1) {
            holder.txtItemName.setText(((CrimeItem) getItem(position)).getPeopleReleation());
            holder.txtItemContent.setText(((CrimeItem) getItem(position)).getPeopleName());
        }else if(mListId == 2){
            holder.txtItemName.setText("項目名稱");
            holder.txtItemContent.setText(((CrimeItem) getItem(position)).getItemName());
        }else if(mListId == 3){
            holder.txtItemName.setText("工具名稱");
            holder.txtItemContent.setText(((CrimeItem) getItem(position)).getToolName());
        }else if(mListId == 4){
            holder.txtItemName.setText("见证人");
            holder.txtItemContent.setText(((CrimeItem) getItem(position)).getWitnessName());
        }
        return convertView;
    }

    private class ViewHolder {
        TextView txtItemName;
        TextView txtItemContent;
        public ViewHolder(TextView txtItemName, TextView txtItemContent){
            this.txtItemName = txtItemName;
            this.txtItemContent = txtItemContent;
        }
    }
}
