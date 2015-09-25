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


        Uri d= POP_MOVIES_TABLE.CONTENT_URI.buildUpon().appendPath("51330").build();


        String projection_col[]={
                POP_MOVIES_TABLE._ID,
                POP_MOVIES_TABLE.OWM_COLUMN_TAG,
                POP_MOVIES_TABLE.OWM_COLUMN_TITLE,
                POP_MOVIES_TABLE.OWM_COLUMN_OVERVIEW,
                POP_MOVIES_TABLE.OWM_COLUMN_RELEASE_DATE,
                POP_MOVIES_TABLE.OWM_COLUMN_VOTE_AVERAGE,
                POP_MOVIES_TABLE.OWM_COLUMN_IS_FAVORITE
        };

        Cursor c=getContentResolver().query(d, projection_col, null, null, null);

      if(c.moveToFirst()) {//move cursor to fisrt row
            do {
                Log.i("Cursor TAG", "TAG " + Integer.toString(c.getInt(c.getColumnIndex(POP_MOVIES_TABLE.OWM_COLUMN_TAG))));
            } while (c.moveToNext());
        }

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
