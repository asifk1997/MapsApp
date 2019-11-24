package com.example.chaloapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> implements Filterable{

    Context c;
    ArrayList<ListItem> listItems,filterList;
    CustomFilter filter;


    public MyAdapter(Context ctx,ArrayList<ListItem> listItems)
    {
        this.c=ctx;
        this.listItems = listItems;
        this.filterList= listItems;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //CONVERT XML TO VIEW ONBJ
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null);

        //HOLDER
        MyHolder holder=new MyHolder(v);

        return holder;
    }

    //DATA BOUND TO VIEWS
    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        //BIND DATA
        holder.StopId.setText(listItems.get(position).getStopID());
        holder.stopName.setText(listItems.get(position).getStopName());




        //IMPLEMENT CLICK LISTENET
        holder.img.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Log.e("AK","clicked: "+listItems.get(position).getStopName());
                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                intent.putExtra("routeId", listItems.get(position).getStopID());
                intent.putExtra("routeName",listItems.get(position).getStopName());
                intent.putExtra("stopDataList",listItems.get(position).getStopDataList());
                c.startActivity(intent);

            }
        });

    }

    //GET TOTAL NUM OF PLAYERS
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    //RETURN FILTER OBJ
    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new CustomFilter(filterList,this);
        }

        return filter;
    }
}
