package com.example.administrator.jzlib.jzlib.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.Activity.MainActivity;
import com.example.administrator.jzlib.jzlib.Been.BookBeen;
import com.example.administrator.jzlib.jzlib.Been.HartCard;
import com.example.administrator.jzlib.jzlib.Been.LoveBeen;
import com.example.administrator.jzlib.jzlib.Been.StarCard;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by Administrator on 2015/9/24.
 */
public class HeartFragment  extends Fragment {
    private CardListView starCardLv;
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
        GlobleAtrr.HeartFrag=this;
        starCardLv = (CardListView) view.findViewById(R.id.starFragment_cardlv);
}
    public void loadMessage() {
        GlobleAtrr.mListViewLove.clear();
        GlobleAtrr.mListViewLove.addAll(GlobleAtrr.love_db.findAll(LoveBeen.class));
        CardArrayAdapter cardArrayAdapter = new MessCardArrayAdapter(GlobleAtrr.a, initCards());
        if ( starCardLv != null) {
            starCardLv.setAdapter(cardArrayAdapter);
        }
    }

    private ArrayList<Card> initCards() {
        ArrayList<Card> cards = new ArrayList<>();
        if (GlobleAtrr.mListViewLove.size() > 0) {
            for (int i = GlobleAtrr.mListViewLove.size()-1; i >=0; i--) {
                cards.add(makecard(GlobleAtrr.mListViewLove.get(i).getS(),i));
            }
        }
        return cards;
    }

    private Card makecard(String article,int i) {
        HartCard newCard = new HartCard(getActivity(), R.layout.heartitemlayout);
        newCard.setArt(article);
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


            return super.getView(position, convertView, parent);
        }


    }


}
