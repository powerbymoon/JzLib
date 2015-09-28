package com.example.administrator.jzlib.jzlib.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.jzlib.R;

/**
 * Created by Administrator on 2015/9/27.
 */
public class LoginSuccessProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.loginsuccesspersonlayout, container, false);
        return view;
    }
}
