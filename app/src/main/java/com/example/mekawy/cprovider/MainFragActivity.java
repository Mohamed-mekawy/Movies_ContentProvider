package com.example.mekawy.cprovider;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
        test_db_tabels();
    }



    public void test_db_tabels(){
        dbOpenhelper mhelper=new dbOpenhelper(getApplicationContext());
        SQLiteDatabase db =mhelper.getWritableDatabase();
        Long value;

        //Content values
        ContentValues mContent=new ContentValues();

        mContent.put(POP_MOVIES_TABLE.OWM_COLUMN_TAG,561330);
        mContent.put(POP_MOVIES_TABLE.OWM_COLUMN_TITLE,"movie name");
        mContent.put(POP_MOVIES_TABLE.OWM_COLUMN_OVERVIEW,"overview");
        mContent.put(POP_MOVIES_TABLE.OWM_COLUMN_RELEASE_DATE,"2015");
        mContent.put(POP_MOVIES_TABLE.OWM_COLUMN_POSTER_PATH,"poster path");
        mContent.put(POP_MOVIES_TABLE.OWM_COLUMN_VOTE_AVERAGE,21.5);
        mContent.put(POP_MOVIES_TABLE.OWM_COLUMN_IS_FAVORITE,1);// movie is in favorite list

        value=db.insert(POP_MOVIES_TABLE.TABLE_NAME,null,mContent);
        Log.i("INSERT INTO POP", Long.toString(value));

        value=db.insert(dbContract.MOST_VOTED_TABLE.TABLE_NAME,null,mContent);
        Log.i("INSERT INTO VOTE",Long.toString(value));

        mContent.remove(POP_MOVIES_TABLE.OWM_COLUMN_IS_FAVORITE);

        value=db.insert(dbContract.FAV_MOVIES.TABLE_NAME,null,mContent);
        Log.i("INSERT INTO FAV", Long.toString(value));
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
