package com.example.neglect.GetterSetter;

import android.widget.CheckBox;

import java.util.ArrayList;

public class Details  {
    String name;
    boolean checkBox;

    public Details(String name, boolean checkBox) {
        this.name = name;
        this.checkBox = checkBox;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }


}
