package com.autosigninwxq;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.provider.Settings;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MainActivity extends Activity {

    private static final String DATABASE_NAME = "apps.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    private ListView appListView;
    ArrayList<AppInfo> applist;//APP信息
    //公平锁，按照线程获取锁的顺序是按照加锁顺序来的
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static boolean flag = false;
    private static final int REQUEST_PACKAGE_USAGE_STATS_PERMISSION = 1010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //数据库调试工具开启
        SQLiteStudioService.instance().start(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//判断是否有sdk权限，可以省略6.0以上才需要
            checkPermission(REQUEST_PACKAGE_USAGE_STATS_PERMISSION);
        }
        Utils.checkSignInServiceEnabled(this);//开启辅助权限
        appListView = findViewById(R.id.appListView);
        Button start = findViewById(R.id.button);
        Button history_info = findViewById(R.id.history_info);
        getAppList(start);//获取App列表，适配器

//        ArrayList<AppInfo> temp=applist.
        System.out.print("--------applist--------");
        System.out.print(applist);

        //如果能获取到app信息则进行相应的入库操作，更新手机可签到app信息总表和app签到表
        //*******
        //
        //获取数据库db对象
        DBHelper dbHelper=new DBHelper(this,DATABASE_NAME,null,DATABASE_VERSION);
        db = dbHelper.getWritableDatabase();

        //将能签到APP的详细信息录入数据库

        //将appname和apppic(app对应的图标)以键值对的形式存入SharedPreferences中


        //今天的签到信息更新至数据库，未选签到则不更新，选择了才更新数据库信息




        history_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , HistorySignActivity.class);
                startActivity(i);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new SignInThread().start();
            }
        });//创建线程
    }

    private void checkPermission(int requestCode) {
        if (requestCode == REQUEST_PACKAGE_USAGE_STATS_PERMISSION) {
            if (!hasPermission()) {
                //如果用户没有开启"可访问使用记录"，则跳转到该设置项并提醒用户打开
                Utils.showToast("\"签到助手\"提示：\n请开启\"可访问使用记录\"权限", this);
                startActivityForResult(
                        new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS),
                        REQUEST_PACKAGE_USAGE_STATS_PERMISSION);
            }
        }
    }

    //检测用户是否对本app开启了“Apps with usage access”权限
    private boolean hasPermission() {
        AppOpsManager appOps = (AppOpsManager)
                getSystemService(Context.APP_OPS_SERVICE);
        int mode = 0;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                    android.os.Process.myUid(), getPackageName());
        }
        return mode == AppOpsManager.MODE_ALLOWED;
    }
    /*创建多线程*/
    class SignInThread extends Thread {
        @Override
        public void run() {
            super.run();
            processSignIn();
            Log.i("msg","创建签到线程");
            System.out.println(currentThread().getName());
        }
    }

    private void processSignIn() {
        for (int i = 0; i < applist.size(); i++) {
            AppInfo tempInfo = applist.get(i);
            if (tempInfo.chosen) {
                autoSignIn(tempInfo);
            }
        }
    }

    private void autoLaunch(String name) {
        PackageManager packageManager = this.getPackageManager();
        Intent mBootUpIntent = packageManager.getLaunchIntentForPackage(name);
        mBootUpIntent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        this.startActivity(mBootUpIntent);
    }

    private void autoSignIn(AppInfo info) {
        lock.lock();//请求锁
        flag = true;//配合SignInService
        switch (info.appName) {
            case "什么值得买":
                try {
                    autoLaunch(info.packageName);
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
                break;
            case "牛客":
                try {
                    autoLaunch(info.packageName);
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
                break;
            case "学习强国":
                try {
                    autoLaunch(info.packageName);
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
                break;
            case "中国国航":
                try {
                    autoLaunch(info.packageName);
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
                break;
            case "腾讯动漫":
                try {
                    autoLaunch(info.packageName);
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
                break;
            case "趣头条":
                try {
                    autoLaunch(info.packageName);
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
                break;
            default:
                break;

        }

    }

    private void getAppList(Button btn) {
        applist = AppInfo.showSupportApps(this);//显示已经做好的能签到的app
        //构造方法（当前context，每一项显示格式，要显示的数据）
        AppAdapter adapter = new AppAdapter(this, R.layout.app_list, applist, btn);
        //注册一个元素点击事件监听器
        appListView.setAdapter(adapter);
        //如果适配器的内容改变时刷新每个Item的内容。
        adapter.notifyDataSetChanged();
    }


    private void doDestroy()  {
        //释放数据库工具的连接
        SQLiteStudioService.instance().stop();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            doDestroy();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        doDestroy();
    }

}

