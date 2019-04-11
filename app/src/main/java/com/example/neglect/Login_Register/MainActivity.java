package com.example.neglect.Login_Register;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ImageView;
import com.example.neglect.Database.insert_new_user;
import com.example.neglect.Edit_Profile;
import com.example.neglect.Navigation_Items.My_Account;
import com.example.neglect.Navigation_Items.Nav_Setting;
import com.example.neglect.Navigation_Items.NotificationNav_bar;
import com.example.neglect.Navigation_Items.Strict_mode;
import com.example.neglect.R;
import com.example.neglect.Utility.My_Utility;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FrameLayout frameLayout;
    TextView name,daysm;
    Switch aSwitch;
    com.example.neglect.Database.insert_new_user insert_new_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name = findViewById(R.id.name);
//        Intent intent1 = getIntent();
//        name.setText(intent1.getStringExtra("name"));
        daysm = findViewById(R.id.daysm);
        name.setText(My_Utility.profile_name);
        daysm.setText(My_Utility.days);
        aSwitch = findViewById(R.id.switchonOFF);
        if (My_Utility.ischecked) {
            aSwitch.setChecked(true);
            aSwitch.setText("ON");
        }
//        aSwitch.setChecked(false);
//            aSwitch.setText("ON");
        frameLayout = findViewById(R.id.profile1);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aSwitch.setChecked(true);
                aSwitch.setText("ON");
                My_Utility.ischecked = true;
                Intent intent = new Intent(MainActivity.this, Edit_Profile.class);
                startActivity(intent);
                finish();
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    My_Utility.ischecked = true;
                    aSwitch.setText("ON");
                    My_Utility.isFinished=true;

                }
                else
                {
                    aSwitch.setText("OFF");
                    My_Utility.ischecked = false;
                    My_Utility.isFinished = false;
                }
            }
        });

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        // Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = getLayoutInflater().inflate(R.layout.nav_header_main,null);
        insert_new_user = new insert_new_user(this);
        TextView name = view.findViewById(R.id.headtitle);
        TextView email = view.findViewById(R.id.heademail);
        Cursor c = insert_new_user.getDatabyid(id);
        My_Utility.id = id;
        My_Utility.Login_Check = true;

        while (c.moveToNext()){
            name.setText(c.getString(1));
            email.setText(c.getString(2));
        }
        navigationView.addHeaderView(view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            Intent intent = new Intent(MainActivity.this, My_Account.class);
            intent.putExtra("id",My_Utility.id);
            startActivity(intent);
        } else if (id == R.id.nav_home) {

        } else if (id == R.id.nav_nootification) {
            Intent intent = new Intent(MainActivity.this, NotificationNav_bar.class);
            startActivity(intent);

        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(MainActivity.this, Nav_Setting.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {
            AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this).setTitle("About Us").setMessage("Neglect \n\nMeans Don't Neglect yourself you are the only one who can do anything .\nDon't waste your Important time in useless things").setCancelable(true);
            a.show();
        } else if (id == R.id.nav_contact) {
            AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this).setTitle("Contact Us").setMessage(" Neglect \n Yash jain \n Mobile No: 9630288239 \n Email id : jainyash031@gmail.com").setCancelable(true);
            a.show();
        }
        else if (id==R.id.nav_d)
        {
            Intent intent = new Intent(MainActivity.this, Strict_mode.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}