package com.example.neglect.Adapter;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.neglect.R;
import com.example.neglect.Utility.My_Utility;

import java.util.ArrayList;
import java.util.List;

public class AppListAdapter extends BaseAdapter {
        Activity context;
        PackageManager packageManager;
        List<PackageInfo> packageInfoList;
        public AppListAdapter(Activity context,
                              List<PackageInfo> packageInfoList1, PackageManager packageManager) {
            super();
            this.context = context;
            this.packageInfoList = packageInfoList1;
            this.packageManager = packageManager;
        }

    private class ViewHolder {
        TextView apkName;

    }

    public int getCount() {
        return packageInfoList.size();
    }

    public Object getItem(int position) {
        return packageInfoList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;


        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.app_list_view_selected, null);
            holder = new ViewHolder();
            holder.apkName =  convertView.findViewById(R.id.appname);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PackageInfo packageInfo = (PackageInfo) getItem(position);
        Drawable appIcon = packageManager
                .getApplicationIcon(packageInfo.applicationInfo);
        String appName = packageManager.getApplicationLabel(
                packageInfo.applicationInfo).toString();
        if(My_Utility.details.get(position).getCheckBox())
        {
            appIcon.setBounds(0, 0, 80, 80);
            holder.apkName.setCompoundDrawables(appIcon, null, null, null);
            holder.apkName.setCompoundDrawablePadding(15);
            holder.apkName.setText(appName);
        }

        return convertView;
    }
}
