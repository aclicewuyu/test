package com.autosigninwxq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *  接受一个数组作为参数来构建显示ListView
 */

class AppAdapter extends ArrayAdapter<AppInfo> {
    private ArrayList<AppInfo> appList;
    private LayoutInflater inflater;
    private int showCnt;
    private Button start;

    AppAdapter(Context context, int resource, ArrayList<AppInfo> objects, Button btn) {
        super(context, resource, objects);
        start = btn;
        this.appList = objects;
        inflater = LayoutInflater.from(context);
        showCnt = 0;
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public AppInfo getItem(int i) {
        return appList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        if (view == null)
            vi = inflater.inflate(R.layout.app_list, null);
        final AppInfo appInfo = appList.get(i);
        TextView appName = vi.findViewById(R.id.app_name);
        final CheckBox chosen = vi.findViewById(R.id.app_chosen);
        chosen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chosen.isChecked()){
                    showCnt++;
                    start.setVisibility(View.VISIBLE);
                    appInfo.chosen = true;
                    switch (appInfo.appName) {
                        case "什么值得买":
                            Utils.showToast("将为您自动执行“什么值得买”APP签到", getContext());
                            break;
                        case "趣头条":
                            Utils.showToast("将为您自动执行“趣头条”APP的签到", getContext());
                            break;
                        case "学习强国":
                            Utils.showToast("将为您自动执行“学习强国”APP的签到", getContext());
                            break;
                        case "牛客":
                            Utils.showToast("将为您自动执行“牛客”APP的签到", getContext());
                            break;
                        case "腾讯动漫":
                            Utils.showToast("将为您自动执行“牛客”APP的签到", getContext());
                            break;
                        case"中国国航":
                            Utils.showToast("将为您自动执行“中国国航”APP签到", getContext());
                            break;
                            default:
                            break;
                    }
                }else{
                    showCnt--;
                    if (showCnt == 0) {
                        start.setVisibility(View.INVISIBLE);
                    }
                    appInfo.chosen = false;
                }
            }
        });
        ImageView appIcon = vi.findViewById(R.id.app_icon);
        appIcon.setImageDrawable(appInfo.appIcon);
        appName.setText(appInfo.appName);
        return vi;
    }
}
