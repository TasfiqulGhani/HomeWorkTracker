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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rackspira.kristiawan.rackmonthpicker.RackMonthPicker;
import com.rackspira.kristiawan.rackmonthpicker.listener.DateMonthDialogListener;
import com.rackspira.kristiawan.rackmonthpicker.listener.OnCancelMonthDialogListener;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import tashfik.jjmotivation.com.wakeup.R;
import tashfik.jjmotivation.com.wakeup.models.HomeWork;
import tashfik.jjmotivation.com.wakeup.utils.DB;
import tashfik.jjmotivation.com.wakeup.utils.PageFourAdapter;

public class PageFour extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<HomeWork> productsList;
    private PageFourAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_three);
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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(PageFour.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DB d = new DB(PageFour.this);

        productsList = d.getAllHomeWork();

        recyclerView = (RecyclerView) findViewById(R.id.rec);


        mAdapter = new PageFourAdapter(productsList, R.layout.page_four_row, PageFour.this);

        recyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(PageFour.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        Button all=findViewById(R.id.all);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PageFour.this,MainActivity.class));
            }
        });

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
        mAdapter = new PageFourAdapter(temp, R.layout.page_four_row, PageFour.this);

        recyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(PageFour.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }




    public void addHomework(final int d, final int m) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PageFour.this);
        alertDialog.setTitle("Add Homework");
        alertDialog.setMessage("Do you want to add new homework ?");

        final EditText weight = new EditText(PageFour.this);
        final EditText led = new EditText(PageFour.this);
        LinearLayout layout = new LinearLayout(PageFour.this);
        layout.setOrientation(LinearLayout.VERTICAL);

        weight.setHint("Homework");
        led.setHint("Led Time");
        layout.addView(weight);
        layout.addView(led);
        alertDialog.setView(layout);
        alertDialog.setIcon(R.drawable.ic_launcher);

        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String WeightCal = weight.getText().toString();
                        String leda = led.getText().toString();

                        if (WeightCal.equals(null) || WeightCal.equalsIgnoreCase("") || WeightCal.length() < 0) {
                            Toast.makeText(PageFour.this, "Enter Home work !", Toast.LENGTH_SHORT).show();
                        }else
                        if (leda.equals(null) || leda.equalsIgnoreCase("") || leda.length() < 0) {
                            Toast.makeText(PageFour.this, "Enter Led Time !", Toast.LENGTH_SHORT).show();
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
                                DB d = new DB(PageFour.this);
                                d.insertHomeWork(a);
                                startActivity(new Intent(PageFour.this, PageFour.class));
                                finish();
                            }else if(dif<=0){
                                HomeWork a = new HomeWork();
                                a.setDeliveryMonth(m);
                                a.setDeliveryDay(d);
                                a.setLed(Integer.parseInt(leda));
                                a.setWork(WeightCal);
                                DB d = new DB(PageFour.this);
                                d.insertHomeWork(a);
                                startActivity(new Intent(PageFour.this, PageFour.class));
                                finish();
                            } else{
                                AlertDialog.Builder builder;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    builder = new AlertDialog.Builder(PageFour.this, android.R.style.Theme_Material_Dialog_Alert);
                                } else {
                                    builder = new AlertDialog.Builder(PageFour.this);
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





}
