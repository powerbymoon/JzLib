package com.example.administrator.jzlib.jzlib.Activity;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.Been.BookBeen;
import com.example.administrator.jzlib.jzlib.Been.MyCard;
import com.example.administrator.jzlib.jzlib.Dialog.Progress;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;
import com.example.administrator.jzlib.jzlib.Util.JsoupUtil;
import com.quinny898.library.persistentsearch.SearchBox;
import com.victor.loading.newton.NewtonCradleLoading;

import net.tsz.afinal.FinalActivity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;

import cn.pedant.SweetAlert.SweetAlertDialog;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.ViewToClickToExpand;
import it.gmariotti.cardslib.library.view.CardListView;
import it.gmariotti.cardslib.library.view.CardViewNative;

/**
 * Created by Administrator on 2015/9/16.
 */
public class ResultActivity extends ActionBarActivity{
    String url0="http://opac.jluzh.com/opac/";
    final String HTML1 = "?strSearchType=title&match_flag=forward&historyCount=1&strText=";
    final String HTML2 = "&doctype=ALL&displaypg=20&showmode=list&sort=CATA_DATE&orderby=desc&dept=ALL";
    private ProgressDialog mypDialog;
    private String html;
    private TextView sumNumber;
    private TextView pageNumber;
    private ImageView nextButton;
    private ImageView preButton;
    private CardListView messageCardLv;
    private SearchBox search;
    private Toolbar toolbar;
    public Progress pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        search = (SearchBox) findViewById(R.id.searchbox);
        search.enableVoiceRecognition(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ResultActivity.this.setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                openSearch();
                return true;
            }
        });
        initUI();
        initProgress();
        Intent intent = this.getIntent();
        html = intent.getStringExtra("URL");
        new LoadBookInfo().execute(html);
        JsoupUtil.clearInfo();
    }

    private void initProgress() {
        pDialog=new Progress(this,SweetAlertDialog.PROGRESS_TYPE);
    }

    private void initUI() {
        messageCardLv = (CardListView) findViewById(R.id.message_cardlv_message);
        // 总页数
        sumNumber = (TextView) findViewById(R.id.sum_number);
        pageNumber = (TextView) findViewById(R.id.page_number);
        // 上一页、下一页按钮
        nextButton = (ImageView) findViewById(R.id.next);
        preButton = (ImageView) findViewById(R.id.pre);
        preButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (JsoupUtil.page <= 1) {
                    Toast.makeText(getApplicationContext(), "已经是第一页了！",
                            Toast.LENGTH_SHORT).show();
                } else {
                    new LoadBookInfo().execute(JsoupUtil.preUrl);
                    JsoupUtil.page--;
                }
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (JsoupUtil.page >= Math.ceil(JsoupUtil.sumNumber / 20)) {
                    Toast.makeText(getApplicationContext(), "已经是最后一页了！",
                            Toast.LENGTH_SHORT).show();
                } else {
                    new LoadBookInfo().execute(JsoupUtil.nextUrl);
                    JsoupUtil.page++;
                }
            }
        });
    }

    public void openSearch() {
        search.revealFromMenuItem(R.id.action_search,this);
        search.setLogoText("");
        search.setSearchListener(new SearchBox.SearchListener() {
            @Override
            public void onSearchOpened() {
                // Use this to tint the screen
            }
            @Override
            public void onSearchClosed() {
                // Use this to un-tint the screen
                closeSearch();
            }
            @Override
            public void onSearchTermChanged() {

            }
            @Override
            public void onSearch(String searchTerm) {
                //  toolbar.setTitle(searchTerm);
                String str = null;
                try {
                    str = new String(searchTerm.trim().getBytes(), "ISO-8859-1");
                    html = GlobleAtrr.MAIN_URL + HTML1 + str + HTML2;
                    new LoadBookInfo().execute(html);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onSearchCleared() {
            }
        });
    }
   protected void closeSearch() {
        search.hideCircularly(this);
        if(search.getSearchText().isEmpty())toolbar.setTitle("");
    }
    private class LoadBookInfo extends
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
            return JsoupUtil.searchBook(params[0]);
        }
        @Override
        protected void onPostExecute(List<Map<String, Object>> result) {
            // TODO Auto-generated method stub
            // 显示总数、页码及图书列表
           pDialog.cancel();
            sumNumber.setText(JsoupUtil.sumNumber.toString());
            pageNumber.setText(JsoupUtil.pageNumber);
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
            CardArrayAdapter cardArrayAdapter = new MessCardArrayAdapter(ResultActivity.this, initCards());
            if (messageCardLv != null) {
                messageCardLv.setAdapter(cardArrayAdapter);
            }
        }
        private ArrayList<Card> initCards() {
            ArrayList<Card> cards = new ArrayList<>();
            if (GlobleAtrr.mData.size() > 0) {
                for (int i = 0; i < GlobleAtrr.mData.size(); i++) {
                    String detail=url0+GlobleAtrr.mData.get(i).get("bookDetail").toString();
                  //  new Detail().execute(pic0);
                    String s0 = GlobleAtrr.mData.get(i).get("bookTitle").toString();
                    String[] ss0=s0.split("\\.");
                    s0=ss0[1];
                    if (s0.length() > 12) {
                        s0 = s0.substring(0, 12) + "...";
                    }
                    String s = GlobleAtrr.mData.get(i).get("bookCallno").toString();
                    String[] ss1 = s.split(" ");
                    s = ss1[ss1.length-1];
                    String s2 = GlobleAtrr.mData.get(0).get("bookPublisher").toString();
                    String[] ss2 = s2.split("著 ");
                    s2 = ss2[0];
                    if (s2.length() >19) {
                        s2= s2.substring(0,19) + "...";
                    }
                    cards.add(makecard(s0,
                            s, s2,detail));
                }
            }
            return cards;
        }
        private Card makecard(String Title, String Callno, String Publisher ,String detail) {
            MyCard newCard = new MyCard(ResultActivity.this, R.layout.message_item);
            newCard.setBookName(Title);
            newCard.setBookNum(Callno);
            newCard.setBookAthor(Publisher);
            newCard.setBookDetail(detail);
            return newCard;
        }
        private class MessCardArrayAdapter extends CardArrayAdapter {
            public MessCardArrayAdapter(ResultActivity messageActivity, ArrayList<Card> cards) {
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
