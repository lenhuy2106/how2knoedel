package de.nle.how2knoedel.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import de.nle.how2knoedel.R;

/**
 * Created by T500 on 03.11.2014.
 */
public class RecipeDatabaseHelper extends SQLiteOpenHelper{

    private Context context;

    public RecipeDatabaseHelper(Context context){
        super(
                context,
                context.getResources().getString(R.string.dbname),
                null,
                Integer.parseInt(context.getResources().getString(R.string.version)));
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(String sql : context.getResources().getStringArray(R.array.create))
            db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
