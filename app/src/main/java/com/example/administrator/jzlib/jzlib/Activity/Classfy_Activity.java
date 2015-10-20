package com.example.administrator.jzlib.jzlib.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.Been.MyCard;
import com.example.administrator.jzlib.jzlib.Dialog.Progress;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;
import com.example.administrator.jzlib.jzlib.Util.JsoupUtil;
import com.quinny898.library.persistentsearch.SearchBox;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

public class Classfy_Activity extends AppCompatActivity {
    String url0="http://opac.jluzh.com/opac/";

    private String html;
    private CardListView messageCardLv;
    public Progress pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classfy_);
        initUI();
        initProgress();
        Intent intent = this.getIntent();
        html = intent.getStringExtra("url");
        new LoadBookInfo_classify().execute(html);
        JsoupUtil.clearInfo();
    }

    private void initProgress() {
        pDialog=new Progress(this, SweetAlertDialog.PROGRESS_TYPE);
    }

    private void initUI() {
        messageCardLv = (CardListView) findViewById(R.id.message_cardlv_message);
        // 总页数
    }

    private class LoadBookInfo_classify extends
            AsyncTask<String, ListView, List<Map<String, Object>>> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            System.out.println("onPreExecute");
            //mypDialog.show();
            initProgress();
            pDialog.show();
            // newtonCradleLoading.start();
            super.onPreExecute();
        }
        @Override
        protected List<Map<String, Object>> doInBackground(String... params) {
            // TODO Auto-generated method stub
            return JsoupUtil.searchBook_clasify(params[0]);
        }
        @Override
        protected void onPostExecute(List<Map<String, Object>> result) {
            // TODO Auto-generated method stub
            // 显示总数、页码及图书列表
            pDialog.cancel();

            // newtonCradleLoading.destroyDrawingCache();
            if (result == null) {
                finish();
                Toast.makeText(getApplicationContext(), "本馆没有您检索的纸本馆藏书目",
                        Toast.LENGTH_SHORT).show();
            } else {
                // sumNumber.setText(JsoupUtil.sumNumber.toString());
                // pageNumber.setText(JsoupUtil.pageNumber);
                GlobleAtrr.mData = result;
                loadMessage();
            }
        }
        private void loadMessage() {
            CardArrayAdapter cardArrayAdapter = new MessCardArrayAdapter(Classfy_Activity.this, initCards());
            if (messageCardLv != null) {
                messageCardLv.setAdapter(cardArrayAdapter);
            }
        }
        private ArrayList<Card> initCards() {
            ArrayList<Card> cards = new ArrayList<>();
            if (GlobleAtrr.mData.size() > 0) {
                for (int i = 0; i < GlobleAtrr.mData.size(); i++) {
                    String detail=url0+GlobleAtrr.mData.get(i).get("bookDetail").toString();
                    String s0 = GlobleAtrr.mData.get(i).get("bookTitle").toString();
                    String s = GlobleAtrr.mData.get(i).get("bookCallno").toString();
                    String s2 = GlobleAtrr.mData.get(i).get("bookAuthor").toString();
                    cards.add(makecard(s0,
                            s, s2,detail));
                }
            }
            return cards;
        }
        private Card makecard(String Title, String Callno, String Publisher ,String detail) {
            MyCard newCard = new MyCard(Classfy_Activity.this, R.layout.message_item);
            newCard.setBookName(Title);
            newCard.setBookNum(Callno);
            newCard.setBookAthor(Publisher);
            newCard.setBookDetail(detail);
            return newCard;
        }
        private class MessCardArrayAdapter extends CardArrayAdapter {
            public MessCardArrayAdapter(Classfy_Activity messageActivity, ArrayList<Card> cards) {
                super(messageActivity, cards);
            }
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                //View item= View.inflate(getApplicationContext(),R.layout.card_comment,null);
                // LayoutInflater layoutInflater=null;
                Card card = (Card) this.getItem(position);
                card.setOnClickListener(new Card.OnCardClickListener() {
                    @Override
                    public void onClick(Card card, View view) {
                        String t=(String)GlobleAtrr.mData.get(position).get("bookDetail");
                        String url=url0+t;
                        new Detail().execute(url);
                        //  Log.d("注意啦2",GlobleAtrr.mData.get(position).get("bookDetail").toString());
                    }
                });

                return super.getView(position, convertView, parent);
            }


        }
    }
    private class Detail extends
            AsyncTask<String, ListView, String> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            System.out.println("onPreExecute");
            initProgress();
            pDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            System.out.println("doInBackground");
            return JsoupUtil.detail(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-genera
            pDialog.cancel();
            // String s=result.substring(0,27);
            //Log.d("sub",s);
            if (result.equals("http://book.douban.com/isbn//")) {
                Toast.makeText(getApplicationContext(), "抱歉！本书没有详细信息。",
                        Toast.LENGTH_SHORT).show();
            } else {
                Intent intent=new Intent();
                intent.setClass(getApplication(),BookDetailActivity.class);
                intent.putExtra("URL",result);
                startActivity(intent);
                super.onPostExecute(result);}
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
