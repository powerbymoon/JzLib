package com.example.administrator.jzlib.jzlib.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.Been.CustomCard;
import com.example.administrator.jzlib.jzlib.Been.MyCard;

import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.ViewToClickToExpand;
import it.gmariotti.cardslib.library.view.CardViewNative;

/**
 * Created by Administrator on 2015/9/18.
 */
public class CardExpandActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        initCards();
    }

    private void initCards() {
        init_custom_card_expand();

    }

    private void init_custom_card_expand() {
        //Create a Card
       MyCard card = new MyCard(CardExpandActivity.this,R.layout.message_item);
       // CustomCard card1 = new CustomCard(getApplication());
        //This provides a simple (and useless) expand area
        CardExpand expand = new CardExpand(getApplication());
       // CardExpand expand1 = new CardExpand(getApplication());
        //Set inner title in Expand Area
        expand.setTitle("lalalal");
       // expand1.setTitle("xxxxxx");
        card.addCardExpand(expand);

        //Set card in the cardView
        CardViewNative cardView = (CardViewNative) findViewById(R.id.carddemo_example_card_expand2);
        ViewToClickToExpand viewToClickToExpand =
                ViewToClickToExpand.builder()
                        .setupView(cardView);
        card.setViewToClickToExpand(viewToClickToExpand);
        cardView.setCard(card);

        //cardView.setCard(card1);


    }
}
