package com.example.mekawy.cprovider;

import android.content.ContentValues;
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

        Uri t=POP_MOVIES_TABLE.CONTENT_URI;

        //Content values
        ContentValues mContent=new ContentValues();

        mContent.put(POP_MOVIES_TABLE.OWM_COLUMN_TAG,5613330);
        mContent.put(POP_MOVIES_TABLE.OWM_COLUMN_TITLE,"movie name");
        mContent.put(POP_MOVIES_TABLE.OWM_COLUMN_OVERVIEW,"overview");
        mContent.put(POP_MOVIES_TABLE.OWM_COLUMN_RELEASE_DATE,"2015");
        mContent.put(POP_MOVIES_TABLE.OWM_COLUMN_POSTER_PATH,"poster path");
        mContent.put(POP_MOVIES_TABLE.OWM_COLUMN_VOTE_AVERAGE,21.5);
        mContent.put(POP_MOVIES_TABLE.OWM_COLUMN_IS_FAVORITE,1);// movie is in favorite list

        Uri returned=getContentResolver().insert(t,mContent);
        Log.i("APPDEBUG",returned.toString());
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
