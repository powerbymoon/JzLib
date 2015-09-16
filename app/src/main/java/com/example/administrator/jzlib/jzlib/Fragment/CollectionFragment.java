package com.example.administrator.jzlib.jzlib.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.jzlib.R;

/**
 * Created by Administrator on 2015/9/16.
 */
public class CollectionFragment extends Fragment {
    TextView t;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collection_layout, container, false);
        t=(TextView)view.findViewById(R.id.textView2);
        Button button=(Button)view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.setText("haha");
            }
        });
        return view;
    }

}
