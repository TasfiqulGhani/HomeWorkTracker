package tashfik.jjmotivation.com.wakeup.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
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

public class PageFourAdapter extends RecyclerView.Adapter<PageFourAdapter.ViewHolder> {

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

    public PageFourAdapter(ArrayList<HomeWork> list, int rowLayout, Activity activity) {
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


        viewHolder.date.setText(""+list.get(position).getDeliveryDay()+" / "+list.get(position).getDeliveryMonth());
        viewHolder.title.setText(list.get(position).getWork());
        viewHolder.tdate.setText(""+list.get(position).getTheDay()+" / "+list.get(position).getTheMonth());


        viewHolder.lead.setText(""+list.get(position).getLed());





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
          TextView title, date , tdate,lead;


        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.homework);
            date = (TextView) itemView.findViewById(R.id.deliverydate);
            tdate = (TextView) itemView.findViewById(R.id.thisdate);
            lead = (TextView) itemView.findViewById(R.id.leadtime);
        }
    }





}
