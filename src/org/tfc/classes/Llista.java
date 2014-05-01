package org.tfc.classes;

import android.content.Context;
import android.text.format.Time;
import com.appcelerator.cloud.sdk.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: Oscar
 * Date: 7/04/14
 * Time: 21:15
 * To change this template use File | Settings | File Templates.
 */
public class Llista {
    private int ACS_id;
    private String name;
    private String weekday;
    private Time hour;
    private String location;

    public Llista() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public int getACS_id() {
        return ACS_id;
    }

    public String getName() {
        return name;
    }

    public String getWeekday() {
        return weekday;
    }

    public Time getHour() {
        return hour;
    }

    public String getLocation() {
        return location;
    }


    public Llista(String listname, String listweekday, Time listhour, String listlocation){


    }

}

