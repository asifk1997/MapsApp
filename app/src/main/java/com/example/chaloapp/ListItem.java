package com.example.chaloapp;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;


public class ListItem {

    private String stopID;
    private String stopName;
    private String stopDataList;
    private ArrayList<LatLng> coordList;



    public ListItem(String stopID, String stopName, String stopDataList,ArrayList<LatLng> coordList) {
        this.stopID = stopID;
        this.stopName = stopName;
        this.stopDataList=stopDataList;
        this.coordList=coordList;
    }

    public String getStopID() {
        return stopID;
    }

    public void setStopID(String stopID) {
        this.stopID = stopID;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getStopDataList() {
        return stopDataList;
    }

    public ArrayList<LatLng> getCoordList() {
        return coordList;
    }
}
