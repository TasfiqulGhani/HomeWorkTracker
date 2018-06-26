package tashfik.jjmotivation.com.wakeup.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tasfiqul Ghani on 6/5/2017.
 */

public class Saver {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public Saver(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }





    public void setCaloricRequired(int isFirstTime) {
        editor.putInt("setPaypal", isFirstTime);
        editor.commit();
    }

    public int getCaloricRequired() {
        return pref.getInt("setPaypal", 0);
    }


    public void setQCounter(int c) {
        editor.putInt("c", c);
        editor.commit();
    }

    public int getQCounter() {
        return pref.getInt("c", 0);
    }



}