package com.autosigninwxq;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Path;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static com.autosigninwxq.SignInService.autoCondition;
import static com.autosigninwxq.SignInService.autoLock;
import static com.autosigninwxq.Utils.delay;

public class autoSignInSMZDM {
    private boolean found = false;
    final String SMZDM = "com.smzdm.client.android";

    public void doSMZDM(AccessibilityService service) {
        autoLock.lock();
        try {
            delay(6000);
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            Log.i("msg","执行了第一个返回");
            delay(4000);
            Utils.reLaunch(service, SMZDM);
            delay(5000);
            iteratorSMZDM(service);
            //autoCondition.await(10, TimeUnit.SECONDS); // 10s
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            Log.i("msg","签到完执行了第一个返回");
            delay(2000);
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            delay(2000);
            Log.i("msg","签到完执行了第二个返回");
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            delay(2000);
            Log.i("msg","签到完执行了第三个返回");
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            delay(2000);
            Log.i("msg","签到完执行了第四个返回");
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            delay(2000);
            Log.i("msg","签到完执行了第5个返回");
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            Log.i("msg","签到完执行了第6个返回");
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
            Log.i("msg","签到完执行了第7个返回");

            delay(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }


        autoLock.unlock();
        MainActivity.condition.signal();
    }
    public static AccessibilityNodeInfo findNodeInfosById(AccessibilityNodeInfo nodeInfo, String resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId(resId);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }
    private void iteratorSMZDM(AccessibilityService service) {
        Log.i("msg","正在点击第一个按钮");
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        targetNode = findNodeInfosById(nodeInfo, "com.smzdm.client.android:id/tab_usercenter");
        Log.i("msg","目标结点输出");
        System.out.println(targetNode);
        while (targetNode!=null){
            Log.i("msg","targetNode");
            System.out.println(targetNode);
            if(targetNode.isClickable()){
                targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                break;
            }
            targetNode = targetNode.getParent();
        }
        delay(2000);
        iteratorSMZDM1( service);
        found = true;
        return;
}
    private void  iteratorSMZDM1( AccessibilityService service) {
        Log.i("msg","正在点击第二个按钮");
        AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        targetNode = findNodeInfosById(nodeInfo, "com.smzdm.client.android:id/tv_login_sign");
        Log.i("msg","目标结点输出");
        System.out.println(targetNode);
        while (targetNode!=null){
            Log.i("msg","targetNode");
            System.out.println(targetNode);
            if(targetNode.isClickable()){
                targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                break;
            }
            targetNode = targetNode.getParent();
        }
        return;
    }


}
