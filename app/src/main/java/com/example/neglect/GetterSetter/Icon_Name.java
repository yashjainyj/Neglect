package com.example.neglect.GetterSetter;

import android.graphics.drawable.Drawable;

public class Icon_Name  {
    Drawable d;
    String name;

    public Icon_Name(Drawable d, String name) {
        this.d = d;
        this.name = name;
    }

    public Drawable getD() {
        return d;
    }

    public void setD(Drawable d) {
        this.d = d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
