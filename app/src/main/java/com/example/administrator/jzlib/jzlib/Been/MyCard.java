package com.example.administrator.jzlib.jzlib.Been;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Administrator on 2015/9/16.
 */
public class MyCard extends Card {
    public MyCard(Context context) {
        super(context);
    }

    public MyCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }
    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String BookName;

    public String getBookDetail() {
        return BookDetail;
    }

    public void setBookDetail(String bookDetail) {
        BookDetail = bookDetail;
    }

    public  String BookDetail;
    public String getBookNum() {
        return BookNum;
    }

    public void setBookNum(String bookNum) {
        BookNum = bookNum;
    }

    public String BookNum;

    public String getBookAthor() {
        return BookAthor;
    }

    public void setBookAthor(String bookAthor) {
        BookAthor = bookAthor;
    }

    public String BookAthor;

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String Icon;

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        final ImageButton iconIV= (ImageButton) parent.findViewById(R.id.messgae_iv_star);
        TextView bookNameTV= (TextView) parent.findViewById(R.id.message_tv_bookname);
        TextView bookCallnoTV= (TextView) parent.findViewById(R.id.message_tv_Callno);
        TextView bookAthorTV= (TextView) parent.findViewById(R.id.message_tv_Anthor);

        // iconIV.setImageResource(getIcon());
        bookNameTV.setText(getBookName());
        bookCallnoTV.setText(getBookNum());
        bookAthorTV.setText(getBookAthor());
        iconIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookBeen Collect = new BookBeen();
                Collect.setBookDetail(getBookDetail());
                Collect.setBookName(getBookName());
                Collect.setBookNum(getBookNum());
                Collect.setBookAthor(getBookAthor());
                GlobleAtrr.db.save(Collect);//这句出错
                //iconIV.setImageResource(R.drawable.ic_star_yellow_600_36dp);
                Toast.makeText(mContext, "收藏成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
