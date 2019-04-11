package com.example.neglect.Utility;

import android.text.format.Time;

import com.example.neglect.GetterSetter.Details;
import com.example.neglect.GetterSetter.GetIndex;
import com.example.neglect.GetterSetter.Icon_Name;
import com.example.neglect.GetterSetter.NotificationData;

import java.util.ArrayList;

public class My_Utility {
    public static boolean Login_Check = false;
    public static String Database_Name = "Neglect.db";
    public static String Table_User = "Login";
    public static String message = "";
    public static String id="";
    public static ArrayList<Details> details;
    public static String profile_name = "";
    public static String days = "";
    public static ArrayList<GetIndex> c= new ArrayList<com.example.neglect.GetterSetter.GetIndex>();
    public static String selectedTime="";
    public static long selectedTime1;
    public static ArrayList<String> button = new ArrayList<>();
    public static boolean ischecked = false;
    public static ArrayList<Icon_Name> icon = new ArrayList<>();
    public static ArrayList<NotificationData> notificationData=new ArrayList<>();
    public static boolean isFinished = false;
}
