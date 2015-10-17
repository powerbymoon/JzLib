package com.example.administrator.jzlib.jzlib.Fragment;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dd.CircularProgressButton;
import com.example.administrator.jzlib.jzlib.Dialog.GoogleNowStockCardwithList;
import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;

import it.gmariotti.cardslib.library.view.CardView;
import it.gmariotti.cardslib.library.view.CardViewNative;


public class MyInfoFragment extends Fragment {
    View view;
    CircularProgressButton zhuxiao;
   public GoogleNowStockCardwithList card2;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_my_info, container, false);
        zhuxiao=(CircularProgressButton)view.findViewById(R.id.zhuxiao_btn);
        zhuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhuxiao.setProgress(50);
                zhuxiao.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        GlobleAtrr.isLogin = false;
                        GlobleAtrr.p.finish();
                    }
                }, 1200);
                zhuxiao.setProgress(100);

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCard();

    }
    public void initCard() {
        //Card

        card2 = new GoogleNowStockCardwithList(getActivity());
        card2.init();

        //Set card in the cardView
        CardViewNative cardView2 = (CardViewNative) getActivity().findViewById(R.id.carddemo_stockcard);
        cardView2.setCard(card2);

    }

}


