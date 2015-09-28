package com.example.administrator.jzlib.jzlib.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.Activity.CardExpandActivity;
import com.example.administrator.jzlib.jzlib.Dialog.Demo;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


/**
 * Created by Administrator on 2015/9/16.
 */
public class CollectionFragment extends Fragment {
   // private static final String KEY_DEMO = "demo";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collection_layout, container, false);
        Demo demo = Demo.values()[0];
TextView tv=(TextView)view.findViewById(R.id.activity_tv_title);
        tv.setText("我的收藏");
       // GlobleAtrr.a.setSupportActionBar(toolbar);
       // GlobleAtrr.a.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ViewGroup tab = (ViewGroup) view.findViewById(R.id.tab);
        tab.addView(LayoutInflater.from(getActivity()).inflate(demo.layoutResId, tab, false));
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        SmartTabLayout viewPagerTab = (SmartTabLayout) view.findViewById(R.id.viewpagertab);
        demo.setup(viewPagerTab);
        FragmentPagerItems pages = new FragmentPagerItems(getActivity());

            pages.add(FragmentPagerItem.of("0",StarFragment.class));
            pages.add(FragmentPagerItem.of("1",HeartFragment.class));
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                GlobleAtrr.a.getSupportFragmentManager(), pages);
        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);
        return view;

    }
}




