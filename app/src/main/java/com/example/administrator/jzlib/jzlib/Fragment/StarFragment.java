package com.example.administrator.jzlib.jzlib.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.Activity.BookDetailActivity;
import com.example.administrator.jzlib.jzlib.Activity.MainActivity;
import com.example.administrator.jzlib.jzlib.Been.BookBeen;
import com.example.administrator.jzlib.jzlib.Been.MyCard;
import com.example.administrator.jzlib.jzlib.Been.StarCard;
import com.example.administrator.jzlib.jzlib.Dialog.Progress;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;
import com.example.administrator.jzlib.jzlib.Util.JsoupUtil;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by Administrator on 2015/9/24.
 */
public class StarFragment  extends Fragment {
    private CardListView starCardLv;
    public Progress pDialog;
    String url0="http://opac.jluzh.com/opac/";
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
        loadMessage();
        //loadMessage();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.starfragement_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GlobleAtrr.StarFrag=this;
        starCardLv = (CardListView) view.findViewById(R.id.starFragment_cardlv);
        //loadMessage();
    }

    public void loadMessage() {
        GlobleAtrr.mListViewData.clear();
        GlobleAtrr.mListViewData.addAll(GlobleAtrr.db.findAll(BookBeen.class));
        CardArrayAdapter cardArrayAdapter = new MessCardArrayAdapter(GlobleAtrr.a, initCards());
        if ( starCardLv != null) {
            starCardLv.setAdapter(cardArrayAdapter);
        }
    }

    private ArrayList<Card> initCards() {
        ArrayList<Card> cards = new ArrayList<>();
        if (GlobleAtrr.mListViewData.size() > 0) {
            for (int i = GlobleAtrr.mListViewData.size()-1; i >= 0; i--) {
                cards.add(makecard(GlobleAtrr.mListViewData.get(i).getBookName(),
                        GlobleAtrr.mListViewData.get(i).getBookNum(),GlobleAtrr.mListViewData.get(i).getBookAthor(),GlobleAtrr.mListViewData.get(i).getBookDetail(),i));
            }
        }
        return cards;
    }

    private Card makecard(String Title, String Callno, String Publisher ,String detail,int i) {
        StarCard newCard = new StarCard(getActivity(), R.layout.star_item);
        newCard.setBookName(Title);
        newCard.setBookNum(Callno);
        newCard.setBookAthor(Publisher);
        newCard.setBookDetail(detail);
        newCard.setPosition(i);
        return newCard;
    }

    private class MessCardArrayAdapter extends CardArrayAdapter {
        public MessCardArrayAdapter(MainActivity messageActivity, ArrayList<Card> cards) {
            super(messageActivity, cards);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            //View item= View.inflate(getApplicationContext(),R.layout.card_comment,null);

            Card card = (Card) this.getItem(position);

           // ImageView star=(ImageView)item.findViewById(R.id.messgae_iv_star);
           /*star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),"3", Toast.LENGTH_SHORT).show();
                }
            });*/
           card.setOnClickListener(new Card.OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    String t= GlobleAtrr.mListViewData.get(position).getBookDetail();
                   // String url=url0+t;
                    new Detail().execute(t);
                }
            });

            return super.getView(position, convertView, parent);
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
                Toast.makeText(getActivity(), "抱歉！本书没有详细信息。",
                        Toast.LENGTH_SHORT).show();
            } else {
                Intent intent=new Intent();
                intent.setClass(getActivity(),BookDetailActivity.class);
                intent.putExtra("URL",result);
                startActivity(intent);
                super.onPostExecute(result);}
        }
    }

    private void initProgress() {
        pDialog=new Progress(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
    }
}


