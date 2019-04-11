package com.example.neglect;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.neglect.Adapter.Select_App_Adapter;
import com.example.neglect.GetterSetter.Details;
import com.example.neglect.Utility.My_Utility;

import java.util.ArrayList;
import java.util.List;

import static com.example.neglect.Utility.My_Utility.details;
import static com.example.neglect.Utility.My_Utility.icon;


public class SelectApp  extends AppCompatActivity {
     RecyclerView listView;
     RecyclerView.Adapter adapter;
     RecyclerView.LayoutManager layoutManager;
     Button submitButton;
     PackageManager packageManager;
     ArrayList<Details> s = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewapp1);
        Toolbar toolbar =findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitButton = findViewById(R.id.submit);
        listView = findViewById(R.id.list_item1);
        packageManager =  getPackageManager();

        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);
        final List<PackageInfo> packageInfoList1 = new ArrayList<PackageInfo>();
        for(PackageInfo pi : packageInfoList) {
            boolean b = isSystemPackage(pi);
            if(!b) {
                packageInfoList1.add(pi);
                s.add(new Details(packageManager.getApplicationLabel(
                        pi.applicationInfo).toString(),false));
            }

        }
        if(My_Utility.icon.size()!=0)
        {
            for(int j=0;j<icon.size();j++)
            for (int i=0;i<packageInfoList1.size();i++)
            {
                if(My_Utility.details.get(i).getName().equalsIgnoreCase(My_Utility.icon.get(j).getName()))
                {
                    My_Utility.details.get(i).setCheckBox(true);
                    s.get(i).setCheckBox(true);
                }
            }
        }
        layoutManager=new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        listView.setHasFixedSize(true);
        adapter=new Select_App_Adapter(s,packageManager,packageInfoList1);
        listView.setAdapter(adapter);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(),
                        Edit_Profile.class);
//                Bundle b = new Bundle();
//                ArrayList<String> name = new ArrayList<>();
//                for(int i=0;i<icon.size();i++)
//                {
//                    name.add(My_Utility.icon.get(i).getName());
//                }
//                b.putStringArrayList("selectedItems", name);
//                intent.putExtras(b);
                startActivity(intent);
                finish();
            }});
    }
    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
                : false;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),
                Edit_Profile.class);
        startActivity(intent);
        finish();
    }
}
