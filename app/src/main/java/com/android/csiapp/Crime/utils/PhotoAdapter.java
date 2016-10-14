package com.android.csiapp.Crime.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.csiapp.Databases.PhotoItem;
import com.android.csiapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * Created by user on 2016/10/7.
 */
public class PhotoAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    private List<PhotoItem> items;

    public PhotoAdapter(Context context, List<PhotoItem> items){
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
            convertView = myInflater.inflate(R.layout.photoadapterview, null);
            holder = new ViewHolder(
                    (ImageView) convertView.findViewById(R.id.photo)
            );
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        PhotoItem item = (PhotoItem)getItem(position);
        String path = item.getPhotoPath();
        Log.d("Anita", "path = "+path);
        if(!path.isEmpty()){
            Bitmap bitmap = null;
            bitmap = loadBitmapFromFile(new File(path));
            if(bitmap!=null) holder.txtItemPhoto.setImageBitmap(bitmap);
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView txtItemPhoto;
        public ViewHolder(ImageView txtItemPhoto){
            this.txtItemPhoto = txtItemPhoto;
        }
    }

    public static Bitmap loadBitmapFromFile(File f) {
        Bitmap b = null;
        BitmapFactory.Options option = new BitmapFactory.Options();
        // Bitmap sampling factor, size = (Original Size)/(inSampleSize)
        option.inSampleSize = 4;

        try {
            FileInputStream fis = new FileInputStream(f);
            b = BitmapFactory.decodeStream(fis, null, option);
            fis.close();

            Matrix matrix = new Matrix();
            matrix.setRotate(0, (float)b.getWidth()/2, (float)b.getHeight()/2);
            Bitmap resultImage = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, true);

            return resultImage;
        } catch(Exception e) {
            return null;
        }
    }
}
