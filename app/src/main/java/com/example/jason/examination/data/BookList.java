package com.example.jason.examination.data;

import cn.bmob.v3.BmobObject;

/**
 * Created by jason on 2018/3/17.
 */

public class BookList extends BmobObject {

    private String bookName;
    private String BookIntroduce;
    private String BookWriter;
    private String BookValue;
    private String bookCover;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookIntroduce() {
        return BookIntroduce;
    }

    public void setBookIntroduce(String bookIntroduce) {
        BookIntroduce = bookIntroduce;
    }

    public String getBookWriter() {
        return BookWriter;
    }

    public void setBookWriter(String bookWriter) {
        BookWriter = bookWriter;
    }

    public String getBookValue() {
        return BookValue;
    }

    public void setBookValue(String bookValue) {
        BookValue = bookValue;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    @Override
    public String toString() {
        return "BookList{" +
                "bookName='" + bookName + '\'' +
                ", BookIntroduce='" + BookIntroduce + '\'' +
                ", BookWriter='" + BookWriter + '\'' +
                ", BookValue='" + BookValue + '\'' +
                ", bookCover='" + bookCover + '\'' +
                '}';
    }
}
