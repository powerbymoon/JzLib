package com.example.administrator.jzlib.jzlib.Been;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.Fragment.StarFragment;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Administrator on 2015/9/24.
 */
public class StarCard extends Card {
    public StarCard(Context context) {
        super(context);
    }

    public StarCard(Context context, int innerLayout) {
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int position;

    @Override
    public void setupInnerViewElements(ViewGroup parent, final View view) {

        final ImageView iconIV= (ImageView) parent.findViewById(R.id.messgae_iv_star);
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

                BookBeen collection =  GlobleAtrr.mListViewData.get(getPosition());
                GlobleAtrr.db.delete(collection);//这句出错
                //iconIV.setImageResource(R.drawable.ic_star_outline_grey_400_36dp);
                try {
                    Thread.sleep(400);
                    GlobleAtrr.StarFrag.loadMessage();
                    Toast.makeText(GlobleAtrr.a,"已删除", Toast.LENGTH_SHORT).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Toast.makeText(GlobleAtrr.a,"删除", Toast.LENGTH_SHORT).show();

            }
       });
    }

}
