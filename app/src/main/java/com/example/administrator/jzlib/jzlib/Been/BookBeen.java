package com.example.administrator.jzlib.jzlib.Been;

import net.tsz.afinal.annotation.sqlite.Id;

/**
 * Created by Administrator on 2015/9/16.
 */

public class BookBeen {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id(column="myId")
    private int id;
    public String BookDetail,BookName,BookNum,BookAthor;
    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookDetail() {
        return BookDetail;
    }

    public void setBookDetail(String bookDetail) {
        BookDetail = bookDetail;
    }

    public String getBookNum() {
        return BookNum;
    }

    public void setBookNum(String bookNum) {
        BookNum = bookNum;
    }

    public String getBookAthor() {
        return BookAthor;
    }

    public void setBookAthor(String bookAthor) {
        BookAthor = bookAthor;
    }


}
