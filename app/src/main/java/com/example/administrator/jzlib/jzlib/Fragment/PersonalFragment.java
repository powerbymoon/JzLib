package com.example.administrator.jzlib.jzlib.Fragment;


import android.content.Context;
import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.Activity.LoginActivity;

import com.example.administrator.jzlib.jzlib.Activity.NativeListColorActivity;
import com.example.administrator.jzlib.jzlib.Activity.PersonalActivity;
import com.example.administrator.jzlib.jzlib.Been.CustomCard;
import com.example.administrator.jzlib.jzlib.Been.Personal_Card;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;
import com.example.administrator.jzlib.jzlib.Util.FragmentUtils;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.zip.Inflater;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.internal.ViewToClickToExpand;
import it.gmariotti.cardslib.library.view.CardView;
import it.gmariotti.cardslib.library.view.CardView;


public class PersonalFragment extends Fragment {

    ScrollView mScrollView;
    View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root= inflater.inflate(R.layout.fragment_personal, container, false);
        mScrollView = (ScrollView) root.findViewById(R.id.card_scrollview);TextView tv=(TextView)root.findViewById(R.id.activity_tv_title);
        tv.setText("更多");

        initCards();
        return root;
    }

   /* @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCards();
    }*/

    private void initCards() {
        init_standard_header_with_expandcollapse_button();
        init_custom_card_expand();

        init_custom_card_expand_clicking_image();
        init_custom_card_expand_inside();
        init_custom_card_expand_programmatic();
    }


    /**
     * This method builds a standard header with base expand/collapse
     */
    private void init_standard_header_with_expandcollapse_button() {

        //Create a Card
        Personal_Card card0 = new Personal_Card(GlobleAtrr.a,R.layout.personal_card_layout);
        card0.setName("个人信息");
        Drawable dra=getResources().getDrawable(R.drawable.ic_account_circle_blue_200_48dp);
        card0.setDraw(dra);
        CardView cardView = (CardView) root.findViewById(R.id.personal_card);
        cardView.setCard(card0);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GlobleAtrr.isLogin)
                {
                    Intent intent = new Intent();
                    intent.setClass(GlobleAtrr.a,PersonalActivity.class);
                    startActivity(intent);
                }
             else {
                    Intent intent = new Intent();
                    intent.setClass(GlobleAtrr.a, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * This method builds a custom card with expand/collapse action clickable in all card view
     */
    private void init_custom_card_expand() {
        //Create a Card
        Personal_Card card1 = new Personal_Card(GlobleAtrr.a,R.layout.personal_card_layout);
        card1.setName("分类浏览");
        Drawable dra=getResources().getDrawable(R.drawable.ic_dashboard_green_300_48dp);
        card1.setDraw(dra);
        CardView cardView = (CardView) root.findViewById(R.id.classfy_card);
        cardView.setCard(card1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(GlobleAtrr.a,NativeListColorActivity.class);
                startActivity(intent);
            }
        });
    }



    /**
     * This method builds a custom card with expand/collapse action clickable in all card view
     */

    /**
     * This method builds a custom card with expand/collapse action clickable in all card view
     */
    private void init_custom_card_expand_clicking_image() {
        //Create a Card
        Personal_Card card1 = new Personal_Card(GlobleAtrr.a,R.layout.personal_card_layout);
        card1.setName("管藏分布");
        Drawable dra=getResources().getDrawable(R.drawable.ic_view_quilt_brown_100_48dp);
        card1.setDraw(dra);
        CardExpand expand = new CardExpand(GlobleAtrr.a);
        //Set inner title in Expand Area;
        expand.setTitle(
                "一楼：" + " 总借还台、咖啡厅、书目检索区、编目部、自习区\n" + " \n" +
                        "二楼:" + " B区外文和港台图书阅览区、" + "馆长室、贵宾室、馆办公室、C区理科图书阅览区\n" + " \n" +
                        "三楼:" + " 文科图书阅览区（中文图书A——H类）\n" + " \n" +
                        "四楼:" + " 文科图书阅览区（中文图书I——K类）、副馆长室\n" + " \n" +
                        "五楼:" + " 电子文献阅览区\n" + " \n" +
                        "六楼:" + " 期刊和报纸阅览区" );
        card1.addCardExpand(expand);
        CardView cardView = (CardView) root.findViewById(R.id.buju);
        ViewToClickToExpand viewToClickToExpand =
                ViewToClickToExpand.builder()
                        .setupView(cardView);
        card1.setViewToClickToExpand(viewToClickToExpand);
        cardView.setCard(card1);
    }

    /**
     * This method builds a card with a collpse/expand section inside
     */
    private void init_custom_card_expand_inside() {
        Personal_Card card1 = new Personal_Card(GlobleAtrr.a,R.layout.personal_card_layout);
        card1.setName("开馆时间");
        Drawable dra=getResources().getDrawable(R.drawable.ic_access_time_teal_300_48dp);
        card1.setDraw(dra);
        CardExpand expand = new CardExpand(GlobleAtrr.a);
        expand.setTitle("周一至周日:\n" +
                        "一层自习区 " + " 07:30-22:30\n" +"\n"+
                "周一至周六:\n" +
                "一层总借还台 " + " 09:30-19:30\n" +
                "三层阅览区 " + " 08:30-22:00\n" +
                "四层阅览区 " + " 09:30-21:30\n" +"\n"+

                "周一至周五、周日:\n" +
                "二层阅览区 " + " 08:30-22:00\n" +
                "五层阅览区 " + " 09:30-21:30\n" +
                "六层阅览区 " + " 09:30-21:30"
               );
        card1.addCardExpand(expand);
        CardView cardView = (CardView)root.findViewById(R.id.gonggao);
        ViewToClickToExpand viewToClickToExpand =
                ViewToClickToExpand.builder()
                        .setupView(cardView);
        card1.setViewToClickToExpand(viewToClickToExpand);
        cardView.setCard(card1);
    }

    private void init_custom_card_expand_programmatic() {
        Personal_Card card1 = new Personal_Card(GlobleAtrr.a,R.layout.personal_card_layout);
        card1.setName("关于");
        Drawable dra=getResources().getDrawable(R.drawable.ic_info_deep_purple_200_48dp);
        card1.setDraw(dra);
        CardExpand expand = new CardExpand(GlobleAtrr.a);
        expand.setTitle("这是一款图书馆助手App，希望给喜欢借阅图书的同学带来方便。\n" +"如有任何摄影作品或者图书心得，欢迎投稿至：jzlib@sina.cn\n"+
                "如有任何意见或建议，欢迎致邮：iamlinman@sina.cn\n"+"作者：Moon"
        +"");
        card1.addCardExpand(expand);
        CardView cardView = (CardView)root.findViewById(R.id.about);
        ViewToClickToExpand viewToClickToExpand =
                ViewToClickToExpand.builder()
                        .setupView(cardView);
        card1.setViewToClickToExpand(viewToClickToExpand);
        cardView.setCard(card1);
    }


    class CustomCard2 extends Card{

        public CustomCard2(Context context) {
            super(context,R.layout.carddemo_example_cardexpand_inner_content);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {

            if (view != null) {
                TextView mTitleView = (TextView) view.findViewById(it.gmariotti.cardslib.library.R.id.card_main_inner_simple_title);
                if (mTitleView != null){
                    mTitleView.setText(mTitle);

                    ViewToClickToExpand viewToClickToExpand =
                            ViewToClickToExpand.builder()
                                    .setupView(mTitleView);
                    setViewToClickToExpand(viewToClickToExpand);
                }
            }
        }
    }


    class CustomThumbnail extends CardThumbnail {

        public CustomThumbnail(Context context) {
            super(context);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View imageView) {

            ViewToClickToExpand viewToClickToExpand =
                    ViewToClickToExpand.builder()
                            .highlightView(false)
                            .setupView(imageView);
            getParentCard().setViewToClickToExpand(viewToClickToExpand);
        }
    }

    class CardExpandInside extends CardExpand {

        public CardExpandInside(Context context) {
            super(context,R.layout.carddemo_example_expandinside_expand_layout);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {

            ImageView img = (ImageView) view.findViewById(R.id.carddemo_inside_image);

            //It is just an example. You should load your images in an async way
            if (img!=null){
                img.setImageResource(R.drawable.ic_star_yellow_600_48dp);
            }
        }

    }
}

