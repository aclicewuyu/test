package com.autosigninwxq;

import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static com.autosigninwxq.Utils.prt;
/*import org.opencv.core.Mat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;*/

public class SignInService extends AccessibilityService {
    String fgPackageName;
    String oldPackageName = "None";
    static Lock autoLock = new ReentrantLock();
    static Condition autoCondition = autoLock.newCondition();
    autoSignInSMZDM smzdm = new autoSignInSMZDM();
    autoSignInGH  gh = new autoSignInGH();
    autoSignInQTT  qtt = new autoSignInQTT();
    autoSignInNK nk = new autoSignInNK();
    autoSignInXXQG  xxqg = new autoSignInXXQG();
    autoSignInTXDM  txdm = new autoSignInTXDM();


    Bitmap sourcemap;
    Bitmap templatemap;


    ImageView imgHuaishi;

/**
 * 通过这个函数可以接收系统发送来的AccessibilityEvent，接收来的AccessibilityEvent是经过过滤的，过滤是在配置工作时设置的。
 */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Log.i("msg","接受系统发送来的accessibilityevent");
        fgPackageName = accessibilityEvent.getPackageName().toString();
        prt("***Event: " + accessibilityEvent);
        prt(oldPackageName + " ------------------------- " + fgPackageName);
        if (!oldPackageName.equals(fgPackageName)) {
            new autoSignThread(accessibilityEvent).start();
        }
        oldPackageName = fgPackageName;
    }

    class autoSignThread extends Thread {//每个应用的签到都是一个线程
        private AccessibilityEvent event;
        autoSignThread(AccessibilityEvent arg) {
            event = arg;
        }
        @Override
        public void run() {
            super.run();
            autoSign();//
        }
    }
      /**
      *保证按线程顺序进行签到
       */

    private void autoSign() {
        if (MainActivity.flag) {
            MainActivity.lock.lock();
            MainActivity.flag = false;
            switch (fgPackageName) {
                case "com.smzdm.client.android":
                    smzdm.doSMZDM(this);
                    sendNotification();
                    break;
                case "com.nowcoder.app.florida":
                    nk.doNK(this);
                    sendNotification2();
                    break;
                case "com.jifen.qukan":
                    qtt.doQTT(this);
                    sendNotification3();
                    break;
                case "cn.xuexi.android":
                    xxqg.doXXQG(this);
                    break;
                case "com.qq.ac.android":
                    txdm.doTXDM(this);
                    break;
                case "com.rytong.airchina":
                    gh.doGH(this);
                    sendNotification4();
                    break;
                default:
                    break;
            }
            oldPackageName = "None";
            MainActivity.lock.unlock();
        }
    }

   /* public  void test(){
        sourcemap = BitmapFactory.decodeResource(getResources(), R.drawable.text_img);
        templatemap = BitmapFactory.decodeResource(getResources(), R.drawable.text_img1);
        Mat sourceori = new Mat();
        Mat source =new Mat();

        Mat templateori = new Mat();
        Mat template = new Mat();

        Utils.bitmapToMat(sourcemap, sourceori);
        Utils.bitmapToMat(templatemap, templateori);

        Imgproc.cvtColor(templateori, template, Imgproc.COLOR_RGBA2BGR);
        Imgproc.cvtColor(sourceori, source, Imgproc.COLOR_RGBA2BGR);

        int height = source.rows() - template.rows() + 1;
        int width = source.cols() - template.cols() + 1;
        Mat result = new Mat(height, width, CvType.CV_32FC1);


        // 模板匹配
        int method = Imgproc.TM_CCOEFF_NORMED;
        Imgproc.matchTemplate(source, template, result,method);
        Core.MinMaxLocResult minMaxResult = Core.minMaxLoc(result);
        Point maxloc = minMaxResult.maxLoc;
        Point minloc = minMaxResult.minLoc;
        Point matchloc = null;
        if(method == Imgproc.TM_SQDIFF || method == Imgproc.TM_SQDIFF_NORMED) {
            matchloc = minloc;
        } else {
            matchloc = maxloc;
        }

        Log.i(TAG, matchloc.x+"----------------"+matchloc.y);

       // imgHuaishi = (ImageView)findViewById(R.id.img_huaishi);
       // imgHuaishi.setImageBitmap(templatemap);
    }*/
   private void sendNotification() {
       //获取NotificationManager实例
       NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
       //实例化NotificationCompat.Builde并设置相关属性
       NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
               //设置小图标
               .setSmallIcon(R.drawable.logo)

               .setContentTitle("什么值得买签到完成")

               //设置通知内容
               .setContentText("ok")
               .setDefaults(Notification.DEFAULT_VIBRATE);
       notifyManager.notify(1, builder.build());
   }
    private void sendNotification2() {
        //获取NotificationManager实例
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //实例化NotificationCompat.Builde并设置相关属性
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                //设置小图标
                .setSmallIcon(R.drawable.logo)

                .setContentTitle("牛客签到完成")

                //设置通知内容
                .setContentText("ok")
                .setDefaults(Notification.DEFAULT_VIBRATE);
        notifyManager.notify(2, builder.build());
    }
    private void sendNotification3() {
        //获取NotificationManager实例
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //实例化NotificationCompat.Builde并设置相关属性
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                //设置小图标
                .setSmallIcon(R.drawable.logo)

                .setContentTitle("趣头条APP签到完成")

                //设置通知内容
                .setContentText("ok")
                .setDefaults(Notification.DEFAULT_VIBRATE);
        notifyManager.notify(3, builder.build());
    }
    private void sendNotification4() {
        //获取NotificationManager实例
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //实例化NotificationCompat.Builde并设置相关属性
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                //设置小图标
                .setSmallIcon(R.drawable.logo)

                .setContentTitle("中国国航APP签到完成")

                //设置通知内容
                .setContentText("ok")
        .setDefaults(Notification.DEFAULT_VIBRATE);
        notifyManager.notify(4, builder.build());
    }

    @Override
    public void onInterrupt() {
        Log.v("msg", "服务关闭了...");
    }
}
