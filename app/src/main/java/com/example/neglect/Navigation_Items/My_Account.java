package com.example.neglect.Navigation_Items;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.neglect.Database.insert_new_user;
import com.example.neglect.R;

public class My_Account extends AppCompatActivity {
    insert_new_user insert_new_user;
    TextView name1;
    EditText name,email,pass;
    String id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__account);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name = findViewById(R.id.NAME);
        email = findViewById(R.id.Email);
        pass = findViewById(R.id.password);
        name1=findViewById(R.id.name11);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final Intent intent = getIntent();
        FloatingActionButton floatingActionButton= findViewById(R.id.fab);

        id= intent.getStringExtra("id");
        insert_new_user = new insert_new_user(this);
        Cursor c = insert_new_user.getDatabyid(id);
        while (c.moveToNext())
        {
            name.setText(c.getString(1));
            email.setText(c.getString(2));
            pass.setText(c.getString(3));
            name1.setText(c.getString(1));
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Are u sure you want to save it ", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                boolean c = insert_new_user.update(id,name.getText().toString(),email.getText().toString(),pass.getText().toString());
                                if(c)
                                    Snackbar.make(v, "updated successfully", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                else
                                    Snackbar.make(v, "Not Updated", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                            }
                        }).show();
            }
        });
    }
  //  @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater= getMenuInflater();
//        menuInflater.inflate(R.menu.edit_profile_menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int i = item.getItemId();
//        switch (i)
//        {
//            case R.id.save :
//                        finish();
//        }
//        return true;
//    }
}
