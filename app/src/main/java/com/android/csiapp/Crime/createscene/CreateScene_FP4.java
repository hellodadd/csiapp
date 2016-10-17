package com.android.csiapp.Crime.createscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.csiapp.Crime.utils.PhotoAdapter;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.ImportantPhotoProvider;
import com.android.csiapp.Databases.OverviewPhotoProvider;
import com.android.csiapp.Databases.PhotoItem;
import com.android.csiapp.Databases.PositionPhotoProvider;
import com.android.csiapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateScene_FP4 extends Fragment {
    private Context context = null;
    private Uri LocalFileUri = null;
    private CrimeItem mItem;
    private PhotoItem mPositionItem;
    private int mEvent;

    List<PhotoItem> mPositionList;
    private ListView mPosition_List;
    private PhotoAdapter mPosition_Adapter;
    List<PhotoItem> mLikeList;
    private ListView mLike_List;
    private PhotoAdapter mLike_Adapter;
    List<PhotoItem> mImportantList;
    private ListView mImportant_List;
    private PhotoAdapter mImportant_Adapter;
    private ImageButton mAdd_Position;
    private ImageButton mAdd_Like;
    private ImageButton mAdd_Important;

    public static final int PHOTO_TYPE_POSITION = 1;
    public static final int PHOTO_TYPE_LIKE = 2;
    public static final int PHOTO_TYPE_IMPORTANT = 3;

    final int POSITION_PHOTO_DELETE = 4;
    final int LIKE_PHOTO_DELETE = 5;
    final int IMPORTANT_PHOTO_DELETE = 6;

    private PositionPhotoProvider mPositionPhotoProvider;
    private OverviewPhotoProvider mOverviewPhotoProvider;
    private ImportantPhotoProvider mImportantPhotoProvider;

    public CreateScene_FP4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp4, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        mItem = activity.getItem();
        mEvent = activity.getEvent();
        context = getActivity().getApplicationContext();

        initData();
        initView(view);

        mPositionPhotoProvider = new PositionPhotoProvider(context);
        mOverviewPhotoProvider = new OverviewPhotoProvider(context);
        mImportantPhotoProvider = new ImportantPhotoProvider(context);

        return view;
    }

    private void initView(View view){
        mAdd_Position = (ImageButton) view.findViewById(R.id.add_position_photo_imageButton);
        mAdd_Position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalFileUri = Uri.fromFile(getOutputMediaFile(context,PHOTO_TYPE_POSITION));
                takePhoto(LocalFileUri, PHOTO_TYPE_POSITION);
            }
        });

        mPosition_List=(ListView) view.findViewById(R.id.Position_photo_listview);
        mPosition_Adapter = new PhotoAdapter(context, mPositionList);
        mPosition_List.setAdapter(mPosition_Adapter);
        setListViewHeightBasedOnChildren(mPosition_List);

        mAdd_Like = (ImageButton) view.findViewById(R.id.add_like_photo_imageButton);
        mAdd_Like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalFileUri = Uri.fromFile(getOutputMediaFile(context,PHOTO_TYPE_LIKE));
                takePhoto(LocalFileUri, PHOTO_TYPE_LIKE);
            }
        });

        mLike_List=(ListView) view.findViewById(R.id.Like_photo_listview);
        mLike_Adapter = new PhotoAdapter(context, mLikeList);
        mLike_List.setAdapter(mLike_Adapter);
        setListViewHeightBasedOnChildren(mLike_List);

        mAdd_Important = (ImageButton) view.findViewById(R.id.add_important_photo_imageButton);
        mAdd_Important.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalFileUri = Uri.fromFile(getOutputMediaFile(context,PHOTO_TYPE_IMPORTANT));
                takePhoto(LocalFileUri, PHOTO_TYPE_IMPORTANT);
            }
        });

        mImportant_List=(ListView) view.findViewById(R.id.Important_photo_listview);
        mImportant_Adapter = new PhotoAdapter(context, mImportantList);
        mImportant_List.setAdapter(mImportant_Adapter);
        setListViewHeightBasedOnChildren(mImportant_List);

        registerForContextMenu(mPosition_List);
        registerForContextMenu(mLike_List);
        registerForContextMenu(mImportant_List);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        String delete = context.getResources().getString(R.string.list_delete);
        if (v.getId()==R.id.Position_photo_listview) {
            menu.add(0, POSITION_PHOTO_DELETE, 0, delete);
        }else if(v.getId()==R.id.Like_photo_listview){
            menu.add(0, LIKE_PHOTO_DELETE, 0, delete);
        }else if(v.getId()==R.id.Important_photo_listview){
            menu.add(0, IMPORTANT_PHOTO_DELETE, 0, delete);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case POSITION_PHOTO_DELETE:
                Log.d("Anita","mPositionList = "+mPositionList.size()+", position = "+info.position);
                if(mEvent == 2) mPositionPhotoProvider.delete(mPositionList.get(info.position).getId());
                mPositionList.remove(info.position);
                setListViewHeightBasedOnChildren(mPosition_List);
                mPosition_Adapter.notifyDataSetChanged();
                return true;
            case LIKE_PHOTO_DELETE:
                Log.d("Anita","mLikeList = "+mLikeList.size()+", position = "+info.position);
                if(mEvent == 2) mOverviewPhotoProvider.delete(mLikeList.get(info.position).getId());
                mLikeList.remove(info.position);
                setListViewHeightBasedOnChildren(mLike_List);
                mLike_Adapter.notifyDataSetChanged();
                return true;
            case IMPORTANT_PHOTO_DELETE:
                Log.d("Anita","mImportantList = "+mImportantList.size()+", position = "+info.position);
                if(mEvent == 2) mImportantPhotoProvider.delete(mImportantList.get(info.position).getId());
                mImportantList.remove(info.position);
                setListViewHeightBasedOnChildren(mImportant_List);
                mImportant_Adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void initData(){
        mPositionList = mItem.getPositionPhoto();
        mLikeList = mItem.getOverviewPhoto();
        mImportantList = mItem.getImportantPhoto();
    }

    private void saveData(){
        mItem.setPositionPhoto(mPositionList);
        mItem.setOverviewPhoto(mLikeList);
        mItem.setImportantPhoto(mImportantList);
    }

    @Override
    public void onResume(){
        super.onResume();
        initData();
        mPosition_Adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause(){
        super.onPause();
        saveData();
    }

    private void takePhoto(Uri LocalFileUri, int PHOTO_TYPE) {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        it.putExtra(MediaStore.EXTRA_OUTPUT, LocalFileUri);
        startActivityForResult(it, PHOTO_TYPE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String path = LocalFileUri.getPath();
        PhotoItem photoItem = new PhotoItem();
        photoItem.setPhotoPath(path);
        Log.d("Anita","LocalFile = "+path);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PHOTO_TYPE_POSITION) {
                Log.d("Camera", "Set image to PHOTO_TYPE_POSITION");
                if(mEvent == 2) photoItem.setId(mPositionPhotoProvider.insert(photoItem));
                mPositionList.add(photoItem);
                setListViewHeightBasedOnChildren(mPosition_List);
                mPosition_Adapter.notifyDataSetChanged();
            } else if (requestCode == PHOTO_TYPE_LIKE) {
                Log.d("Camera", "Set image to PHOTO_TYPE_LIKE");
                if(mEvent == 2) photoItem.setId(mOverviewPhotoProvider.insert(photoItem));
                mLikeList.add(photoItem);
                setListViewHeightBasedOnChildren(mLike_List);
                mLike_Adapter.notifyDataSetChanged();
            } else if (requestCode == PHOTO_TYPE_IMPORTANT) {
                Log.d("Camera", "Set image to PHOTO_TYPE_IMPORTANT");
                if(mEvent == 2) photoItem.setId(mImportantPhotoProvider.insert(photoItem));
                mImportantList.add(photoItem);
                setListViewHeightBasedOnChildren(mImportant_List);
                mImportant_Adapter.notifyDataSetChanged();
            }
        }
    }

    private void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()获取ListView对应的Adapter
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    private File getOutputMediaFile(Context context, int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File( context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Report");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == PHOTO_TYPE_POSITION){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "POSITION_"+ timeStamp + ".jpg");
        } else if(type == PHOTO_TYPE_LIKE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "LIKE_"+ timeStamp + ".jpg");
        } else if (type == PHOTO_TYPE_IMPORTANT) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMPORTANT_"+ timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }
}
