package com.example.neglect.Navigation_Items;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.neglect.GetterSetter.NotificationData;
import com.example.neglect.Login_Register.Login_Activity;
import com.example.neglect.Login_Register.MainActivity;
import com.example.neglect.R;
import com.example.neglect.Utility.My_Utility;

import java.util.ArrayList;

import static com.example.neglect.Utility.My_Utility.notificationData;

public class NotificationNav_bar extends AppCompatActivity {
    TextView message;
    ListView notification_list;
    CustomListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_nav_bar);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            notification_list = findViewById(R.id.notification_list);
            message = findViewById(R.id.nonoti);
            adapter = new CustomListAdapter();
            notification_list.setAdapter(adapter);
            LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));
 }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notification, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                Intent intent = new Intent(
                        "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public BroadcastReceiver onNotice= new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // String pack = intent.getStringExtra("package");
            String title = intent.getStringExtra("title");
            String text = intent.getStringExtra("text");
            //int id = intent.getIntExtra("icon",0);

            Context remotePackageContext = null;
            try {
//                remotePackageContext = getApplicationContext().createPackageContext(pack, 0);
//                Drawable icon = remotePackageContext.getResources().getDrawable(id);
//                if(icon !=null) {
//                    ((ImageView) findViewById(R.id.imageView)).setBackground(icon);
//                }
                byte[] byteArray =intent.getByteArrayExtra("icon");
                Bitmap bmp = null;
                if(byteArray !=null) {
                    bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                }
                NotificationData notificationData1 = new NotificationData(title,bmp,text);
                if(notificationData !=null) {
                    notificationData.add(notificationData1);
                    adapter.notifyDataSetChanged();
                }else {
                    notificationData = new ArrayList<NotificationData>();
                    notificationData.add(notificationData1);
                    adapter = new CustomListAdapter();
                    notification_list=(ListView)findViewById(R.id.notification_list);
                    notification_list.setAdapter(adapter);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    class CustomListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return notificationData.size();
        }

        @Override
        public Object getItem(int position) {
            return notificationData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.show_notification,null);
            TextView name = convertView.findViewById(R.id.name);
            TextView message = convertView.findViewById(R.id.message_content);
            ImageView image = convertView.findViewById(R.id.image);
            name.setText(notificationData.get(position).getName());
            message.setText(notificationData.get(position).getMessage());
            image.setImageBitmap(notificationData.get(position).getImage());
            return convertView;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NotificationNav_bar.this, MainActivity.class);
        intent.putExtra("id", My_Utility.id);
        startActivity(intent);
    }
}
