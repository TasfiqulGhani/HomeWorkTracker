package tashfik.jjmotivation.com.wakeup.utils;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import tashfik.jjmotivation.com.wakeup.models.HomeWork;

public class DB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "hwx.db";
    Context context;
    private HashMap hp;

    public DB(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table homeworktab " +
                        "(homework text,day integer,month integer,tday integer,tmonth integer,ledtime integer)"
        );

         
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS homeworktab");
        onCreate(db);
    }
     
 
    public boolean insertHomeWork(HomeWork p) {

        Calendar cal = Calendar.getInstance();
        int toadayDate=cal.get(Calendar.DAY_OF_MONTH);
        int todayMonth=cal.get(Calendar.MONTH);
        todayMonth=todayMonth+1;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("homework", p.getWork());
            contentValues.put("day", p.getDeliveryDay());
            contentValues.put("month", p.getDeliveryMonth());
            contentValues.put("tday", toadayDate);
            contentValues.put("tmonth",todayMonth);
            contentValues.put("ledtime", p.getLed());
            db.insert("homeworktab", null, contentValues);
            return true;
        } catch (SQLiteConstraintException e) {
            Log.e("Already", "there");
            return false;
        }

    }


    public String getDate(){
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        return formattedDate;
    }


    

    public void DeleteAllHomeWork() {
        SQLiteDatabase db = this.getWritableDatabase();
         db.delete("homeworktab",null,null);

    }



    public void removeHomeWork(HomeWork f) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("homeworktab","homework='"+f.getWork()+"'",null);

    }

 
    public ArrayList<HomeWork> getAllHomeWork(){
        ArrayList<HomeWork> data = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from homeworktab", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                HomeWork p=new HomeWork();
                p.setWork(cursor.getString(cursor.getColumnIndex("homework")));
                p.setDeliveryDay(Integer.parseInt(cursor.getString(cursor.getColumnIndex("day"))));
                p.setDeliveryMonth(Integer.parseInt(cursor.getString(cursor.getColumnIndex("month"))));
                 p.setTheDay(Integer.parseInt(cursor.getString(cursor.getColumnIndex("tday"))));
                p.setTheMonth(Integer.parseInt(cursor.getString(cursor.getColumnIndex("tmonth"))));
                p.setLed(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ledtime"))));




                data.add(p);
                cursor.moveToNext();
            }
        }
        return data;

    }







 

}