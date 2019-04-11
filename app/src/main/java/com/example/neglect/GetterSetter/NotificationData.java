package com.example.neglect.GetterSetter;


import android.graphics.Bitmap;

public class NotificationData {
    String name;
    Bitmap image;
    String message;
    public NotificationData(String name, Bitmap bmp,String message) {
        this.name = name;
        this.image = bmp;
        this.message =message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
