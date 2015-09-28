package com.example.administrator.jzlib.jzlib.GlobleData;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/9/16.
 */
public class GlobleMeth {

    public static void showToast(Context c, String s) {
        Toast.makeText(c, s, Toast.LENGTH_LONG).show();

    }
    public static boolean hasInternet(Activity a) {
        ConnectivityManager manager = (ConnectivityManager) a
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            return false;
        }
        if (info.isRoaming()) {
            return true;
        }
        return true;
    }

}

