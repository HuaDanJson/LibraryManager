package com.example.jason.examination.utils.db;

import android.content.Context;

import com.aidebar.greendaotest.gen.BookDao;
import com.aidebar.greendaotest.gen.DaoManager;
import com.example.jason.examination.data.Book;

import java.util.List;

/**
 * Created by jason on 2018/3/22.
 */

public class DBBookListUtils {

    private BookDao dbCurrentUserDao;
    private static DBBookListUtils dbCurrentUserUtils = null;

    public DBBookListUtils(Context context) {
        dbCurrentUserDao = DaoManager.getInstance(context).getNewSession().getBookDao();
    }

    public static DBBookListUtils getInstance() {
        return dbCurrentUserUtils;
    }

    public static void Init(Context context) {
        if (dbCurrentUserUtils == null) {
            dbCurrentUserUtils = new DBBookListUtils(context);
        }
    }

    /**
     * 完成对数据库中插入一条数据操作
     *
     * @param
     * @return
     */
    public void insertOneData(Book dbUserInvestment) {
        dbCurrentUserDao.insertOrReplace(dbUserInvestment);
    }

    /**
     * 完成对数据库中插入多条数据操作
     *
     * @param dbUserInvestmentList
     * @return
     */
    public boolean insertManyData(List<Book> dbUserInvestmentList) {
        boolean flag = false;
        try {
            dbCurrentUserDao.insertOrReplaceInTx(dbUserInvestmentList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中删除一条数据操作
     *
     * @param dbUserInvestment
     * @return
     */
    public boolean deleteOneData(Book dbUserInvestment) {
        boolean flag = false;
        try {
            dbCurrentUserDao.delete(dbUserInvestment);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中删除一条数据 ByKey操作
     *
     * @return
     */
    public boolean deleteOneDataByKey(long id) {
        boolean flag = false;
        try {
            dbCurrentUserDao.deleteByKey(id);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中批量删除数据操作
     *
     * @return
     */
    public boolean deleteManData(List<Book> dbUserInvestmentList) {
        boolean flag = false;
        try {
            dbCurrentUserDao.deleteInTx(dbUserInvestmentList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中数据全部删除
     *
     * @return
     */
    public boolean deleteAll() {
        boolean flag = false;
        try {
            dbCurrentUserDao.deleteAll();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库更新数据操作
     *
     * @return
     */
    public boolean updateData(Book dbUserInvestment) {
        boolean flag = false;
        try {
            dbCurrentUserDao.update(dbUserInvestment);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库批量更新数据操作
     *
     * @return
     */
    public boolean updateManData(List<Book> dbUserInvestmentList) {
        boolean flag = false;
        try {
            dbCurrentUserDao.updateInTx(dbUserInvestmentList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库查询数据操作
     *
     * @return
     */
    public Book queryOneData(long id) {
        return dbCurrentUserDao.load(id);
    }

    /**
     * 完成对数据库查询所有数据操作
     *
     * @return
     */
    public List<Book> queryAllData() {
        return dbCurrentUserDao.loadAll();
    }

    /**
     * 完成对数据库条件查询数据操作 bookName
     *
     * @return
     */
    public List<Book> queryUserDependBookName(String bookName) {
        return dbCurrentUserDao.queryBuilder().where(BookDao.Properties.BookName.like("%" + bookName + "%")).build().list();
    }

    /**
     * 完成对数据库条件查询数据操作 bookWriter
     *
     * @return
     */
    public List<Book> queryUserDependBookWriter(String bookWriter) {
        return dbCurrentUserDao.queryBuilder().where(BookDao.Properties.BookWriter.like("%" + bookWriter + "%")).build().list();
    }


    /**
     * 完成对数据库条件查询数据操作 classification
     *
     * @return
     */
    public List<Book> queryUserDependlassification(String classification) {
        return dbCurrentUserDao.queryBuilder().where(BookDao.Properties.Classification.eq(classification)).build().list();
    }

    /**
     * 完成对数据库条件查询数据操作 isRead
     *
     * @return
     */
    public List<Book> queryUserDependIsRead(boolean isRead) {
        return dbCurrentUserDao.queryBuilder().where(BookDao.Properties.IsReadBefore.eq(isRead)).build().list();
    }
}
