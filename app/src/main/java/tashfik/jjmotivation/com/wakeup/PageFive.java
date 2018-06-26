package tashfik.jjmotivation.com.wakeup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PageFive extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_five);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ( (ImageView) findViewById(R.id.jan)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PageFive.this,MainActivity.class);
                i.putExtra("check","1");
                startActivity(i);
            }
        });



        ( (ImageView) findViewById(R.id.feb)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PageFive.this,MainActivity.class);
                i.putExtra("check","2");
                startActivity(i);
            }
        });



        ( (ImageView) findViewById(R.id.mar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PageFive.this,MainActivity.class);
                i.putExtra("check","3");
                startActivity(i);
            }
        });



        ( (ImageView) findViewById(R.id.apr)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PageFive.this,MainActivity.class);
                i.putExtra("check","4");
                startActivity(i);
            }
        });

        ( (ImageView) findViewById(R.id.may)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PageFive.this,MainActivity.class);
                i.putExtra("check","5");
                startActivity(i);
            }
        });



        ( (ImageView) findViewById(R.id.june)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PageFive.this,MainActivity.class);
                i.putExtra("check","6");
                startActivity(i);
            }
        });




        ( (ImageView) findViewById(R.id.july)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PageFive.this,MainActivity.class);
                i.putExtra("check","7");
                startActivity(i);
            }
        });

        ( (ImageView) findViewById(R.id.aug)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PageFive.this,MainActivity.class);
                i.putExtra("check","8");
                startActivity(i);
            }
        });

        ( (ImageView) findViewById(R.id.sep)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PageFive.this,MainActivity.class);
                i.putExtra("check","9");
                startActivity(i);
            }
        });

        ( (ImageView) findViewById(R.id.oct)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PageFive.this,MainActivity.class);
                i.putExtra("check","10");
                startActivity(i);
            }
        });




        ( (ImageView) findViewById(R.id.nov)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PageFive.this,MainActivity.class);
                i.putExtra("check","11");
                startActivity(i);
            }
        });



        ( (ImageView) findViewById(R.id.dec)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PageFive.this,MainActivity.class);
                i.putExtra("check","12");
                startActivity(i);
            }
        });
    }

}
