package tashfik.jjmotivation.com.wakeup;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rackspira.kristiawan.rackmonthpicker.RackMonthPicker;
import com.rackspira.kristiawan.rackmonthpicker.listener.DateMonthDialogListener;
import com.rackspira.kristiawan.rackmonthpicker.listener.OnCancelMonthDialogListener;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import tashfik.jjmotivation.com.wakeup.models.HomeWork;
import tashfik.jjmotivation.com.wakeup.utils.DB;
import tashfik.jjmotivation.com.wakeup.utils.HomeWorkAdapter;
import tashfik.jjmotivation.com.wakeup.utils.Saver;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private ArrayList<HomeWork> productsList;
    private HomeWorkAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                addHomework(dayOfMonth, monthOfYear + 1);

            }

        };

        Button all=findViewById(R.id.all);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PageFive.class));
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DB d = new DB(MainActivity.this);
        try {
            Log.e("Get", "" + d.getAllHomeWork().get(0).getWork());
        } catch (Exception e) {

        }
        productsList = d.getAllHomeWork();




        recyclerView = (RecyclerView) findViewById(R.id.rec);


        mAdapter = new HomeWorkAdapter(productsList, R.layout.food_row, MainActivity.this);

        recyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        try{
            Intent i =getIntent();
            int searchD=Integer.parseInt(i.getStringExtra("check"));

            filter(searchD);
        }catch (Exception e){

        }
    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }


    void filter(int S){
        ArrayList<HomeWork> temp=new ArrayList<>();
        for(int i=0;i<productsList.size();i++){
            Log.e("GOTT",""+productsList.get(i).getDeliveryMonth()+"  "+S);
            if(productsList.get(i).getDeliveryMonth()==S){
                temp.add(productsList.get(i));
            }
        }
        mAdapter = new HomeWorkAdapter(temp, R.layout.food_row, MainActivity.this);

        recyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }




    public void addHomework(final int d, final int m) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Add Weight");
        alertDialog.setMessage("Do you want to add new Weight ?");

        final EditText weight = new EditText(MainActivity.this);
        final EditText led = new EditText(MainActivity.this);
        LinearLayout layout = new LinearLayout(MainActivity.this);
        layout.setOrientation(LinearLayout.VERTICAL);

        weight.setHint("Homework");
        led.setHint("Led Time");
        layout.addView(led);
        layout.addView(weight);
        alertDialog.setView(layout);
        alertDialog.setIcon(R.drawable.ic_launcher);

        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String WeightCal = weight.getText().toString();
                        String leda = led.getText().toString();

                        if (WeightCal.equals(null) || WeightCal.equalsIgnoreCase("") || WeightCal.length() < 0) {
                            Toast.makeText(MainActivity.this, "Enter Home work !", Toast.LENGTH_SHORT).show();
                        }else
                        if (leda.equals(null) || leda.equalsIgnoreCase("") || leda.length() < 0) {
                            Toast.makeText(MainActivity.this, "Enter  !", Toast.LENGTH_SHORT).show();
                        } else {

                            Calendar cal = Calendar.getInstance();
                            int toadayDate=cal.get(Calendar.DAY_OF_MONTH);

                            int dif=(d-toadayDate)+1;

                            if(dif>=Integer.parseInt(leda)){
                                HomeWork a = new HomeWork();
                                a.setDeliveryMonth(m);
                                a.setDeliveryDay(d);
                                a.setLed(Integer.parseInt(leda));
                                a.setWork(WeightCal);
                                DB d = new DB(MainActivity.this);
                                d.insertHomeWork(a);
                                startActivity(new Intent(MainActivity.this, PageFour.class));
                                finish();
                            }else if(dif<=0){
                                HomeWork a = new HomeWork();
                                a.setDeliveryMonth(m);
                                a.setDeliveryDay(d);
                                a.setLed(Integer.parseInt(leda));
                                a.setWork(WeightCal);
                                DB d = new DB(MainActivity.this);
                                d.insertHomeWork(a);
                                startActivity(new Intent(MainActivity.this, PageFour.class));
                                finish();
                            }else{
                                AlertDialog.Builder builder;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                                } else {
                                    builder = new AlertDialog.Builder(MainActivity.this);
                                }
                                builder.setTitle("Error !")
                                        .setMessage("Lead time can not be more than difference between delivery date and this date.")
                                        .setPositiveButton("Try Again !", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // continue with delete
                                            }
                                        })

                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            }

                        }


                    }
                });

        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }

    public boolean getLedChecker(int d1, int m1 ,int led){

        Calendar thatDay1 = Calendar.getInstance();
        thatDay1.set(Calendar.DAY_OF_MONTH,d1);
        thatDay1.set(Calendar.MONTH,m1);

        Calendar thatDay2 = Calendar.getInstance();

        long diff = thatDay1.getTimeInMillis()-thatDay2.getTimeInMillis()  ;
        long days = diff / (24 * 60 * 60 * 1000);

        if(days<led){
            Toast.makeText(this, "Time is less than led time !", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            return false;
        }

    }



}
