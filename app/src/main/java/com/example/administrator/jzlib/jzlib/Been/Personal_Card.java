package com.example.administrator.jzlib.jzlib.Been;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
 * Created by Administrator on 2015/9/28.
 */
public class Personal_Card  extends Card {
    public Personal_Card(Context context) {
        super(context);
    }
    public Personal_Card(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public String name;

    public Drawable getDraw() {
        return draw;
    }

    public void setDraw(Drawable draw) {
        this.draw = draw;
    }

    public Drawable draw;

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        TextView Name = (TextView) parent.findViewById(R.id.personal_name);
        ImageView pic0=(ImageView) parent.findViewById(R.id.presonal_pic);
        Name.setText(getName());
        pic0.setImageDrawable(getDraw());
    }
}