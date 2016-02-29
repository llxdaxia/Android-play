package cn.hotwoo.play.book;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by llxal on 2015/12/8.
 */
public class BookContentProvider extends ContentProvider {

    public static final String CONTENT_URI = "BOOK_URI";
    private ContentResolver resolver;
    private ContentValues values;
    private UriMatcher matcher;

    private static final int FUCK = 1;

    public BookContentProvider(){
        values = new ContentValues();
        matcher = new UriMatcher(UriMatcher.NO_MATCH); //实例化一个matcher
        matcher.addURI("cn.hotwoo.play.book.BookContentProvider","/fuck",FUCK);

    }


    @Override
    public boolean onCreate() {
        return resolver != null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (matcher.match(uri)){
            case FUCK:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override  //向contentprovider中添加新数据
    public Uri insert(Uri uri, ContentValues values) {
        return resolver.insert(uri,values);  //传入contentprovider中的列的名字，和对应的contentvalues,返回新的uri
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

}
