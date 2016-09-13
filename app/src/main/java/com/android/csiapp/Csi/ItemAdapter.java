package com.android.csiapp.Csi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.csiapp.Item;
import com.android.csiapp.R;

import java.util.List;

/**
 * Created by AnitaLin on 2016/9/12.
 */
public class ItemAdapter extends BaseAdapter {

    private LayoutInflater myInflater;
    private List<Item> items;

    public ItemAdapter(Context context, List<Item> items){
        myInflater = LayoutInflater.from(context);
        this.items = items;
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
            convertView = myInflater.inflate(R.layout.listview_display, null);
            holder = new ViewHolder(
                (TextView) convertView.findViewById(R.id.casetype),
                (TextView) convertView.findViewById(R.id.area),
                (TextView) convertView.findViewById(R.id.time)
            );
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Item item = (Item)getItem(position);
        holder.txtCasetype.setText(((Item) getItem(position)).getCasetype());
        holder.txtArea.setText(((Item) getItem(position)).getArea());
        holder.txtTime.setText(((Item) getItem(position)).getTime());
        return convertView;
    }

    private class ViewHolder {
        TextView txtCasetype;
        TextView txtArea;
        TextView txtTime;
        public ViewHolder(TextView txtCasetype, TextView txtArea, TextView txtTime){
            this.txtCasetype = txtCasetype;
            this.txtArea = txtArea;
            this.txtTime = txtTime;
        }
    }
}
