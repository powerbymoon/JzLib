package com.example.administrator.jzlib.jzlib.Dialog;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.jzlib.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * Created by Administrator on 2015/9/30.
 */
public enum PersonalDemo
{
    CUSTOM_TAB_ICONS1(R.string.demo_title_custom_tab_icons1, R.layout.demo_custom_tab_icons1) {
@Override
public int[] tabs() {
        return new int[]{
        R.string.demo_tab_no_title,
        R.string.demo_tab_no_title,
        };
        }
@Override
public void setup(SmartTabLayout layout) {
        super.setup(layout);
final LayoutInflater inflater = LayoutInflater.from(layout.getContext());
final Resources res = layout.getContext().getResources();
        layout.setCustomTabView(new SmartTabLayout.TabProvider() {
@Override
public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
        ImageView icon = (ImageView) inflater.inflate(R.layout.custom_tab_icon1, container, false);
        switch (position) {
        case 0:
        icon.setImageDrawable(res.getDrawable(R.drawable.ic_assignment_ind_black_24dp));
        break;
        case 1:
        icon.setImageDrawable(res.getDrawable(R.drawable.ic_my_library_books_black_24dp));
        break;
           /* case 2:
              icon.setImageDrawable(res.getDrawable(R.drawable.ic_person_white_24dp));
              break;
            case 3:
              icon.setImageDrawable(res.getDrawable(R.drawable.ic_flash_on_white_24dp));
              break;*/
default:
        throw new IllegalStateException("Invalid position: " + position);
        }
        return icon;
        }
        });
        }
        };
public final int titleResId;
public final int layoutResId;
    PersonalDemo(int titleResId, int layoutResId) {
        this.titleResId = titleResId;
        this.layoutResId = layoutResId;
        }
public static int[] tab10() {
        return new int[] {
        };
        }
public void startActivity(Context context) {
        // DemoActivity.startActivity(context, this);
        }
public void setup(final SmartTabLayout layout) {
        //Do nothing.
        }
public int[] tabs() {
        return tab10();
        }

        }

