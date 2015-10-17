package com.example.administrator.jzlib.jzlib.Activity;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.Dialog.Demo;
import com.example.administrator.jzlib.jzlib.Dialog.PersonalDemo;
import com.example.administrator.jzlib.jzlib.Fragment.HeartFragment;
import com.example.administrator.jzlib.jzlib.Fragment.MyInfoFragment;
import com.example.administrator.jzlib.jzlib.Fragment.MybookFragment;
import com.example.administrator.jzlib.jzlib.Fragment.StarFragment;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class PersonalActivity extends AppCompatActivity implements MybookFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_layout);
        GlobleAtrr.p=this;
        PersonalDemo demo = PersonalDemo.values()[0];
        TextView tv=(TextView)findViewById(R.id.activity_tv_title);
        tv.setText("Hello");
        // GlobleAtrr.a.setSupportActionBar(toolbar);
        // GlobleAtrr.a.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ViewGroup tab = (ViewGroup)findViewById(R.id.tab);
        tab.addView(LayoutInflater.from(this).inflate(demo.layoutResId, tab, false));
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        SmartTabLayout viewPagerTab = (SmartTabLayout)findViewById(R.id.viewpagertab);
        demo.setup(viewPagerTab);
        FragmentPagerItems pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of("0",MyInfoFragment.class));
        pages.add(FragmentPagerItem.of("1",MybookFragment.class));
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                this.getSupportFragmentManager(), pages);
        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);
    }

}
