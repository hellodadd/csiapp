package com.android.csiapp.Crime.createscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
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

import com.android.csiapp.Crime.utils.EvidenceAdapter;
import com.android.csiapp.Crime.utils.PhotoAdapter;
import com.android.csiapp.Crime.utils.Priview_photo_Activity;
import com.android.csiapp.Databases.CameraPhotoProvider;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.CrimeProvider;
import com.android.csiapp.Databases.EvidenceItem;
import com.android.csiapp.Databases.EvidenceProvider;
import com.android.csiapp.Databases.MonitoringPhotoProvider;
import com.android.csiapp.Databases.PhotoItem;
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
public class CreateScene_FP5 extends Fragment {
    private Context context = null;
    private Uri LocalFileUri = null;
    private CrimeItem mItem;
    private EvidenceItem mEvidenceItem;
    private int mEvent;

    List<EvidenceItem> mEvidenceList;
    private ListView mEvidence_List;
    private EvidenceAdapter mEvidence_Adapter;
    private ImageButton mAdd_Scene_Evidence;
    List<PhotoItem> mMonitoringList;
    private ListView mMonitoring_List;
    private PhotoAdapter mMonitoring_Adapter;
    private ImageButton mAdd_Monitoring;
    List<PhotoItem> mCameraList;
    private ListView mCamera_List;
    private PhotoAdapter mCamera_Adapter;
    private ImageButton mAdd_Camera;

    public static final int TYPE_EVIDENCE = 0;
    public static final int PHOTO_TYPE_MONITORING = 1;
    public static final int PHOTO_TYPE_CAMERA= 2;

    final int EVIDENCE_DELETE = 8;
    final int MONITORING_DELETE = 9;
    final int CAMERA_DELETE = 10;

    private EvidenceProvider mEvidenceProvider;
    private MonitoringPhotoProvider mMonitoringPhotoProvider;
    private CameraPhotoProvider mCameraPhotoProvider;

    public CreateScene_FP5() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp5, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        if(savedInstanceState == null){
            mItem = activity.getItem();
            mEvent = activity.getEvent();
        }else{
            mItem = (CrimeItem) savedInstanceState.getSerializable("CrimeItem");
            mEvent = (int) savedInstanceState.getSerializable("Event");
        }
        context = getActivity().getApplicationContext();

        initData();
        initView(view);

        mEvidenceProvider = new EvidenceProvider(context);
        mMonitoringPhotoProvider = new MonitoringPhotoProvider(context);
        mCameraPhotoProvider = new CameraPhotoProvider(context);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("CrimeItem",mItem);
        outState.putSerializable("Event",mEvent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {
            savedInstanceState.putSerializable("CrimeItem", mItem);
            savedInstanceState.putSerializable("Event", mEvent);
        }
    }

