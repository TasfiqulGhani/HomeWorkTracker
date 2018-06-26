package tashfik.jjmotivation.com.wakeup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


       Intent intent = getIntent();


        ((TextView)findViewById(R.id.title)).setText(intent.getStringExtra("n"));
        ((TextView)findViewById(R.id.details)).setText(intent.getStringExtra("d"));

        ((TextView)findViewById(R.id.price)).setText(intent.getStringExtra("m")+" ");

    }

}
