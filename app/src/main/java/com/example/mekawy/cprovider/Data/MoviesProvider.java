package com.example.mekawy.cprovider.Data;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;


import com.example.mekawy.cprovider.Data.dbContract.MOST_VOTED_TABLE;
import com.example.mekawy.cprovider.Data.dbContract.POP_MOVIES_TABLE;
import com.example.mekawy.cprovider.Data.dbContract.FAV_MOVIES;

public class MoviesProvider extends ContentProvider{

    private static dbOpenhelper mhelper;

    private static final UriMatcher sUriMatcher=fill_matcher();

    private static final int POP_MOVIES=1;
    private static final int POP_MOVIES_WITH_ID=2;


    private static UriMatcher fill_matcher(){
        UriMatcher mMathcer=new UriMatcher(UriMatcher.NO_MATCH);
        String Authority=dbContract.CONTENT_AUTHORITY;
        mMathcer.addURI(Authority,dbContract.PATH_POP_MOVIES,POP_MOVIES);
        mMathcer.addURI(Authority,dbContract.PATH_POP_MOVIES+"/#",POP_MOVIES_WITH_ID);//Append number to contenturi
        return mMathcer;
    }


    @Override
    public boolean onCreate() {
        mhelper =new dbOpenhelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sort) {
        SQLiteDatabase db=mhelper.getReadableDatabase();
        int match_val=sUriMatcher.match(uri);
        Cursor ret_cursor = null;
        switch (match_val){

            case POP_MOVIES:{
                ret_cursor=db.query(POP_MOVIES_TABLE.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sort);
            }

            case POP_MOVIES_WITH_ID:{


                break;
            }
        }


        return ret_cursor;
    }

    @Override
    public String getType(Uri uri) {

        int match_value=sUriMatcher.match(uri);
        switch (match_value){
            case POP_MOVIES:return POP_MOVIES_TABLE.CONTENT_DIR_TYPE;
            case POP_MOVIES_WITH_ID:return POP_MOVIES_TABLE.CONTENT_ITEM_TYPE;
            default: throw new UnsupportedOperationException("unsupported type :"+uri);
        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db=mhelper.getWritableDatabase();
        int match_value=sUriMatcher.match(uri);
        Uri ret_uri;
        switch (match_value){

            case POP_MOVIES:{
            Long ret_val=db.insert(POP_MOVIES_TABLE.TABLE_NAME,null,contentValues);
                if(ret_val!=-1) ret_uri=POP_MOVIES_TABLE.buildMovieUri(ret_val);
                else throw new SQLException("Provider_insert_DB_NOT_VALID");
                break;
            }

            default: throw new UnsupportedOperationException("error,Uri not supported");
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return ret_uri;
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