    private void initView(View view){
        mEvidenceItem = new EvidenceItem();
        mAdd_Scene_Evidence = (ImageButton) view.findViewById(R.id.add_scene_evidence);
        mAdd_Scene_Evidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP5_NewEvidenceActivity.class);
                it.putExtra("com.android.csiapp.Databases.Item", mItem);
                it.putExtra("com.android.csiapp.Databases.EvidenceItem", mEvidenceItem);
                it.putExtra("Event",1);
                startActivityForResult(it, TYPE_EVIDENCE);
            }
        });

        mEvidence_List=(ListView) view.findViewById(R.id.evidence_listview);
        mEvidence_Adapter = new EvidenceAdapter(context, mEvidenceList);
        mEvidence_List.setAdapter(mEvidence_Adapter);
        setListViewHeightBasedOnChildren(mEvidence_List);
        AdapterView.OnItemClickListener itemListener1 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getActivity(), CreateScene_FP5_NewEvidenceActivity.class);
                mEvidenceItem = mEvidenceList.get(position);
                it.putExtra("com.android.csiapp.Databases.Item", mItem);
                it.putExtra("com.android.csiapp.Databases.EvidenceItem", mEvidenceItem);
                it.putExtra("Event",2);
                it.putExtra("Position", position);
                startActivityForResult(it, TYPE_EVIDENCE);
            }
        };
        mEvidence_List.setOnItemClickListener(itemListener1);

        mAdd_Monitoring = (ImageButton) view.findViewById(R.id.add_monitoring_screen);
        mAdd_Monitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalFileUri = Uri.fromFile(getOutputMediaFile(context, PHOTO_TYPE_MONITORING));
                takePhoto(LocalFileUri, PHOTO_TYPE_MONITORING);
            }
        });

        mMonitoring_List=(ListView) view.findViewById(R.id.monitoring_photo_listview);
        mMonitoring_Adapter = new PhotoAdapter(context, mMonitoringList);
        mMonitoring_List.setAdapter(mMonitoring_Adapter);
        mMonitoring_List.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                Intent it = new Intent(getActivity(), Priview_photo_Activity.class);
                it.putExtra("Path",mMonitoringList.get(position).getPhotoPath());
                startActivityForResult(it, 100);
            }
        });
        setListViewHeightBasedOnChildren(mMonitoring_List);

        mAdd_Camera = (ImageButton) view.findViewById(R.id.add_camera_position);
        mAdd_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalFileUri = Uri.fromFile(getOutputMediaFile(context, PHOTO_TYPE_CAMERA));
                takePhoto(LocalFileUri, PHOTO_TYPE_CAMERA);
            }
        });

        mCamera_List=(ListView) view.findViewById(R.id.camera_photo_listview);
        mCamera_Adapter = new PhotoAdapter(context, mCameraList);
        mCamera_List.setAdapter(mCamera_Adapter);
        mCamera_List.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                Intent it = new Intent(getActivity(), Priview_photo_Activity.class);
                it.putExtra("Path",mCameraList.get(position).getPhotoPath());
                startActivityForResult(it, 100);
            }
        });
        setListViewHeightBasedOnChildren(mCamera_List);

        registerForContextMenu(mEvidence_List);
        registerForContextMenu(mMonitoring_List);
        registerForContextMenu(mCamera_List);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        String delete = context.getResources().getString(R.string.list_delete);
        if (v.getId()==R.id.evidence_listview) {
            menu.add(0, EVIDENCE_DELETE, 0, delete);
        }else if(v.getId()==R.id.monitoring_photo_listview){
            menu.add(0, MONITORING_DELETE, 0, delete);
        }else if(v.getId()==R.id.camera_photo_listview){
            menu.add(0, CAMERA_DELETE, 0, delete);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case EVIDENCE_DELETE:
                if(mEvent == 2) mEvidenceProvider.delete(mEvidenceList.get(info.position).getId());
                mEvidenceList.remove(info.position);
                setListViewHeightBasedOnChildren(mEvidence_List);
                mEvidence_Adapter.notifyDataSetChanged();
                return true;
            case MONITORING_DELETE:
                if(mEvent == 2) mMonitoringPhotoProvider.delete(mMonitoringList.get(info.position).getId());
                mMonitoringList.remove(info.position);
                setListViewHeightBasedOnChildren(mMonitoring_List);
                mMonitoring_Adapter.notifyDataSetChanged();
                return true;
            case CAMERA_DELETE:
                if(mEvent == 2) mCameraPhotoProvider.delete(mCameraList.get(info.position).getId());
                mCameraList.remove(info.position);
                setListViewHeightBasedOnChildren(mCamera_List);
                mCamera_Adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void initData(){
        mEvidenceList = mItem.getEvidenceItem();
        mMonitoringList = mItem.getMonitoringPhoto();
        mCameraList = mItem.getCameraPhoto();
    }

    private void saveData(){
        mItem.setEvidenceItem(mEvidenceList);
        mItem.setMonitoringPhoto(mMonitoringList);
        mItem.setCameraPhoto(mCameraList);
    }

    @Override
    public void onResume(){
        super.onResume();
        initData();
        mEvidence_Adapter.notifyDataSetChanged();
        mMonitoring_Adapter.notifyDataSetChanged();
        mCamera_Adapter.notifyDataSetChanged();
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
        Log.d("Photo","onActivityResult");
        if (resultCode == Activity.RESULT_OK) {
            if(requestCode == TYPE_EVIDENCE) {
                EvidenceItem evidenceItem = (EvidenceItem) data.getSerializableExtra("com.android.csiapp.Databases.EvidenceItem");
                int event = (int) data.getIntExtra("Event", 1);
                int position = (int) data.getIntExtra("Position", 0);
                if (mEvent == 2 && event == 1)
                    evidenceItem.setId(mEvidenceProvider.insert(evidenceItem));
                if (event == 1) {
                    mEvidenceList.add(evidenceItem);
                } else {
                    mEvidenceList.set(position, evidenceItem);
                }
                setListViewHeightBasedOnChildren(mEvidence_List);
                mEvidence_Adapter.notifyDataSetChanged();
            } else if (requestCode == PHOTO_TYPE_MONITORING) {
                String path = LocalFileUri.getPath();
                PhotoItem photoItem = new PhotoItem();
                photoItem.setPhotoPath(path);
                photoItem.setUuid(CrimeProvider.getUUID());
                Log.d("Camera", "Set image to PHOTO_TYPE_MONITORING");
                if(mEvent == 2) photoItem.setId(mMonitoringPhotoProvider.insert(photoItem));
                mMonitoringList.add(photoItem);
                setListViewHeightBasedOnChildren(mMonitoring_List);
                mMonitoring_Adapter.notifyDataSetChanged();
            } else if (requestCode == PHOTO_TYPE_CAMERA) {
                String path = LocalFileUri.getPath();
                PhotoItem photoItem = new PhotoItem();
                photoItem.setPhotoPath(path);
                photoItem.setUuid(CrimeProvider.getUUID());
                Log.d("Camera", "Set image to PHOTO_TYPE_CAMERA");
                if(mEvent == 2) photoItem.setId(mCameraPhotoProvider.insert(photoItem));
                mCameraList.add(photoItem);
                setListViewHeightBasedOnChildren(mCamera_List);
                mCamera_Adapter.notifyDataSetChanged();
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
        if (type == PHOTO_TYPE_MONITORING){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "MONITORING_PHOTO_"+ timeStamp + ".jpg");
        } else if(type == PHOTO_TYPE_CAMERA) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "CAMERA_PHOTO_"+ timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }
}
