package com.kooknluke.abrewforyou.DB.sqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Dan Poss on 9/23/2017.
 */

public class SqlLiteDbHelper extends SQLiteOpenHelper {

    private static SqlLiteDbHelper sqlLiteDbHelper;

    private static String DB_PATH = "/data/data/com.kooknluke.abrewforyou/databases/";

    private static String DB_NAME = "montana_breweries.db";

    private SQLiteDatabase liteDatabase;

    private final Context theContext;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 4;

    public SqlLiteDbHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.theContext = context;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
    }

    public static SqlLiteDbHelper getInstance(Context context) {
        Log.d("DATABASE", "Inside SqlLiteDbHelper getInstance");
        if (sqlLiteDbHelper == null) {
            sqlLiteDbHelper = new SqlLiteDbHelper(context);
        }
        return sqlLiteDbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        boolean dbExist = checkDataBase();

        Log.d("DATABASE", "DBExists :: " + dbExist);
        if (!dbExist) {
            try {
                Log.d("DATABASE", "Need to update/create sqlliteDB");
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database :: " + e.toString());
            }
        }
    }

    private void copyDataBase() throws IOException {

            //Open your local db as the input stream
            InputStream myInput = theContext.getAssets().open(DB_NAME);

            // Path to the just created empty db
            String outFileName = DB_PATH + DB_NAME;

            Log.v("DATABASE", "Database path created : " + outFileName);

            //Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }

            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();

        }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

            Log.d("DATABASE", myPath);

            if (checkDB != null) {
                checkDB.close();
                return true;
            } else if (checkDB != null && checkDB.getVersion() == DATABASE_VERSION) {
                return true;
            }
            return false;
        }
        catch(SQLiteException e) {

            //database does't exist yet.

            return false;
        }
    }

}
