package com.example.jason.examination.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import cn.bmob.v3.BmobObject;

/**
 * Created by jason on 2018/3/17.
 */
@Entity
public class Book extends BmobObject {

    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "Book")
    private String bookName;
    private String bookPress;
    private String bookIntroduce;
    private String bookWriter;
    private String bookValue;
    private String bookPublishingTime;
    private String bookCover;
    private String classification;//书的分类
    private boolean isReadBefore;//是否阅读过

    @Generated(hash = 1290088178)
    public Book(Long id, String bookName, String bookPress, String bookIntroduce,
                String bookWriter, String bookValue, String bookPublishingTime,
                String bookCover, String classification, boolean isReadBefore) {
        this.id = id;
        this.bookName = bookName;
        this.bookPress = bookPress;
        this.bookIntroduce = bookIntroduce;
        this.bookWriter = bookWriter;
        this.bookValue = bookValue;
        this.bookPublishingTime = bookPublishingTime;
        this.bookCover = bookCover;
        this.classification = classification;
        this.isReadBefore = isReadBefore;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPress() {
        return this.bookPress;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress;
    }

    public String getBookIntroduce() {
        return this.bookIntroduce;
    }

    public void setBookIntroduce(String bookIntroduce) {
        this.bookIntroduce = bookIntroduce;
    }

    public String getBookWriter() {
        return this.bookWriter;
    }

    public void setBookWriter(String bookWriter) {
        this.bookWriter = bookWriter;
    }

    public String getBookValue() {
        return this.bookValue;
    }

    public void setBookValue(String bookValue) {
        this.bookValue = bookValue;
    }

    public String getBookPublishingTime() {
        return this.bookPublishingTime;
    }

    public void setBookPublishingTime(String bookPublishingTime) {
        this.bookPublishingTime = bookPublishingTime;
    }

    public String getBookCover() {
        return this.bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getClassification() {
        return this.classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public boolean getIsReadBefore() {
        return this.isReadBefore;
    }

    public void setIsReadBefore(boolean isReadBefore) {
        this.isReadBefore = isReadBefore;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookPress='" + bookPress + '\'' +
                ", bookIntroduce='" + bookIntroduce + '\'' +
                ", bookWriter='" + bookWriter + '\'' +
                ", bookValue='" + bookValue + '\'' +
                ", bookPublishingTime='" + bookPublishingTime + '\'' +
                ", bookCover='" + bookCover + '\'' +
                ", classification='" + classification + '\'' +
                ", isReadBefore=" + isReadBefore +
                '}';
    }
}
