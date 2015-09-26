package com.example.mekawy.cprovider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mekawy.cprovider.Data.dbContract;
import com.example.mekawy.cprovider.Data.dbOpenhelper;
import com.example.mekawy.cprovider.Data.dbContract.POP_MOVIES_TABLE;

public class MainFragActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_frag);


        Uri d = POP_MOVIES_TABLE.CONTENT_URI;

        ContentValues []mContent=new ContentValues[2];

        mContent[0]=new ContentValues();
        mContent[1]=new ContentValues();

        mContent[0].put(POP_MOVIES_TABLE.OWM_COLUMN_TAG, 855154);
        mContent[0].put(POP_MOVIES_TABLE.OWM_COLUMN_TITLE, "movie name1");
        mContent[0].put(POP_MOVIES_TABLE.OWM_COLUMN_OVERVIEW, "overview1");
        mContent[0].put(POP_MOVIES_TABLE.OWM_COLUMN_RELEASE_DATE, "2015");
        mContent[0].put(POP_MOVIES_TABLE.OWM_COLUMN_POSTER_PATH, "poster path1");
        mContent[0].put(POP_MOVIES_TABLE.OWM_COLUMN_VOTE_AVERAGE, 21.5);
        mContent[0].put(POP_MOVIES_TABLE.OWM_COLUMN_IS_FAVORITE,1);// movie is in favorite list

        mContent[1].put(POP_MOVIES_TABLE.OWM_COLUMN_TAG, 84655);
        mContent[1].put(POP_MOVIES_TABLE.OWM_COLUMN_TITLE, "movie name2");
        mContent[1].put(POP_MOVIES_TABLE.OWM_COLUMN_OVERVIEW, "overview2");
        mContent[1].put(POP_MOVIES_TABLE.OWM_COLUMN_RELEASE_DATE, "2015");
        mContent[1].put(POP_MOVIES_TABLE.OWM_COLUMN_POSTER_PATH, "poster path2");
        mContent[1].put(POP_MOVIES_TABLE.OWM_COLUMN_VOTE_AVERAGE, 21.5);
        mContent[1].put(POP_MOVIES_TABLE.OWM_COLUMN_IS_FAVORITE,1);// movie is in favorite list


        int x=getContentResolver().bulkInsert(POP_MOVIES_TABLE.CONTENT_URI,mContent);
        Log.i("APPDEBUG",Integer.toString(x));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
