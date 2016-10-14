package com.android.csiapp.Crime.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by user on 2016/10/14.
 */
public class BackAlertDialog extends AlertDialog{
    private Context mContext;
    private AlertDialog alertDialog;
    public BackAlertDialog(Context context) {
        super(context);
        mContext = context;
    }

    public void onCreateDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("警告");
        builder.setMessage("请填写必填项信息");
        builder.setPositiveButton("补全信息", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        })
        .setNegativeButton("退出", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                getOwnerActivity().finish();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }
}
