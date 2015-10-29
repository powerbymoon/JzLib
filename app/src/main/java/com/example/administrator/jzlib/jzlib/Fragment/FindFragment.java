package com.example.administrator.jzlib.jzlib.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.format.Time;
import android.text.method.ScrollingMovementMethod;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.Activity.ResultActivity;
import com.example.administrator.jzlib.jzlib.Been.LoveBeen;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleMeth;
import com.example.administrator.jzlib.jzlib.Util.JsoupUtil;
import com.quinny898.library.persistentsearch.SearchBox;

import net.tsz.afinal.FinalBitmap;

import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.bitmap.BitmapMemoryCache;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by Administrator on 2015/9/15.
 */
public class FindFragment extends Fragment {
    KJBitmap kjb;
    private MaterialDialog commentMd;
    private View commentV;
    SharedPreferences pre_article;
    private SearchBox search;
    private Toolbar toolbar;
    private String html;
    TextView mTextView = null;
    TextView smallDate;
    TextView name;
    ImageButton img_love;
    //SpannableString msp = null;
    TextView date=null;
    ImageView pic;
    View view;
    TextView article;
    String html_art="http://blog.sina.com.cn/s/articlelist_5704243491_0_1.html";
    private String htmlpic="http://photo.blog.sina.com.cn/u/5704243491";
    final String HTML1 = "?strSearchType=title&match_flag=forward&historyCount=1&strText=";
    final String HTML2 = "&doctype=ALL&displaypg=20&showmode=list&sort=CATA_DATE&orderby=desc&dept=ALL";
    SharedPreferences isCheck=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //GlobleMeth.showToast(getActivity(),"onCreateView");
        view = inflater.inflate(R.layout.find_layout, container, false);

        init();
        if(GlobleMeth.hasInternet(getActivity())){
        new initPic().execute(htmlpic);
        new initArticle().execute(html_art);
        }
        else{
            GlobleMeth.showToast(getActivity(),"请检查网络");
        }
        search = (SearchBox) view.findViewById(R.id.searchbox);
        search.enableVoiceRecognition(this);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        GlobleAtrr.a.setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                openSearch();
                return true;
            }
        });

       // msp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),20, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
       // date.setText(msp);
        return view;
    }

    private void init() {
        pre_article= getActivity().getSharedPreferences("pre_article", Context.MODE_APPEND);
        commentV = View.inflate(getActivity(), R.layout.comment_material_dialog, null);
        commentMd = new MaterialDialog(getActivity()).setTitle("保存图片").setContentView(commentV).setPositiveButton("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {//substring(33)
                kjb.saveImage(getActivity(),pre_article.getString("pic_url", ""),GlobleAtrr.FilepathPic+pre_article.getString("pic_url", "").substring(33));
                  Toast.makeText(getActivity(), "保存成功至:"+GlobleAtrr.FilepathPic, Toast.LENGTH_SHORT).show();
                commentMd.dismiss();
            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getActivity(), "取消", Toast.LENGTH_SHORT).show();
                commentMd.dismiss();
            }
        });
        kjb=new KJBitmap();
        pic=(ImageView) view.findViewById(R.id.imageView_pic);
        img_love=(ImageButton)view.findViewById(R.id.mb_love);
        if (pre_article!=null&&!pre_article.getString("pic_url","").isEmpty())
        {
           kjb.display(pic,pre_article.getString("pic_url",""));
            pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commentMd.show();
                }
            });
        }
      //  mTextView = (TextView) view.findViewById(R.id.tv_article);
        date=(TextView)view.findViewById(R.id.tv_date);
        smallDate=(TextView)view.findViewById(R.id.date_small);
        name=(TextView)view.findViewById(R.id.zuozhe);
        article=(TextView)view.findViewById(R.id.tv_article);
        article.setMovementMethod(new ScrollingMovementMethod());
        if (pre_article!=null&&!pre_article.getString("article","").isEmpty())
        {
            article.setText(pre_article.getString("article",""));
        }
       // isCheck= getActivity().getSharedPreferences("isCheck", getActivity().MODE_APPEND);

        isCheck= getActivity().getSharedPreferences("isCheck", getActivity().MODE_APPEND);
           if(isCheck.getString("fg","").equals("1")){
                img_love.setBackgroundResource(R.drawable.like_1);
            }else if (isCheck.getString("fg","").equals("0")){
                img_love.setBackgroundResource(R.drawable.like_0);
             }
            else{
            SharedPreferences.Editor edit1 = isCheck.edit();
            edit1.putString("fg", "0");
            edit1.commit();}


