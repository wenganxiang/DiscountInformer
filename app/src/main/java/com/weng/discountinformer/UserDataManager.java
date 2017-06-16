package com.weng.discountinformer;
//用户数据管理类
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.weng.discountinformer.user.User;

/**
 * Created by Weng Anxiang on 2017/3/11.
 */

public class UserDataManager {
    private static final String TAG = "UserDataManager";
    private static final String DB_NAME = "user_data";
    private static final String TABLE_NAME = "users";
    private static final String ID = "_id";
    private static final String USER_NAME = "user_name";
    private static final String USER_PWD = "user_pwd";
    private static final int DB_VERSION = 1;
    private int dbVersion;  //数据库版本
    private Context mContext = null;

    //param of User:  userID, userName, userPassword, userAddress,
    private static final String DB_CREATE = "creat table " + TABLE_NAME + "("
            + ID + " integer primary key,"
            + USER_NAME + " varchar,"
            + USER_PWD + " varchar" +");";
    private SQLiteDatabase mSQLiteDatabase = null;
    private DatabaseManagementHelper mDatabaseHelper = null;

    private class DatabaseManagementHelper extends SQLiteOpenHelper
    {
        DatabaseManagementHelper(Context context)   //参考第二行代码P212
        {
            super(context, DB_NAME, null, dbVersion);
        }

        @Override
        //建表
        public void onCreate(SQLiteDatabase db) {
            Log.i(TAG, "db.getVersion()=" + db.getVersion());
            db.execSQL("drop table if exist " + TABLE_NAME + ";");
            db.execSQL(DB_CREATE);
            Log.i(TAG, "db.execSQL(DB_CREATE)");
            Log.e(TAG, DB_CREATE);
        }

        @Override
        //更新表
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exist" + TABLE_NAME);
            dbVersion += 1;
            db.setVersion(dbVersion);
            onCreate(db);
        }
    }
    public UserDataManager(Context context)
    {
        mContext = context;
        dbVersion = 1;
    }
    //打开数据库
    public void openDatabase()  throws SQLException
    {
        mDatabaseHelper = new DatabaseManagementHelper(mContext);
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
    }
    //关闭数据库
    public void closeDatabase() throws SQLException
    {
        mDatabaseHelper.close();
    }

    //添加新用户
    public long insertUserData(User user)
    {
        String userId = user.getUserID();
        String userPassword = user.getPassword();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userId);
        values.put(USER_PWD, userPassword);
        return mSQLiteDatabase.insert(TABLE_NAME, ID, values);
    }

    //根据用户名找用户，在注册时可以判断用户名是否已经存在
    public int findUserByName(String name)
    {
        Log.d(TAG, "findUserByName, userName=" + name);
        int result = 0;
        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME + "=" + name,
                null, null, null, null);
        if (cursor != null)
        {
            result = cursor.getCount();
            cursor.close();
            Log.d(TAG, "findUserByName, result=" + result);
        }
        return result;      //返回1则说明用户已经存在
    }
    //根据用户名和密码找用户，用于登录
    public int findUserByNameAndPassword(String name, String pwd)
    {
        Log.d(TAG, "findUserByNameAndPassword");
        int result = 0;
        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME + "=" + "and" + USER_PWD + "=" +pwd,
                null, null, null, null);
        if (cursor != null)
        {
            result = cursor.getCount();
            cursor.close();
            Log.d(TAG, "findUserByName, result=" + result);
        }
        return result;//返回1则说明用户存在
    }

    public static boolean isExisted(String s)
    {
        return true;
    }

}
