package com.example.administrator.jzlib.jzlib.Dialog;

import android.app.ProgressDialog;
import android.content.Context;

import android.graphics.Color;
import android.os.CountDownTimer;
import com.example.administrator.jzlib.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2015/9/22.
 */
public class Progress {
    int i=0;
    Context con;
    final SweetAlertDialog pDialog;
    public Progress(Context context, int alerType) {
        this.con = context;
        pDialog = new SweetAlertDialog(context,alerType).setTitleText("Loading");
        //pDialog.setCancelable(false);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#5CB3FF"));
        new CountDownTimer(800 * 20, 800) {
            public void onTick(long millisUntilFinished) {
                // you can change the progress bar color by ProgressHelper every 800 millis
                i++;
                switch (i){
                    case 0:
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#3BB9FF"));
                        break;
                    case 1:
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#F778A1"));
                        break;
                    case 2:
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#5CB3FF"));
                        break;
                    case 3:
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#00E3E3"));
                        break;
                    case 4:
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#FFFF00"));
                        break;
                    case 5:
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#347C17"));
                        break;
                    case 6:
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        break;
                    case 7:
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#3BB9FF"));
                        break;

                }
            }

            @Override
            public void onFinish() {
                i = -1;
                pDialog.setTitleText("联网超时")
                        .setConfirmText("确认")
                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);
            }
        }.start();

    }
     public void show(){
    pDialog.show();
}
  public void cancel(){
      pDialog.cancel();
      pDialog.dismiss();
  }
}

