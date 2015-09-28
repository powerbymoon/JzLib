package com.example.administrator.jzlib.jzlib.Been;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.administrator.jzlib.R;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Administrator on 2015/9/18.
 */
public class CustomCard extends Card {

    protected TextView mTitle;
    protected TextView mSecondaryTitle;

    /**
     * Constructor with a custom inner layout
     *
     * @param context
     */
    public CustomCard(Context context) {
        this(context, R.layout.carddemo_mycard_inner_content);
    }

    /**
     * @param context
     * @param innerLayout
     */
    public CustomCard(Context context, int innerLayout) {
        super(context, innerLayout);
        init();
    }

    private void init() {

    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        mTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_title);
        mSecondaryTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_secondaryTitle);



        if (mTitle != null)
            mTitle.setText("hallow");

        if (mSecondaryTitle != null)
            mSecondaryTitle.setText("点我");


    }
}

