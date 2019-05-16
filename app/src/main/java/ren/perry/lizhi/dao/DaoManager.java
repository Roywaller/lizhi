package ren.perry.lizhi.dao;

import android.content.Context;

import ren.perry.lizhi.app.Constants;

/**
 * Created by xiaoyehai on 2018/8/10 0010.
 */

public class DaoManager {

    private Context mContext;

    //创建数据库的名字
    private static final String DB_NAME = Constants.App.appId + "_db";

    //多线程中要被共享的使用volatile关键字修饰  GreenDao管理类
    private volatile static DaoManager mInstance;

    //它里边实际上是保存数据库的对象
    private static DaoMaster mDaoMaster;

    //创建数据库的工具
    private static MyDexOpenHelper mHelper;

    //管理gen里生成的所有的Dao对象里边带有基本的增删改查的方法
    private static DaoSession mDaoSession;


    private DaoManager() {
    }

    /**
     * 单例模式获得操作数据库对象
     */
    public static DaoManager get() {
        if (mInstance == null) {
            synchronized (DaoManager.class) {
                if (mInstance == null) {
                    mInstance = new DaoManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化上下文创建数据库的时候使用
     */
    public void init(Context context) {
        this.mContext = context;
    }

    /**
     * 判断是否有存在数据库，如果没有则创建
     */
    public DaoMaster getDaoMaster() {
        if (mDaoMaster == null) {
            mHelper = new MyDexOpenHelper(mContext, DB_NAME, null);
            mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
            //数据库加密
//            mDaoMaster = new DaoMaster(mHelper.getEncryptedWritableDb("thin_android"));
        }
        return mDaoMaster;
    }

    /**
     * 完成对数据库的添加、删除、修改、查询操作，
     */
    public DaoSession getDaoSession() {
        if (mDaoSession == null) {
            if (mDaoMaster == null) {
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }

    /**
     * 关闭所有的操作，数据库开启后，使用完毕要关闭
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }

    public void closeDaoSession() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }
}
