package com.example.administrator.jzlib.jzlib.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.jzlib.jzlib.Fragment.CollectionFragment;
import com.example.administrator.jzlib.jzlib.Fragment.FindFragment;

/**
 * Created by wuyexiong on 4/25/15.
 */
public class TestFragmentAdapter extends FragmentPagerAdapter {

    protected static final String[] CONTENT = new String[] { "find", "collection", "personal" };
    private int mCount = CONTENT.length;

    public TestFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        String s=(CONTENT[position % CONTENT.length]);
        if(s.equals("find")){
            return new FindFragment();
        }
        else if(s.equals("collection"))
        {
            return new CollectionFragment();
        }
        return new Fragment();
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
