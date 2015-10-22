package com.example.administrator.jzlib.jzlib.Fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.Activity.PersonalActivity;
import com.example.administrator.jzlib.jzlib.Been.MyBookCard;
import com.example.administrator.jzlib.jzlib.Been.MyCard;
import com.example.administrator.jzlib.jzlib.Dialog.Progress;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;
import com.example.administrator.jzlib.jzlib.Util.JsoupUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;


public class MybookFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private CardListView messageCardLv;
    View view;
    TextView status;
    public Progress pDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // Toast.makeText(getActivity(), "lalalawowowowo", Toast.LENGTH_SHORT).show();
        view=inflater.inflate(R.layout.fragment_mybook, container, false);
        messageCardLv = (CardListView) view.findViewById(R.id.message_cardlv_message);
        status=(TextView)view.findViewById(R.id.status);
        initProgress();
        BorrowedBook bb = new BorrowedBook();
        bb.execute();
        return view;
    }
    private void initProgress() {
        pDialog=new Progress(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
    }

    public interface OnFragmentInteractionListener {
    }

    private class BorrowedBook  extends
            AsyncTask<String, Integer, List<Map<String, Object>>> {
        @Override
        protected List<Map<String, Object>> doInBackground(String... params) {
            // TODO Auto-generated method stub
            return JsoupUtil.getBorrowedBook();
        }
        @Override
        protected void onPostExecute(List<Map<String, Object>> result) {
            // TODO Auto-generated method stub
            System.out.println(result);
            //pDialog.cancel();
            if(result==null){
                status.setText("当前没有任何借阅");
            }
            else{
           // mypDialog.cancel();
            //for(int i,i<result.size(),i++)
          //  Log.d("DEBUG", "熊出没 注意" + e1);
                GlobleAtrr.result=result;
               // GlobleData.flag1=true;
               loadMessage();
                super.onPostExecute(result);
        }}

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
          //  mypDialog.show();
          //  pDialog.show();
            super.onPreExecute();
        }

    }

    private void loadMessage() {
        CardArrayAdapter cardArrayAdapter = new MessCardArrayAdapter(getActivity(), initCards());
        if (messageCardLv != null) {
            messageCardLv.setAdapter(cardArrayAdapter);
        }
    }

    private ArrayList<Card> initCards() {
        ArrayList<Card> cards = new ArrayList<>();
        if (GlobleAtrr.result.size() > 0) {
            for(int i=0;i<GlobleAtrr.result.size();i++){
                String s0=GlobleAtrr.result.get(i).get("booktitle").toString();
                String s1=GlobleAtrr.result.get(i).get("borrowedDate").toString();
                String s2=GlobleAtrr.result.get(i).get("paybackDate").toString();
                cards.add(makecard(s0,
                        s1, s2));
            }
        }
        return cards;
    }
    private Card makecard(String title, String borrowTime, String payTime) {
        MyBookCard newCard = new MyBookCard(getActivity(), R.layout.mybookitemlayout);
        newCard.setBookName(title);
        newCard.setBookBorrowTime(borrowTime);
        newCard.setBookPayTime(payTime);
        return newCard;
    }
    private class MessCardArrayAdapter extends CardArrayAdapter {
        public MessCardArrayAdapter(FragmentActivity messageActivity, ArrayList<Card> cards) {
            super(messageActivity, cards);
        }
    }
}




