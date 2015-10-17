/*
 * ******************************************************************************
 *   Copyright (c) 2013-2014 Gabriele Mariotti.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *  *****************************************************************************
 */

package com.example.administrator.jzlib.jzlib.Dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.Been.StudentInfo;

import java.util.ArrayList;
import java.util.List;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.prototypes.CardWithList;
import it.gmariotti.cardslib.library.prototypes.LinearListView;

/**
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 */
public class GoogleNowStockCardwithList extends CardWithList {

    public GoogleNowStockCardwithList(Context context) {
        super(context);
    }

    @Override
    protected CardHeader initCardHeader() {

        //Add Header
        CardHeader header = new CardHeader(getContext(), R.layout.carddemo_googlenowstock_withlist_inner_header) {
            @Override
            public void setupInnerViewElements(ViewGroup parent, View view) {
                super.setupInnerViewElements(parent, view);
               // TextView subTitle = (TextView) view.findViewById(R.id.carddemo_googlenow_main_inner_lastupdate);
              //  if (subTitle != null) {
                 //   subTitle.setText("04120902");  //Should use strings.xml
               // }
            }
        };
        header.setTitle(StudentInfo.name); //should use strings.xml
        return header;
    }

    @Override
    protected void initCard() {

        //Set the whole card as swipeable
     //   setSwipeable(false);
     //   setOnSwipeListener(new OnSwipeListener() {
          //  @Override
         //   public void onSwipe(Card card) {
       //         Toast.makeText(getContext(), "Swipe on " + card.getCardHeader().getTitle(), Toast.LENGTH_SHORT).show();
        //    }
    //    });

    }


    @Override
    protected List<ListObject> initChildren() {

        //Init the list
        List<ListObject> mObjects = new ArrayList<ListObject>();

        //Add an object to the list
        StockObject s1 = new StockObject(this);
        s1.code = "读者类型：";
      //  s1.value = "本科";
        s1.deltaPerc = StudentInfo.education;
     ///  s1.deltaPerc = -1.27f;
        mObjects.add(s1);

        StockObject s2 = new StockObject(this);
        s2.code = "过期图书数:";

        s2.deltaPerc = StudentInfo.expired;
        mObjects.add(s2);

        StockObject s3 = new StockObject(this);
        s3.code = "5天内过期图书数：";
       // s3.value = "0";
        s3.deltaPerc = StudentInfo.toExpire;
        mObjects.add(s3);

        StockObject s4 = new StockObject(this);
        s4.code = "累计借书数：";
        s4.deltaPerc = StudentInfo.sumBorrowed;
        mObjects.add(s4);

        StockObject s5 = new StockObject(this);
        s5.code = "最大可借图书数：";
        s5.deltaPerc= StudentInfo.maxBorrow;
        mObjects.add(s5);

        StockObject s6 = new StockObject(this);
        s6.code = "超期欠款：";
        s6.deltaPerc = StudentInfo.qiankuan;
        mObjects.add(s6);

        StockObject s7 = new StockObject(this);
        s7.code = "证件失效日期:";
        s7.deltaPerc= StudentInfo.expireData;
        mObjects.add(s7);

        return mObjects;
    }

    @Override
    public View setupChildView(int childPosition, ListObject object, View convertView, ViewGroup parent) {

        //Setup the ui elements inside the item
        TextView textViewCode = (TextView) convertView.findViewById(R.id.textViewCode);
        //TextView textViewValue = (TextView) convertView.findViewById(R.id.textViewValue);
        //TextView textViewDelta = (TextView) convertView.findViewById(R.id.textViewDelta);
        TextView textViewDeltaPerc = (TextView) convertView.findViewById(R.id.textViewPerc);

        //Retrieve the values from the object
        StockObject stockObject = (StockObject) object;
        textViewCode.setText(stockObject.code);
       // textViewValue.setText(""+stockObject.value);
       // textViewDelta.setText(""+stockObject.delta);
        textViewDeltaPerc.setText(""+stockObject.deltaPerc);
        textViewDeltaPerc.setTextColor(getContext().getResources().getColor(R.color.main_bottom_tab_textcolor_selected));
       /* if (stockObject.delta<0){
            textViewDelta.setTextColor(getContext().getResources().getColor(R.color.main_bottom_tab_textcolor_selected));
            textViewDeltaPerc.setTextColor(getContext().getResources().getColor(R.color.main_bottom_tab_textcolor_selected));
        }else{
            textViewDelta.setTextColor(getContext().getResources().getColor(R.color.main_bottom_tab_textcolor_selected));
            textViewDeltaPerc.setTextColor(getContext().getResources().getColor(R.color.main_bottom_tab_textcolor_selected));
        }*/

        return convertView;
    }

    @Override
    public int getChildLayoutId() {
        return R.layout.carddemo_googlenowstock_withlist_inner_main;
    }


    // -------------------------------------------------------------
    // Weather Object
    // -------------------------------------------------------------

    public class StockObject extends DefaultListObject {

        public String code;
        //public String value;
        //public String delta;
        public String deltaPerc;

        public StockObject(Card parentCard) {
            super(parentCard);
            init();
        }

        private void init() {
            //OnClick Listener
            setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(LinearListView parent, View view, int position, ListObject object) {
                    Toast.makeText(getContext(), "Click on " + getObjectId(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public String getObjectId() {
            return code;
        }
    }

}