//        if(isCheck.getString("fg","").equals("1")){
//            img_love.setBackgroundResource(R.drawable.like_1);
//        }
        img_love.setOnClickListener(new View.OnClickListener() {
           // boolean flag=false;
            @Override
            public void onClick(View v) {
                if(isCheck.getString("fg","").equals("0")){
                LoveBeen l=new LoveBeen();
                l.setS(article.getText().toString());
                GlobleAtrr.love_db.save(l);
                img_love.setBackgroundResource(R.drawable.like_1);
                    SharedPreferences.Editor edit1=isCheck.edit();
                    edit1.putString("fg","1");
                    edit1.commit();
                  // GlobleMeth.showToast(getActivity(),"收藏成功");
                GlobleAtrr.HeartFrag.loadMessage();}
                else{
                    GlobleAtrr.mListViewLove.addAll(GlobleAtrr.love_db.findAll(LoveBeen.class));
                    int positon=GlobleAtrr.mListViewLove.size()-1;
                    if(positon>=0){
                    GlobleAtrr.love_db.delete(GlobleAtrr.mListViewLove.get(positon));}
                    img_love.setBackgroundResource(R.drawable.like_0);
                    SharedPreferences.Editor edit1=isCheck.edit();
                    edit1.putString("fg","0");
                    edit1.commit();
                GlobleAtrr.HeartFrag.loadMessage();}
            }
        });
        Time time = new Time();
        time.setToNow();
        Integer year = time.year;
        Integer month = time.month+1;
        Integer day = time.monthDay;
        date.setText(day.toString());
        //GlobleMeth.showToast(getActivity(),"month:"+month.toString());
        smallDate.setText(year.toString() + "/" + month.toString());
    }


    public void openSearch() {
        search.revealFromMenuItem(R.id.action_search, getActivity());
        search.setLogoText("");
        search.setSearchListener(new SearchBox.SearchListener() {

            @Override
            public void onSearchOpened() {
                // Use this to tint the screen
                toolbar.setVisibility(View.GONE);
            }
            @Override
            public void onSearchClosed() {
                // Use this to un-tint the screen
                closeSearch();
                toolbar.setVisibility(View.VISIBLE);
            }
            @Override
            public void onSearchTermChanged() {

            }
            @Override
            public void onSearch(String searchTerm) {
                try {
                    String str = new String(searchTerm.trim().getBytes(), "utf-8");
                    html = GlobleAtrr.MAIN_URL + HTML1 + str + HTML2;
                    //String html0 = GlobleData.MAIN_URL + HTML3 + str + HTML2;
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), ResultActivity.class);
                    intent.putExtra("URL", html);
                    startActivity(intent);
                    Log.d("URL",html);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
              //  toolbar.setTitle(searchTerm);
            }

            @Override
            public void onSearchCleared() {

            }

        });

    }


    protected void closeSearch() {
        search.hideCircularly(getActivity());
        if(search.getSearchText().isEmpty())toolbar.setTitle("");
    }

    private class initPic extends
            AsyncTask<String, ListView, Map<String,String>> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
           // GlobleMeth.showToast(getActivity(),"ִ��");
            System.out.println("onPreExecute");
            super.onPreExecute();
        }
        @Override
        protected Map<String,String> doInBackground(String... params) {
            System.out.println("doInBackground");
            return JsoupUtil.getPic(params[0]);
        }
        @Override
        protected void onPostExecute(Map<String,String> result)
        {
            if(result.equals(pre_article.getString("pic_url",""))){
                name.setText(result.get("althor"));
            }
            else {
                kjb.display(pic, result.get("url"));
                pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commentMd.show();
                    }
                });
                SharedPreferences.Editor edit = pre_article.edit();
                edit.putString("pic_url", result.get("url"));
                edit.commit();
                name.setText(result.get("althor"));
            }
        }
    }



    private class initArticle extends
            AsyncTask<String, ListView,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {
            System.out.println("doInBackground");
            return JsoupUtil.getArt_url(params[0]);
        }
        @Override
        protected void onPostExecute(String result) {
        // GlobleMeth.showToast(getActivity(),result);
            new loadArticle().execute(result);
        }
        private class loadArticle extends AsyncTask<String, ListView,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected String doInBackground(String... params) {
                return JsoupUtil.loadArt(params[0]);
            }
            @Override
            protected void onPostExecute(String result) {
                if(result.equals(pre_article.getString("article","")))
                {
                }else{
                    result.trim();
                article.setText(result);
                    img_love.setBackgroundResource(R.drawable.like_0);
                    SharedPreferences.Editor edit1=isCheck.edit();
                    edit1.putString("fg","0");
                    edit1.commit();
                SharedPreferences.Editor edit = pre_article.edit();
                edit.putString("article",result);
                edit.commit();}
            }
        }
    }
}