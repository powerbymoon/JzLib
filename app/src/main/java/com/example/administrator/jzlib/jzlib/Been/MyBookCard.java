package com.example.administrator.jzlib.jzlib.Been;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Administrator on 2015/10/2.
 */
public class MyBookCard extends Card {
    public MyBookCard(Context context) {
        super(context);
    }
    public MyBookCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    String bookName;

    public String getBookBorrowTime() {
        return bookBorrowTime;
    }

    public void setBookBorrowTime(String bookBorrowTime) {
        this.bookBorrowTime = bookBorrowTime;
    }

    public String getBookPayTime() {
        return bookPayTime;
    }

    public void setBookPayTime(String bookPayTime) {
        this.bookPayTime = bookPayTime;
    }

    String bookBorrowTime;
    String bookPayTime;

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //final ImageButton iconIV= (ImageButton) parent.findViewById(R.id.messgae_iv_star);
        TextView bookNameTV= (TextView) parent.findViewById(R.id.bookTitleTV);
        TextView bookBorrowTV= (TextView) parent.findViewById(R.id.borrowedDateTV);
        TextView bookPayTV= (TextView) parent.findViewById(R.id.paybackDateTV);
        bookNameTV.setText(getBookName());
        bookBorrowTV.setText(getBookBorrowTime());
        bookPayTV.setText(getBookPayTime());
    }
}
