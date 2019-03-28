package aditdnair.uplan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static aditdnair.uplan.Dashboard.dash;
import static android.content.ContentValues.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String tablename = LoginActivity.username;

    public static class FeedEntry implements BaseColumns {

        public static final String TABLE_NAME = "entry";
        public static final String COL_5 ="ID";
        public static final String COL_1 = "NAME";
        public static final String COL_2 = "AMOUNT";
        public static final String COL_3="DATE";
        public static final String COL_4="CATEGORY";
        public static final String TAG="DatabaseHelper";
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedData.db";

    public String SQL_CREATE_ENTRIES= "CREATE TABLE "+FeedEntry.TABLE_NAME+"("+FeedEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT,AMOUNT TEXT,DATE TEXT,CATEGORY TEXT)";

    public String SQL_DELETE_ENTRIES="DROP TABLE IF EXISTS "+FeedEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        //super(context, DATABASE_NAME, null, DATABASE_VERSION);
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void addData1(String name, String amount, String date, String category){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(FeedEntry.COL_1,name);
        contentValues.put(FeedEntry.COL_2,amount);
        contentValues.put(FeedEntry.COL_3,date);
        contentValues.put(FeedEntry.COL_4,category);

        Log.d(TAG, "add Data: Adding " + name + "to" + FeedEntry.TABLE_NAME);
        Log.d(TAG, "add Data: Adding " + amount + "to" + FeedEntry.TABLE_NAME);
        Log.d(TAG, "add Data: Adding " + date + "to" + FeedEntry.TABLE_NAME);
        Log.d(TAG, "add Data: Adding " + category + "to" + FeedEntry.TABLE_NAME);

        long newRowId=db.insert(FeedEntry.TABLE_NAME,null,contentValues);

    }
    public void deleteData(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(FeedEntry.TABLE_NAME,null,null);
    }
    /*public void readData(){
        SQLiteDatabase db=this.getReadableDatabase();
        String[] projection={BaseColumns._ID,FeedEntry.COL_1,FeedEntry.COL_2,FeedEntry.COL_3,FeedEntry.COL_4};

        String selection=FeedEntry.COL_1+" =?";
        String[] selectionArgs={"Name"};
    }*/

    public void deleteData1(String name,String amount,String date,String category){
        SQLiteDatabase db=this.getWritableDatabase();
        String[] selectionArgs={name,amount,date,category};
        db.delete(FeedEntry.TABLE_NAME,"NAME =? AND AMOUNT =? AND DATE =? AND CATEGORY =? ", new String[]{name,amount,date,category});
        /*if(deletedRows==-1){
            return false;
        }
        else {
            return true;
        }*/

    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query="Select * from "+FeedEntry.TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }

    public Cursor viewData1() {
        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                FeedEntry.COL_1,
                FeedEntry.COL_2,
                FeedEntry.COL_3,
                FeedEntry.COL_4
        };

// Filter results WHERE "title" = 'My Title'


// How you want the results sorted in the resulting Cursor

        Cursor cursor = db.query(
                FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        /*List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(FeedEntry.COL_5));
            itemIds.add(itemId);
        }*/
        return cursor;
    }
    public void updateName(String name,String name1){
        SQLiteDatabase db = this.getWritableDatabase();

// New value for one column
        String title1 = name;
        String t1=FeedEntry.COL_1;
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COL_1, title1);

