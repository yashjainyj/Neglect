package com.example.neglect.Navigation_Items;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.neglect.R;
import com.example.neglect.Utility.My_Utility;

public class Nav_Setting extends AppCompatActivity {
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        frameLayout = findViewById(R.id.box);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Nav_Setting.this);
                View view = getLayoutInflater().inflate(R.layout.alertdialogsetmesage,null);
                final EditText editText = view.findViewById(R.id.message1);
                editText.setText("Don't waste your time and be productive!");
                builder.setView(view);
                builder.setTitle("Blocking Screen Message").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        My_Utility.message = editText.getText().toString();
                        Toast.makeText(Nav_Setting.this, My_Utility.message, Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("No",null);
                builder.show();
            }
        });
    }

}
