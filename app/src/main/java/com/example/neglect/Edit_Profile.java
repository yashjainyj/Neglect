
package com.example.neglect;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.example.neglect.Login_Register.MainActivity;
import com.example.neglect.Navigation_Items.NotificationNav_bar;
import com.example.neglect.Utility.My_Utility;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


import static com.example.neglect.Utility.My_Utility.button;
import static com.example.neglect.Utility.My_Utility.c;
import static com.example.neglect.Utility.My_Utility.isFinished;
import static com.example.neglect.Utility.My_Utility.selectedTime;


public class Edit_Profile extends AppCompatActivity {
    Button monday,tuesday,wednesday,thrusday,friday,saturday,sunday,addtime,addapp;
    TextView selectedtime;
    EditText entername;
    ListView listViewselected;
    public static long Start_Time_In_MILls;
    public  static long Time_Left_In_MILLIS;
    PackageManager packageManager;
    List<PackageInfo> packageInfoList ;
    List<PackageInfo> packageInfoList1 ;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar =findViewById(R.id.toolbar11);
        setSupportActionBar(toolbar);
        entername = findViewById(R.id.entername);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        monday = findViewById(R.id.monday);
        listViewselected  = findViewById(R.id.listviewSelected);
        tuesday = findViewById(R.id.tuesday);
        wednesday = findViewById(R.id.wednesday);
        thrusday = findViewById(R.id.Thrusday);
        friday = findViewById(R.id.friday);
        saturday = findViewById(R.id.Saturday);
        sunday = findViewById(R.id.Sunday);
        packageManager =  getPackageManager();
        packageInfoList= packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);
        packageInfoList1 = new ArrayList<PackageInfo>();
        addtime = findViewById(R.id.add);
        addapp = findViewById(R.id.adda);

        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.contains("Monday"))
                {
                    button.remove("Monday");
                    monday.setBackgroundResource(R.drawable.round_button_change);
                }
                else
                {
                    button.add("Monday");
                    monday.setBackgroundResource(R.drawable.round_button);
                }
            }
        });
        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.contains("Tuesday"))
                {
                    button.remove("Tuesday");
                    tuesday.setBackgroundResource(R.drawable.round_button_change);
                }
                else
                {
                    button.add("Tuesday");
                    tuesday.setBackgroundResource(R.drawable.round_button);
                }
            }
        });
        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.contains("Wednesday"))
                {
                    button.remove("Wednesday");
                    wednesday.setBackgroundResource(R.drawable.round_button_change);
                }
                else
                {
                    button.add("Wednesday");
                    wednesday.setBackgroundResource(R.drawable.round_button);
                }
            }
        });
        thrusday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.contains("Thrusday"))
                {
                    button.remove("Thrusday");
                    thrusday.setBackgroundResource(R.drawable.round_button_change);
                }
                else
                {
                    button.add("Thrusday");
                    thrusday.setBackgroundResource(R.drawable.round_button);
                }
            }
        });
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.contains("Friday"))
                {
                    button.remove("Friday");
                    friday.setBackgroundResource(R.drawable.round_button_change);
                }
                else
                {
                    button.add("Friday");
                    friday.setBackgroundResource(R.drawable.round_button);
                }
            }
        });
        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.contains("Saturday"))
                {
                    button.remove("Saturday");
                    saturday.setBackgroundResource(R.drawable.round_button_change);
                }
                else
                {
                    button.add("Saturday");
                    saturday.setBackgroundResource(R.drawable.round_button);
                }
            }
        });
        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.contains("Sunday"))
                {
                    button.remove("Sunday");
                    sunday.setBackgroundResource(R.drawable.round_button_change);
                }
                else
                {
                    button.add("Sunday");
                    sunday.setBackgroundResource(R.drawable.round_button);
                }
            }
        });
        selectedtime = findViewById(R.id.selectedtime);
        addtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder a = new AlertDialog.Builder(Edit_Profile.this);
                View view = getLayoutInflater().inflate(R.layout.select_time_rb,null);
                final RadioButton min30 = view.findViewById(R.id.min30);
                final RadioButton hour1 = view.findViewById(R.id.hour1);
                final RadioButton hour2 = view.findViewById(R.id.hour2);
                final RadioButton hour3 = view.findViewById(R.id.hour3);
                final RadioButton hour4 = view.findViewById(R.id.hour4);
                a.setView(view);
                a.setPositiveButton("Select", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(min30.isChecked())
                        {
                            selectedtime.setText("30 Minute");
                            My_Utility.selectedTime = "30 Minute" ;
                            My_Utility.selectedTime1 = 30*60000;
                        }
                        else if(hour1.isChecked())
                        {
                            selectedtime.setText("1 Hour");
                            My_Utility.selectedTime = "1 Hour";
                            My_Utility.selectedTime1 = 1*3600000;
                        }
                        else if(hour2.isChecked())
                        {
                            selectedtime.setText("2 Hour");
                            My_Utility.selectedTime = "2 Hour";
                            My_Utility.selectedTime1 = 2*3600000;
                        }
                        else if(hour3.isChecked())
                        {
                            selectedtime.setText("3 Hour");
                            My_Utility.selectedTime = "3 Hour";
                            My_Utility.selectedTime1 = 3*3600000;
                        }
                        else if(hour4.isChecked())
                        {
                            selectedtime.setText("4 Hour");
                            My_Utility.selectedTime = "4 Hour";
                            My_Utility.selectedTime1 = 4*3600000;
                        }

                    }
                });
                a.setNegativeButton("Cancel",null);
                a.show();
            }
        });
        addapp = findViewById(R.id.adda);
        if(!My_Utility.profile_name.equalsIgnoreCase(""))
        entername.setText(My_Utility.profile_name);
        if(!selectedTime.equalsIgnoreCase(""))
            selectedtime.setText(selectedTime);

        if(button.contains("Monday"))
        {
            monday.setBackgroundResource(R.drawable.round_button);
        }
        if(button.contains("Tuesday"))
        {
            tuesday.setBackgroundResource(R.drawable.round_button);
        }
        if(My_Utility.button.contains("Wednesday"))
        {
            wednesday.setBackgroundResource(R.drawable.round_button);
        }
        if(My_Utility.button.contains("Thrusday"))
        {
            thrusday.setBackgroundResource(R.drawable.round_button);
        }
        if(My_Utility.button.contains("Friday"))
        {
            friday.setBackgroundResource(R.drawable.round_button);
        }
        if(My_Utility.button.contains("Saturday"))
        {
            saturday.setBackgroundResource(R.drawable.round_button);
        }
        if(My_Utility.button.contains("Sunday"))
        {
            sunday.setBackgroundResource(R.drawable.round_button);
        }
