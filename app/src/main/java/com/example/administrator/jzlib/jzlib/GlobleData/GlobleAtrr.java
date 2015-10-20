package com.example.administrator.jzlib.jzlib.GlobleData;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.jzlib.jzlib.Activity.MainActivity;
import com.example.administrator.jzlib.jzlib.Activity.PersonalActivity;
import com.example.administrator.jzlib.jzlib.Been.BookBeen;
import com.example.administrator.jzlib.jzlib.Been.HartCard;
import com.example.administrator.jzlib.jzlib.Been.LoveBeen;
import com.example.administrator.jzlib.jzlib.Fragment.HeartFragment;
import com.example.administrator.jzlib.jzlib.Fragment.StarFragment;

import net.tsz.afinal.FinalDb;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.kymjs.kjframe.KJDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 * Created by Administrator on 2015/9/16.
 */
public class GlobleAtrr {
    public static MainActivity a;
    public  static PersonalActivity p;
    public static List<Map<String, Object>> mData;
    public static FinalDb db;
    public static final String MAIN_URL = "http://opac.jluzh.com/opac/openlink.php";
    public static List<BookBeen> mListViewData = new ArrayList<BookBeen>();
    public static List<LoveBeen> mListViewLove = new ArrayList<LoveBeen>();
    public static StarFragment StarFrag;
    public static HeartFragment HeartFrag;
    public static boolean no=false;
    public static boolean hasbook=false;
    public static String cookies;
    public static HttpClient client=new DefaultHttpClient();
    public static boolean flag=false;
    public static List<Map<String, Object>> result=null;
    public static FragmentManager mFragmentManager;
    public static Fragment mCurrentFragment;
    public static boolean isLogin = false;
    public static KJDB love_db;

    public static final String SheHui = "http://opac.jluzh.com/top/top_lend.php?cls_no=C";
    public static final String ZheXue = "http://opac.jluzh.com/top/top_lend.php?cls_no=B";
    public static final String JunShi ="http://opac.jluzh.com/top/top_lend.php?cls_no=E";
    public static final String JinJi ="http://opac.jluzh.com/top/top_lend.php?cls_no=F";
    public static final String WenHua ="http://opac.jluzh.com/top/top_lend.php?cls_no=G";
    public static final String YuYan ="http://opac.jluzh.com/top/top_lend.php?cls_no=H";
    public static final String WenXue ="http://opac.jluzh.com/top/top_lend.php?cls_no=I";
    public static final String YiShu ="http://opac.jluzh.com/top/top_lend.php?cls_no=J";
    public static final String LiShi ="http://opac.jluzh.com/top/top_lend.php?cls_no=K";
    public static final String ZiRang ="http://opac.jluzh.com/top/top_lend.php?cls_no=N";
    public static final String ShuLi ="http://opac.jluzh.com/top/top_lend.php?cls_no=O";
    public static final String TianWen ="http://opac.jluzh.com/top/top_lend.php?cls_no=P";
    public static final String dongwu="http://opac.jluzh.com/top/top_lend.php?cls_no=Q";
    public static final String zhiwu="http://opac.jluzh.com/top/top_lend.php?cls_no=S";
    public static final String yiyao="http://opac.jluzh.com/top/top_lend.php?cls_no=R";
    public static final String gongye="http://opac.jluzh.com/top/top_lend.php?cls_no=T";
    public static final String zonghe="http://opac.jluzh.com/top/top_lend.php?cls_no=Z";
    public static final String MaLie = "http://opac.jluzh.com/top/top_lend.php?cls_no=A";
}
