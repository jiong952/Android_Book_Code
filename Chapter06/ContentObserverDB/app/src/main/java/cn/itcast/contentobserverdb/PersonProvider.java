package cn.itcast.contentobserverdb;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonProvider extends ContentProvider {
    //定义一个uri路径的匹配器，如果路径匹配不成功返回-1
    private static UriMatcher mUriMatcher = new UriMatcher(-1);
    private static final int SUCCESS = 1; //匹配路径成功时的返回码
    private PersonDBOpenHelper helper;     //数据库操作类的对象
    //添加路径匹配器的规则
    static {
        mUriMatcher.addURI("cn.itcast.contentobserverdb", "info", SUCCESS);
    }
    @Override
    public boolean onCreate() { //当内容提供者被创建时调用
        helper = new PersonDBOpenHelper(getContext());
        return false;
    }
    /**
     * 查询数据操作
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        //匹配查询的Uri路径
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            SQLiteDatabase db = helper.getReadableDatabase();
            return db.query("info", projection, selection, selectionArgs,
                    null, null, sortOrder);
        } else {
            throw new IllegalArgumentException("路径不正确，无法查询数据！");
        }
    }
    /**
     * 添加数据操作
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            SQLiteDatabase db = helper.getReadableDatabase();
            long rowId = db.insert("info", null, values);
            if (rowId > 0) {
                Uri insertedUri = ContentUris.withAppendedId(uri, rowId);
                //提示数据库的内容变化了
                getContext().getContentResolver().notifyChange(insertedUri, null);
                return insertedUri;
            }
            db.close();
            return uri;
        } else {
            throw new IllegalArgumentException("路径不正确，无法插入数据！");
        }
    }
    /**
     * 删除数据操作
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            SQLiteDatabase db = helper.getWritableDatabase();
            int count = db.delete("info", selection, selectionArgs);
            //提示数据库的内容变化了
            if (count > 0) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            db.close();
            return count;
        } else {
            throw new IllegalArgumentException("路径不正确，无法随便删除数据!");
        }
    }
    /**
     * 更新数据操作
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            SQLiteDatabase db = helper.getWritableDatabase();
            int count = db.update("info", values, selection, selectionArgs);
            //提示数据库的内容变化了
            if (count > 0) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            db.close();
            return count;
        } else {
            throw new IllegalArgumentException("路径不正确，无法更新数据！");
        }
    }
    @Override
    public String getType(Uri uri) {
        return null;
    }
}
