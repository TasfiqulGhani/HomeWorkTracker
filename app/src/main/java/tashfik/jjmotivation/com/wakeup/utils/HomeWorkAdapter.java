package tashfik.jjmotivation.com.wakeup.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import tashfik.jjmotivation.com.wakeup.Details;
import tashfik.jjmotivation.com.wakeup.MainActivity;
import tashfik.jjmotivation.com.wakeup.R;
import tashfik.jjmotivation.com.wakeup.models.HomeWork;


/**
 * Created by wel on 24-02-2017.
 */

public class HomeWorkAdapter extends RecyclerView.Adapter<HomeWorkAdapter.ViewHolder> {

    String source = null;
    DB db;
    int toadayDate;
    int todayMonth;
    
    Intent intent;
    private ArrayList<HomeWork> list;
    private int rowLayout;
    private Context mContext;
    private Activity activity;
    private int pos;

    public HomeWorkAdapter(ArrayList<HomeWork> list, int rowLayout, Activity activity) {
        this.list = list;
        this.rowLayout = rowLayout;
        this.activity = activity;

    }



    public void setData(ArrayList<HomeWork> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void clearData() {

        if (list != null)
            list.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

     
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        int theDay=list.get(position).getTheDay();
        int theMonth=list.get(position).getTheMonth();
        int theDeliver=list.get(position).getDeliveryDay();
        Calendar cal = Calendar.getInstance();
        toadayDate=cal.get(Calendar.DAY_OF_MONTH);
        Log.e("toadayDate",""+toadayDate);
        todayMonth=cal.get(Calendar.MONTH);
        todayMonth=todayMonth+1;
        viewHolder.title.setText(list.get(position).getWork());
        Log.e("GetTheDay",""+list.get(position).getTheDay());
        int lead=list.get(position).getLed();

        int dif=(theDeliver-theDay);





        if(lead>3){



            int x=lead/3;
            int y=lead-x-x;
            Log.e("","");
            // today date 9
            int blue =theDay;
            int green=theDay+x; //11
            int yellow=green+x; //14
            int red=green+y;   //17
            // del 19



            if( theDay>list.get(position).getDeliveryDay()){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.black));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.black));


            } else if(toadayDate>=list.get(position).getDeliveryDay()){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.black));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.black));

            } else if(toadayDate>yellow && toadayDate<=theDeliver){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.red));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.red));

            }else if(toadayDate>green && toadayDate <=red){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.yellow));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.yellow));

            }else if(toadayDate>blue && toadayDate <=yellow){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.green));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.grean));


            }else if(toadayDate==list.get(position).getTheDay()){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.blue));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.blue));

            }else {
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.yellow));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.yellow));

            }
        }else if(lead==3){
            if( theDay>list.get(position).getDeliveryDay()){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.black));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.black));


            } else if(toadayDate>list.get(position).getDeliveryDay()){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.black));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.black));

            } else if(toadayDate==theDeliver){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.red));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.red));

            }else if(theDeliver-1==toadayDate){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.yellow));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.yellow));

            }else if(theDeliver-2 == toadayDate){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.green));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.grean));

            }
            else {
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.blue));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.blue));

            }
        }else if(lead==2) {


            if( theDay>list.get(position).getDeliveryDay()){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.black));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.black));


            } else if(toadayDate>list.get(position).getDeliveryDay()){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.black));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.black));

            } else if(toadayDate==theDeliver){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.red));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.red));

            } else if(toadayDate==theDeliver-1){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.yellow));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.yellow));

            }
            else {
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.blue));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.blue));

            }
        }else if(lead==1){

            if( theDay>list.get(position).getDeliveryDay()){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.black));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.black));


            } else if(toadayDate>list.get(position).getDeliveryDay()){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.black));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.black));

            }else   if(toadayDate==theDeliver){
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.red));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.red));

            }else  {
                viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.blue));
                viewHolder.layer.setBackgroundColor(activity.getResources().getColor(R.color.blue));

            }

        }















        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            intent = new Intent(activity, Details.class);
           /* HomeWork p=list.get(position);
                intent.putExtra("n",p.getWork());
                intent.putExtra("d",p.getDeliveryDay() );
                intent.putExtra("m",""+p.getDeliveryMonth() );

               // activity.startActivity(intent);
*/


                db=new DB(activity);
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(activity, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(activity);
                }
                builder.setTitle("Choose")
                        .setMessage("Do you want to remove this homework ?")

                        .setNegativeButton("Remove", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                HomeWork p=list.get(position);
                                DB d=new DB(activity);
                                d.removeHomeWork(p );
                                activity.startActivity(new Intent(activity,  MainActivity.class));
                                activity.finish();
                            }
                        }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })



                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });

/*
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                db=new DB(activity);
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(activity, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(activity);
                }
                builder.setTitle("Choose")
                        .setMessage("Do you want to add this HomeWork to caloric ?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {




 
                                    android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(activity);
                                    alertDialog.setTitle("Add amount");
                                    alertDialog.setMessage("Please enter HomeWork amount ?");

                                    final EditText weight = new EditText(activity);

                                    LinearLayout layout = new LinearLayout(activity);
                                    layout.setOrientation(LinearLayout.VERTICAL);

                                    weight.setHint("Amount");

                                    layout.addView(weight);
                                    alertDialog.setView(layout);
                                    alertDialog.setIcon(R.mipmap.ic_launcher_round);

                                    alertDialog.setPositiveButton("YES",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    String WeightCal = weight.getText().toString();



                                                    if (WeightCal.equals(null) || WeightCal.equalsIgnoreCase("") || WeightCal.length()<0) {
                                                        Toast.makeText(activity, "Enter Weight Name !", Toast.LENGTH_SHORT).show();
                                                    }else {
                                                        HomeWork p=list.get(position);

                                                        double cal= ((double)Integer.parseInt(WeightCal)/(double)p.getAmount())*(double)p.getCal();
                                                        Log.e("CALA",""+cal+"  "+Integer.parseInt(WeightCal)+"  "+p.getAmount()+"  "+p.getCal());
                                                        db.insertCaloric( cal);
                                                        activity.startActivity(new Intent(activity, tashfik.celiapp.com.celiapp.activities.HomeWork.class));
                                                        activity.finish();
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
                        })
                        .setNegativeButton("Remove", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                HomeWork p=list.get(position);
                                DB d=new DB(activity);
                                d.removeHomeWork(p.getName());
                                activity.startActivity(new Intent(activity, tashfik.celiapp.com.celiapp.activities.HomeWork.class));
                                activity.finish();
                            }
                        }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })



                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
            }
        });


*/

    }

     

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
          TextView title;
          ImageView image;
          LinearLayout layer;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.txt_title);
            image = (ImageView) itemView.findViewById(R.id.image);
            layer = (LinearLayout) itemView.findViewById(R.id.layer);
        }
    }



    public long getLeadTime(int d1, int m1, int d2, int m2){

        Calendar thatDay1 = Calendar.getInstance();
        thatDay1.set(Calendar.DAY_OF_MONTH,d1);
        thatDay1.set(Calendar.MONTH,m1);

        Calendar thatDay2 = Calendar.getInstance();
        thatDay2.set(Calendar.DAY_OF_MONTH,d2);
        thatDay2.set(Calendar.MONTH,m2);

        long diff = thatDay1.getTimeInMillis()-thatDay2.getTimeInMillis()  ;
        long days = diff / (24 * 60 * 60 * 1000);
        return days;
    }



}
