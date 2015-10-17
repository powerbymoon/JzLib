package com.example.administrator.jzlib.jzlib.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.jzlib.jzlib.Fragment.CollectionFragment;
import com.example.administrator.jzlib.jzlib.Fragment.FindFragment;
import com.example.administrator.jzlib.jzlib.Fragment.LoginSuccessProfileFragment;
import com.example.administrator.jzlib.jzlib.Fragment.PersonalFragment;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleMeth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyexiong on 4/25/15.
 */
public class TestFragmentAdapter extends FragmentPagerAdapter {

    protected static final String[] CONTENT = new String[] {"find","collection","personal"};
    private int mCount = CONTENT.length;
    List<Fragment> fragmentList = new ArrayList<Fragment>();
    public TestFragmentAdapter(FragmentManager fm, List<Fragment> mFragmentList) {
        super(fm);
        this.fragmentList=mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
       // String s=(CONTENT[position]);
        return fragmentList.get(position);
       /*switch (position){
           // case -1:return new PersonalFragment();
            case 0:return new FindFragment();
            case 1:return new CollectionFragment();
            case 2: return new PersonalFragment();
        }
        return new FindFragment();*/
       /* if(s.equals("find")){
            return new FindFragment();
           // return new FindFragment();
        }
        else if(s.equals("collection"))
        {
            return new CollectionFragment();
        }
        else if(s.equals("personal"))
        {
            GlobleMeth.showToast(GlobleAtrr.a,s);
            return new PersonalFragment();
        }
        else return new PersonalFragment();*/
    }

    @Override
    public int getCount() {
        return mCount;
    }



    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}
