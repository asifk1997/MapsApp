package com.example.chaloapp;


import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder  {

    //OUR VIEWS
    TextView StopId;
    TextView stopName;
    ImageButton img;

    public MyHolder(View itemView) {
        super(itemView);
        this.StopId= (TextView) itemView.findViewById(R.id.stop_id);
        this.stopName= (TextView) itemView.findViewById(R.id.stop_name);
        this.img = (ImageButton)itemView.findViewById(R.id.imageButton);



    }



}