//        Bundle b = getIntent().getExtras();
//      if(b!=null)
//      {
//          resultArr = b.getStringArrayList("selectedItems");
//         // adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, resultArr);
//          listViewselected.setAdapter(new Custom());
//      }
        if(My_Utility.icon.size()!=0)
        {
            listViewselected.setAdapter(new Custom());
        }
        addapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit_Profile.this,SelectApp.class);
                My_Utility.profile_name = entername.getText().toString();
                startActivity(intent);
                finish();
            }
        });
        listViewselected.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        for(PackageInfo pi : packageInfoList) {
            boolean c = isSystemPackage(pi);
            if(!c) {
                packageInfoList1.add(pi);
            }

        }
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
                : false;
    }

    class Custom extends BaseAdapter
    {

        @Override
        public int getCount() {
            return My_Utility.icon.size();
        }

        @Override
        public Object getItem(int position) {
            return My_Utility.icon.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.app_list_view_selected,null);
            final TextView name = view.findViewById(R.id.appname);
            ImageButton delete = view.findViewById(R.id.delete);
            final Drawable appIcon = My_Utility.icon.get(position).getD();
            appIcon.setBounds(0, 0, 80, 80);
            name.setCompoundDrawables(appIcon, null, null, null);
            name.setCompoundDrawablePadding(15);
            name.setText(My_Utility.icon.get(position).getName());
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItemFromList(v,position);
                }
            });
            return view;
        }

        private void deleteItemFromList(View v, int position) {
            My_Utility.icon.remove(position);
           // My_Utility.name.remove(position);
            notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.edit_profile_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String days1="";
        String[] daysm = {"Monday","Tuesday","Wednesday","Thrusday","Friday","Saturday","Sunday"};
        String[] daysm1 = {"M","T","W","Th","F","Sa","S"};
        switch (id)
        {
            case R.id.save :
                if(entername.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(this, "Enter Profile Name", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(Edit_Profile.this, MainActivity.class);;
                    intent.putExtra("id",My_Utility.id);
                    for(int i=0;i<daysm.length;i++)
                    {
                        if(days1.equalsIgnoreCase(""))
                        {
                            if(My_Utility.button.contains(daysm[i]))
                                days1 = days1 + "  " +daysm1[i];
                        }
                        else
                            if(My_Utility.button.contains(daysm[i]))
                                 days1 = days1 + "," +daysm1[i];
                    }
                    My_Utility.ischecked=true;
                    Start_Time_In_MILls = My_Utility.selectedTime1;
                    Time_Left_In_MILLIS = Start_Time_In_MILls;
                    My_Utility.profile_name = entername.getText().toString();
                    My_Utility.days = days1;
                    Calendar calendar = Calendar.getInstance();
                    String currentday = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                    String[] cd = currentday.split(",");
                    if(My_Utility.button.contains(cd[0]))
                    startTimer();
                    Toast.makeText(Edit_Profile.this, "Profile Saved" , Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public  void startTimer()
    {
        countDownTimer = new CountDownTimer(Time_Left_In_MILLIS,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Time_Left_In_MILLIS= millisUntilFinished;
                updateCount();
            }
            @Override
            public void onFinish() {
                isFinished = false;
            }
        }.start();
        isFinished = true;
    }
    public static void updateCount()
    {

        int minutes = (int) (Time_Left_In_MILLIS /1000) /60;
        int seconds = (int) (Time_Left_In_MILLIS /1000) %60;
        String timeleft = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        Log.i("Time Left " , "--------------->" + timeleft);
    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("Do you want to save it ?").setPositiveButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String days1="";
                String[] daysm = {"Monday","Tuesday","Wednesday","Thrusday","Friday","Saturday","Sunday"};
                String[] daysm1 = {"M","T","W","Th","F","Sa","S"};
                Intent intent = new Intent(Edit_Profile.this, MainActivity.class);;
                intent.putExtra("id",My_Utility.id);
                for(int i=0;i<daysm.length;i++)
                {
                    if(days1.equalsIgnoreCase(""))
                    {
                        if(My_Utility.button.contains(daysm[i]))
                            days1 = days1 + "  " +daysm1[i];
                    }
                    else
                    if(My_Utility.button.contains(daysm[i]))
                        days1 = days1 + "," +daysm1[i];
                }
                My_Utility.ischecked=true;
                Start_Time_In_MILls = My_Utility.selectedTime1;
                Time_Left_In_MILLIS = Start_Time_In_MILls;
                My_Utility.profile_name = entername.getText().toString();
                My_Utility.days = days1;
                Calendar calendar = Calendar.getInstance();
                String currentday = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                String[] cd = currentday.split(",");
                if (My_Utility.button.contains(cd[0]))
                startTimer();
                Toast.makeText(Edit_Profile.this, "Profile Saved"  , Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        }).setNegativeButton("cancel",null);
        builder.show();
    }

}

