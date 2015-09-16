package com.example.administrator.jzlib.jzlib.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;
import com.quinny898.library.persistentsearch.SearchBox;

/**
 * Created by Administrator on 2015/9/15.
 */
public class FindFragment extends Fragment {
    private SearchBox search;
    private Toolbar toolbar;
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
        return view;
    }



    public void openSearch() {
        search.revealFromMenuItem(R.id.action_search, getActivity());
        search.setLogoText("");
		/*for (int x = 0; x < 10; x++) {
			SearchResult option = new SearchResult("Result "
					+ Integer.toString(x), getResources().getDrawable(
					R.drawable.ic_history));
			search.addSearchable(option);
		}*/
		/*search.setMenuListener(new MenuListener() {

			@Override
			public void onMenuClick() {
				// Hamburger has been clicked
				Toast.makeText(RevealActivity.this, "Menu click",
						Toast.LENGTH_LONG).show();
			}

		});*/

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
                Toast.makeText(getActivity(), searchTerm + " Searched",
                        Toast.LENGTH_LONG).show();
                toolbar.setTitle(searchTerm);

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