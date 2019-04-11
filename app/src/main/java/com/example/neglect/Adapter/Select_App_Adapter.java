package com.example.neglect.Adapter;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.neglect.GetterSetter.Details;
import com.example.neglect.GetterSetter.GetIndex;
import com.example.neglect.GetterSetter.Icon_Name;
import com.example.neglect.R;
import com.example.neglect.Utility.My_Utility;

import java.util.ArrayList;
import java.util.List;

import static com.example.neglect.Utility.My_Utility.details;


public class Select_App_Adapter extends RecyclerView.Adapter<Select_App_Adapter.AppViewHolder> {

    PackageManager packageManager;
    List<PackageInfo> packageInfoList1;
     int c1=-1;

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.app_list_view,viewGroup,false);
        AppViewHolder appViewHolder=new AppViewHolder(view);
        return appViewHolder;
    }

    public Select_App_Adapter(ArrayList<Details> details, PackageManager packageManager, List<PackageInfo> packageInfoList1) {
        My_Utility.details = details;
        this.packageManager = packageManager;
        this.packageInfoList1=packageInfoList1;
    }

    public Object getItem(int position) {
        return packageInfoList1.get(position);
    }
    @Override
    public void onBindViewHolder(@NonNull final AppViewHolder appViewHolder, final int i) {


        final Details details1 = details.get(i);
        PackageInfo packageInfo = (PackageInfo) getItem(i);
        final Drawable appIcon = packageManager
                .getApplicationIcon(packageInfo.applicationInfo);
        final String appName = packageManager.getApplicationLabel(
                packageInfo.applicationInfo).toString();
        appIcon.setBounds(0, 0, 80, 80);
        appViewHolder.textView.setCompoundDrawables(appIcon, null, null, null);
        appViewHolder.textView.setCompoundDrawablePadding(15);
        appViewHolder.textView.setText(appName);
        //appViewHolder.textView.setText(details1.getName());
       // appViewHolder.checkBox.setChecked(details1.getCheckBox());
        appViewHolder.checkBox.setChecked(details1.getCheckBox());
        appViewHolder.checkBox.setTag(details1);
        appViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Details student_class = (Details) cb.getTag();
                student_class.setCheckBox(cb.isChecked());
                details.get(i).setCheckBox(cb.isChecked());
                Icon_Name icon_name = new Icon_Name(appIcon,appName);
                if(My_Utility.details.get(i).getCheckBox())
                {
                            My_Utility.icon.add(icon_name);
                           // My_Utility.name.add(appName);
                }
                else
                {
                   appViewHolder.checkBox.setChecked(true);
                    //My_Utility.icon.remove(index);
                    //My_Utility.c.remove(i);
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return details.size();
    }


    public class AppViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CheckBox checkBox;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.appname);
            checkBox=itemView.findViewById(R.id.checkbox);
        }

    }

}
