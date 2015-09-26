package com.example.mekawy.cprovider.Data;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;


import com.example.mekawy.cprovider.Data.dbContract.POP_MOVIES_TABLE;

public class MoviesProvider extends ContentProvider{

    private static dbOpenhelper mhelper;
    private static final UriMatcher sUriMatcher=fill_matcher();
    private static SQLiteQueryBuilder sQueryBuilder;


    private static final int POP_MOVIES=1;
    private static final int POP_MOVIES_WITH_TAG =2;



    static {
        sQueryBuilder=new SQLiteQueryBuilder();
        sQueryBuilder.setTables(POP_MOVIES_TABLE.TABLE_NAME);
    }


    private static final String POP_MOVIE_SELECT_BY_TAG=
            POP_MOVIES_TABLE.TABLE_NAME+"."+POP_MOVIES_TABLE.OWM_COLUMN_TAG+ " = ? ";


    private static UriMatcher fill_matcher(){
        UriMatcher mMathcer=new UriMatcher(UriMatcher.NO_MATCH);
        String Authority=dbContract.CONTENT_AUTHORITY;
        mMathcer.addURI(Authority,dbContract.PATH_POP_MOVIES,POP_MOVIES);
        mMathcer.addURI(Authority,dbContract.PATH_POP_MOVIES+"/#", POP_MOVIES_WITH_TAG);//Append number to contenturi
        return mMathcer;
    }


    @Override
    public boolean onCreate() {
        mhelper =new dbOpenhelper(getContext());
        return true;
    }



    private Cursor get_Movie_by_TAG(Uri uri,String[] projection,String sort_order){
        SQLiteDatabase db=mhelper.getReadableDatabase();
        String Movie_TAG=uri.getPathSegments().get(1);

        String selection =POP_MOVIE_SELECT_BY_TAG;
        String SelectionArgs[]=new String[]{Movie_TAG};

        Cursor mCur=sQueryBuilder.query(
                db,
                projection,
                selection,
                SelectionArgs,
                null,
                null,
                sort_order);
    return mCur;
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
                break;
            }

            case POP_MOVIES_WITH_TAG:{
                ret_cursor=get_Movie_by_TAG(uri, projection,sort);
                break;
            }

            default:throw  new UnsupportedOperationException("unsupported Query "+uri);
        }
            ret_cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return ret_cursor;
    }

    @Override
    public String getType(Uri uri) {

        int match_value=sUriMatcher.match(uri);
        switch (match_value){
            case POP_MOVIES:return POP_MOVIES_TABLE.CONTENT_DIR_TYPE;
            case POP_MOVIES_WITH_TAG:return POP_MOVIES_TABLE.CONTENT_ITEM_TYPE;
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
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db=mhelper.getWritableDatabase();
        int match_val=sUriMatcher.match(uri);
        int ret_val;

        if(selection==null)
            selection="1"; //return the number of rows deleted in case of null selection

        switch (match_val){

            case POP_MOVIES:{
                ret_val=db.delete(POP_MOVIES_TABLE.TABLE_NAME, selection, selectionArgs);
                break;
            }

            default:throw new UnsupportedOperationException("unsupported delete command : "+uri);
        }
        //notify only in case of rows deleted
        if(ret_val!=0)
            getContext().getContentResolver().notifyChange(uri,null);
        return ret_val;
    }


    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        SQLiteDatabase db=mhelper.getWritableDatabase();
        int match_val=sUriMatcher.match(uri);
        int ret_val;

        switch (match_val){

            case POP_MOVIES:{
                ret_val=db.update(POP_MOVIES_TABLE.TABLE_NAME,contentValues,selection,selectionArgs);
                break;
            }

            default:throw new UnsupportedOperationException("unsupported update command : "+uri);
        }
        //notify only in case of rows deleted
        if(ret_val!=0)
            getContext().getContentResolver().notifyChange(uri,null);
        return ret_val;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {

        SQLiteDatabase db=mhelper.getWritableDatabase();
        int match_val=sUriMatcher.match(uri);
        int ret_val;
        ret_val=0;

        switch (match_val){

            case POP_MOVIES:{
                    db.beginTransaction();
                    for(ContentValues value:values){
                        long ret=db.insert(POP_MOVIES_TABLE.TABLE_NAME,null,value);
                        if(ret!=-1)ret_val++;
                    }
                    db.setTransactionSuccessful();
                    db.endTransaction();
                    getContext().getContentResolver().notifyChange(uri,null);
                    return ret_val;
            }
            default :throw new UnsupportedOperationException("unsupported bulk insertion, "+uri);
        }
    }

}
