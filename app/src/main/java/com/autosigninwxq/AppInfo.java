package com.autosigninwxq;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * APP实体类
 */

public class AppInfo {
    public String appName="";//应用名称
    public String packageName = "";//应用包名
    public String versionName = "";//版本名称
    public int versionCode = 0;//版本号
    public Drawable appIcon = null;//应用图标
    public boolean chosen = false;
    /**
     * 获取本机所安装app信息//////////但是撒多
     * @return List<AppInfo> app信息列表
     */
    public static ArrayList<AppInfo> showSupportApps(Context context) {
        ArrayList<AppInfo> appList = new ArrayList<AppInfo>();
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (int i = 0;i < packages.size();i++) {
            PackageInfo packageInfo = packages.get(i);
            AppInfo tempInfo = new AppInfo();
            tempInfo.appName = packageInfo.applicationInfo.loadLabel(pm).toString();
            tempInfo.packageName = packageInfo.packageName;
            tempInfo.versionName = packageInfo.versionName;
            tempInfo.versionCode = packageInfo.versionCode;
            tempInfo.appIcon = packageInfo.applicationInfo.loadIcon(pm);
            //去除系统应用
            if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                if (tempInfo.appName.equals("什么值得买") ||
                        tempInfo.appName.equals("趣头条")||
                        tempInfo.appName.equals("牛客")||
                        tempInfo.appName.equals("学习强国")||
                        tempInfo.appName.equals("腾讯动漫")||
                        tempInfo.appName.equals("中国国航")) {
                    appList.add(tempInfo);
                }
            }
        }
        return appList;
    }
}
