package com.example.jason.examination.data;

import cn.bmob.v3.BmobObject;

/**
 * Created by jason on 2018/3/17.
 */

public class BookList extends BmobObject {

    private String bookName;
    private String bookIntroduce;
    private String bookWriter;
    private String bookValue;
    private String bookCover;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookIntroduce() {
        return bookIntroduce;
    }

    public void setBookIntroduce(String bookIntroduce) {
        this.bookIntroduce = bookIntroduce;
    }

    public String getBookWriter() {
        return bookWriter;
    }

    public void setBookWriter(String bookWriter) {
        this.bookWriter = bookWriter;
    }

    public String getBookValue() {
        return bookValue;
    }

    public void setBookValue(String bookValue) {
        this.bookValue = bookValue;
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
                ", bookIntroduce='" + bookIntroduce + '\'' +
                ", bookWriter='" + bookWriter + '\'' +
                ", bookValue='" + bookValue + '\'' +
                ", bookCover='" + bookCover + '\'' +
                '}';
    }
}
