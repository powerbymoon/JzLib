package com.example.administrator.jzlib.jzlib.GlobleData;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.jzlib.jzlib.Activity.MainActivity;
import com.example.administrator.jzlib.jzlib.Been.BookBeen;
import com.example.administrator.jzlib.jzlib.Fragment.StarFragment;

import net.tsz.afinal.FinalDb;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 * Created by Administrator on 2015/9/16.
 */
public class GlobleAtrr {
    public static MainActivity a;
    public static List<Map<String, Object>> mData;
    public static FinalDb db;
    public static final String MAIN_URL = "http://opac.jluzh.com/opac/openlink.php";
    public static List<BookBeen> mListViewData = new ArrayList<BookBeen>();
    public static StarFragment StarFrag;
    public static boolean no=false;
    public static HttpClient client=new DefaultHttpClient();
    public static boolean flag=false;

    public static FragmentManager mFragmentManager;
    public static Fragment mCurrentFragment;
}
