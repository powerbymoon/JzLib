package com.example.administrator.jzlib.jzlib.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.Activity.ResultActivity;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;
import com.quinny898.library.persistentsearch.SearchBox;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2015/9/15.
 */
public class FindFragment extends Fragment {
    private SearchBox search;
    private Toolbar toolbar;
    private String html;
    TextView mTextView = null;
    //SpannableString msp = null;
    TextView date=null;
    final String HTML1 = "?strSearchType=title&match_flag=forward&historyCount=1&strText=";
    final String HTML2 = "&doctype=ALL&displaypg=20&showmode=list&sort=CATA_DATE&orderby=desc&dept=ALL";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_layout, container, false);
        search = (SearchBox) view.findViewById(R.id.searchbox);
        search.enableVoiceRecognition(this);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        GlobleAtrr.a.setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                openSearch();
                return true;
            }
        });
        mTextView = (TextView) view.findViewById(R.id.tv_article);
        date=(TextView)view.findViewById(R.id.tv_date);

       // msp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),20, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
       // date.setText(msp);
        return view;
    }



    public void openSearch() {
        search.revealFromMenuItem(R.id.action_search, getActivity());
        search.setLogoText("");

        search.setSearchListener(new SearchBox.SearchListener() {

            @Override
            public void onSearchOpened() {
                // Use this to tint the screen

            }

            @Override
            public void onSearchClosed() {
                // Use this to un-tint the screen
                closeSearch();
            }

            @Override
            public void onSearchTermChanged() {

            }





            @Override
            public void onSearch(String searchTerm) {
                try {
                    String str = new String(searchTerm.trim().getBytes(), "ISO-8859-1");
                    html = GlobleAtrr.MAIN_URL + HTML1 + str + HTML2;
                    //String html0 = GlobleData.MAIN_URL + HTML3 + str + HTML2;
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), ResultActivity.class);
                    intent.putExtra("URL", html);
                    startActivity(intent);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
              //  toolbar.setTitle(searchTerm);
            }

            @Override
            public void onSearchCleared() {

            }

        });

    }


    protected void closeSearch() {
        search.hideCircularly(getActivity());
        if(search.getSearchText().isEmpty())toolbar.setTitle("");
    }

}