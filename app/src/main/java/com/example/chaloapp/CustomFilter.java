package com.example.chaloapp;

import android.widget.Filter;

import java.util.ArrayList;


public class CustomFilter extends Filter{

    MyAdapter adapter;
    ArrayList<ListItem> filterList;


    public CustomFilter(ArrayList<ListItem> filterList, MyAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<ListItem> filteredListItems =new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getStopName().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredListItems.add(filterList.get(i));
                }
            }

            results.count= filteredListItems.size();
            results.values= filteredListItems;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }


        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

       adapter.listItems = (ArrayList<ListItem>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
