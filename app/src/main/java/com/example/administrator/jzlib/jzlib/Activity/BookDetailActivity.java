package com.example.administrator.jzlib.jzlib.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;
import com.example.administrator.jzlib.jzlib.Util.JsoupUtil;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.annotation.view.ViewInject;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/25.
 */
public class BookDetailActivity extends FinalActivity {

    private TextView jianjie;


    private TextView shuming;
    private FinalBitmap fb=null;
    private String html="";
    @ViewInject(id=R.id.imageView1)
    ImageView tupian; //无需findViewById
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdetail);
        fb  = FinalBitmap.create(this.getApplicationContext());
        //fb.configBitmapLoadThreadSize(3);
        //fb = new FinalBitmap(this).init().display(tupian, "url");
        shuming=(TextView)findViewById(R.id.shuming);
        jianjie=(TextView)findViewById(R.id.jianjietext);
        //tupain=(ImageView)findViewById(R.id.imageView1);
        Intent intent = this.getIntent();
        html = intent.getStringExtra("URL");
        new BookDetail().execute(html);

    }
    public class BookDetail extends
            AsyncTask<String,ListView, List<Map<String, Object>>> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            System.out.println("onPreExecute");

            super.onPreExecute();
        }

        @Override
        protected List<Map<String, Object>> doInBackground(String... params) {
            System.out.println("doInBackground");
            return JsoupUtil.bookdetail(params[0]);
        }

        @Override
        protected void onPostExecute(List<Map<String, Object>> result)
        {
            // TODO Auto-generated method stub
            if(GlobleAtrr.no){
            }
            else{
                shuming.setText(result.get(0).get("shuming").toString());
                jianjie.setText(result.get(0).get("jianjie").toString());
                jianjie.setMovementMethod(new ScrollingMovementMethod());
                String tupianurl=result.get(0).get("tupianurl").toString();
                fb.display(tupian, tupianurl);}

            // TODO 自动生成的方法存根

            //tupain.setImageBitmap(tupian);
            super.onPostExecute(result);
        }
    }


}
