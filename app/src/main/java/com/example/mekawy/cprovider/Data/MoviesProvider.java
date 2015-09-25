package com.example.mekawy.cprovider.Data;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;



import com.example.mekawy.cprovider.Data.dbContract.MOST_VOTED_TABLE;
import com.example.mekawy.cprovider.Data.dbContract.POP_MOVIES_TABLE;
import com.example.mekawy.cprovider.Data.dbContract.FAV_MOVIES;

public class MoviesProvider extends ContentProvider{

    private static dbOpenhelper mhelper;

    private static final UriMatcher mUriMatcher=fill_matcher();

    private static final int POP_MOVIES_ALL=1;
    private static final int POP_MOVIES_WITH_ID=2;


    private static UriMatcher fill_matcher(){
        UriMatcher mMathcer=new UriMatcher(UriMatcher.NO_MATCH);
        String Authority=dbContract.CONTENT_AUTHORITY;
        mMathcer.addURI(Authority,dbContract.PATH_POP_MOVIES,POP_MOVIES_ALL);
        mMathcer.addURI(Authority,dbContract.PATH_POP_MOVIES+"/#",POP_MOVIES_WITH_ID);
        return mMathcer;
    }


    @Override
    public boolean onCreate() {
        mhelper =new dbOpenhelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }


}