// Which row to update, based on the title
        String selection = FeedEntry.COL_1+ " LIKE ? ";
        String[] selectionArgs = {name1};
        db.update(
                FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public void updateAmount(String amount,String amount1){
        SQLiteDatabase db = this.getWritableDatabase();

// New value for one column
        String title2 = amount;
        String t2=FeedEntry.COL_2;
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COL_2, title2);

// Which row to update, based on the title
        String selection =FeedEntry.COL_2+" LIKE ? ";
        String[] selectionArgs = {amount1};

        db.update(
                FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public void updateDate(String date,String date1){
        SQLiteDatabase db = this.getWritableDatabase();

// New value for one column
        String title3 = date;
        String t3=FeedEntry.COL_3;
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COL_3, title3);

// Which row to update, based on the title
        String selection = FeedEntry.COL_3+" LIKE ? ";
        String[] selectionArgs = {date1};

        db.update(
                FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public void updateCategory(String category,String category1){
        SQLiteDatabase db = this.getWritableDatabase();

// New value for one column
        String title3 = category;
        String t3=FeedEntry.COL_4;
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COL_4, title3);

// Which row to update, based on the title
        String selection = FeedEntry.COL_4+" LIKE ? ";
        String[] selectionArgs = {category1};

        db.update(
                FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public void updateData(String name,String name1,String date,String date1,String amount,String amount1,String category,String category1){
        SQLiteDatabase db = this.getWritableDatabase();

// New value for one column
        String title1 = name;
        String title2 = amount;
        String title3 = date;
        String title4 = category;
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COL_1, title1);
        values.put(FeedEntry.COL_2, title2);
        values.put(FeedEntry.COL_3, title3);
        values.put(FeedEntry.COL_4, title4);

// Which row to update, based on the title
        String selection = FeedEntry.COL_4+" LIKE ? ";
        String[] selectionArgs = {category1};

        db.update(
                FeedEntry.TABLE_NAME,
                values,
                "NAME =? AND AMOUNT =? AND DATE =? AND CATEGORY =? ", new String[]{name1,amount1,date1,category1});
    }

    public Cursor searchName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedEntry.COL_1,
                FeedEntry.COL_2,
                FeedEntry.COL_3,
                FeedEntry.COL_4
        };

// Filter results WHERE "title" = 'My Title'
        String selection = FeedEntry.COL_1+ " = ?";
        String[] selectionArgs = {name};

// How you want the results sorted in the resulting Cursor

        Cursor cursor = db.query(
                FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        return cursor;
    }

    public Cursor searchAmount(String amount) {
        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedEntry.COL_1,
                FeedEntry.COL_2,
                FeedEntry.COL_3,
                FeedEntry.COL_4
        };

// Filter results WHERE "title" = 'My Title'
        String selection = FeedEntry.COL_2 + " = ?";
        String[] selectionArgs = {amount};

// How you want the results sorted in the resulting Cursor

        Cursor cursor = db.query(
                FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        return cursor;
    }

    public Cursor searchDate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedEntry.COL_1,
                FeedEntry.COL_2,
                FeedEntry.COL_3,
                FeedEntry.COL_4
        };

// Filter results WHERE "title" = 'My Title'
        String selection = FeedEntry.COL_3 + " = ?";
        String[] selectionArgs = {date};

// How you want the results sorted in the resulting Cursor

        Cursor cursor = db.query(
                FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        return cursor;
    }

    public Cursor searchCategory(String category) {
        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedEntry.COL_1,
                FeedEntry.COL_2,
                FeedEntry.COL_3,
                FeedEntry.COL_4
        };

// Filter results WHERE "title" = 'My Title'
        String selection = FeedEntry.COL_4 + " = ?";
        String[] selectionArgs = {category};

// How you want the results sorted in the resulting Cursor

        Cursor cursor = db.query(
                FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        return cursor;
    }

    public Cursor getItemId(String name,String amount,String date,String category){
        SQLiteDatabase db=this.getWritableDatabase();
        String query1= " SELECT "+FeedEntry.COL_5+" FROM "+ FeedEntry.TABLE_NAME +" WHERE"+ FeedEntry.COL_1+" = "+name+FeedEntry.COL_2+" = "+amount+FeedEntry.COL_3+" = "+date+FeedEntry.COL_4+" = "+category;

        Cursor data= db.rawQuery(query1,null);
        return data;
    }

    public int sumCat(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT SUM("+FeedEntry.COL_4+") FROM "+ FeedEntry.TABLE_NAME+" WHERE CATEGORY !=? OR CATEGORY NOT LIKE?", new String[]{"INCOME"});
        if(cursor.moveToFirst()){
            int total=cursor.getInt(cursor.getColumnIndex("TOTAL"));
            return total;
        }
        return 0;
    }

    public Cursor income1(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT NAME,AMOUNT,DATE,CATEGORY FROM entry WHERE CATEGORY LIKE?",new String[]{"INCOME"} );
        return cursor;
    }

    public Cursor expenses() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT NAME,AMOUNT,DATE,CATEGORY FROM entry WHERE CATEGORY  NOT LIKE?",new String[]{"INCOME"} );
        return cursor;
    }

    public  float income(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query(FeedEntry.TABLE_NAME, new String[]{"SUM(AMOUNT)"},"CATEGORY LIKE? AND DATE =?", new String[]{"INCOME",dash.strdate}, null,null,"DATE ASC");
        float f = 0;
        cursor.moveToFirst();
        if(cursor!=null && cursor.moveToFirst()){
            f=cursor.getFloat(cursor.getColumnIndexOrThrow("SUM(AMOUNT)"));
            cursor.close();
        }
        return f;
    }

    public  float expenses1(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query(FeedEntry.TABLE_NAME, new String[]{"SUM(AMOUNT)"},"CATEGORY NOT LIKE? AND DATE =? ", new String[]{"INCOME",dash.strdate}, null,null,null);
        float f = 0;
        cursor.moveToFirst();
        if(cursor!=null && cursor.moveToFirst()){
            f=cursor.getFloat(cursor.getColumnIndexOrThrow("SUM(AMOUNT)"));
            cursor.close();
        }
        return f;
    }

    public float difference(){
        float d1,d2;
        d1=income();
        d2=expenses1();
        if(d1>d2) {
            float d3 = d1 - d2;
            return d3; //savings
        }
        else
            return 0;
    }

    public float food(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT SUM(AMOUNT) FROM entry WHERE CATEGORY LIKE? AND DATE=?",new String[]{"FOOD", dash.strdate} );
        float f = 0;
       cursor.moveToFirst();
       if(cursor!=null && cursor.moveToFirst()){
           f=cursor.getFloat(0);
           cursor.close();
       }
        return f;
    }

    public float health(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query(FeedEntry.TABLE_NAME, new String[]{"SUM(AMOUNT)"},"CATEGORY LIKE? AND DATE=?", new String[]{"HEALTH",dash.strdate}, null,null,null);
        float f = 0;
        cursor.moveToFirst();
        if(cursor!=null && cursor.moveToFirst()){
            f=cursor.getFloat(cursor.getColumnIndexOrThrow("SUM(AMOUNT)"));
            cursor.close();
        }
        return f;
    }

    public float travel(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query(FeedEntry.TABLE_NAME, new String[]{"SUM(AMOUNT)"},"CATEGORY LIKE? AND DATE=?", new String[]{"TRAVEL",dash.strdate}, null,null,null);
        float f = 0;
        cursor.moveToFirst();
        if(cursor!=null && cursor.moveToFirst()){
            f=cursor.getFloat(cursor.getColumnIndexOrThrow("SUM(AMOUNT)"));
            cursor.close();
        }
        return f;
    }

    public float loans(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor;
        cursor=db.query(FeedEntry.TABLE_NAME, new String[]{"SUM(AMOUNT)"},"CATEGORY LIKE? AND DATE=?", new String[]{"LOANS",dash.strdate}, null,null,null);
        float f = 0;
        cursor.moveToFirst();
        if(cursor!=null && cursor.moveToFirst()){
            f=cursor.getFloat(cursor.getColumnIndexOrThrow("SUM(AMOUNT)"));
            cursor.close();
        }
        return f;
    }

    public float bills(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query(FeedEntry.TABLE_NAME, new String[]{"SUM(AMOUNT)"},"CATEGORY LIKE? AND DATE=?", new String[]{"BILLS",dash.strdate}, null,null,null);
        float f = 0;
        cursor.moveToFirst();
        if(cursor!=null && cursor.moveToFirst()){
            f=cursor.getFloat(cursor.getColumnIndexOrThrow("SUM(AMOUNT)"));
            cursor.close();
        }
        return f;
    }

    public float recreation(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query(FeedEntry.TABLE_NAME, new String[]{"SUM(AMOUNT)"},"CATEGORY LIKE? AND DATE=?", new String[]{"RECREATION",dash.strdate}, null,null,null);
        float f = 0;
        cursor.moveToFirst();
        if(cursor!=null && cursor.moveToFirst()){
            f=cursor.getFloat(cursor.getColumnIndexOrThrow("SUM(AMOUNT)"));
            cursor.close();
        }
        return f;
    }

    public float housing(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor;
        cursor=db.query(FeedEntry.TABLE_NAME, new String[]{"SUM(AMOUNT)"},"CATEGORY LIKE? AND DATE=?", new String[]{"HOUSING",dash.strdate}, null,null,null);
        float f = 0;
        cursor.moveToFirst();
        if(cursor!=null && cursor.moveToFirst()){
            f=cursor.getFloat(cursor.getColumnIndexOrThrow("SUM(AMOUNT)"));
            cursor.close();
        }
        return f;
    }

    public float education(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query(FeedEntry.TABLE_NAME, new String[]{"SUM(AMOUNT)"},"CATEGORY LIKE? AND DATE=?", new String[]{"EDUCATION",dash.strdate}, null,null,null);
        float f = 0;
        cursor.moveToFirst();
        if(cursor!=null && cursor.moveToFirst()){
            f=cursor.getFloat(cursor.getColumnIndexOrThrow("SUM(AMOUNT)"));
            cursor.close();
        }
        return f;
    }

    public float misc(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query(FeedEntry.TABLE_NAME, new String[]{"SUM(AMOUNT)"},"CATEGORY LIKE? AND DATE=?", new String[]{"MISCELLANEOUS",dash.strdate}, null,null,null);
        float f = 0;
        cursor.moveToFirst();
        if(cursor!=null && cursor.moveToFirst()){
            f=cursor.getFloat(cursor.getColumnIndexOrThrow("SUM(AMOUNT)"));
            cursor.close();
        }
        return f;
    }
    /*public float savings(){
        SQLiteDatabase db=this.getWritableDatabase();

    }*/
}


